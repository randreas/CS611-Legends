import java.math.BigDecimal;
import java.util.*;

/*
 * BattleRound.java - battleround class that creates monsters to spawn and heroes to fight
 */
public class BattleRound {
	private List<Monster> monsterList;
	private ioUtility io;
	private int level = -1;
	
	/*
	 * BattleRound constructor - chooses which monsters spawn during the battle
	 */
	public BattleRound(LegendsPlayer p, List<Monster> monList) {
	
		this.monsterList = new ArrayList<Monster>();
		io = new ioUtility();
		io.playSound("monsterbattle");
		this.level = -1;
		for(Hero h : p.getHeroes()) {
			this.level = Math.max(this.level, h.getLevel());
		}
		
		List<Monster> filteredList = new ArrayList<Monster>();
		for(Monster m : monList) {
			if(m.getLevel() == this.level) {
				filteredList.add(m);
			}
		}
		
		int numMonsters = p.getHeroes().size();
		for(int i = 0; i < numMonsters; i++) {
			Random r = new Random();
			Monster m = (Monster) filteredList.get(r.nextInt(filteredList.size())).clone();
			
			this.monsterList.add(m);
		}
	
	}
	
	/*
	 * Function to run the battle round fight logic
	 */
	public void fight(LegendsPlayer p) {
		printBattleStats(p);
		int roundResult = 0;
		while(roundResult == 0) {
			playerPhase(p);
			monsterPhase(p);
			roundResult = checkRoundResults(p);
			switch(roundResult) {
			case 0:
				for(Hero h : p.getHeroes()) {
					if(h.getHp() != 0) {
						h.regenHpMana();
					}
				}
				break;
			case 1:
				playerWinsResults(p);
				break;
			case 2:
				playerLoseResults(p);
				break;
			}
		}
		
	}
	
	

	/*
	 * Function that runs the player phase of the battle 
	 */
	public void playerPhase(LegendsPlayer p) {
		for(Hero h : p.getHeroes()) {
			
			if(h.getHp()== 0) {
				continue;
			}
			System.out.println("It is " + h.getName() + "\'s Turn");
			String action;
			Monster m = null;
			boolean validAction = false;
			while(!validAction) {
				action = io.parseBattleAction();
				switch(action) {
				case "A":
					m = chooseMonster(monsterList);
					
					boolean hit = h.attack(m);
					if(hit) {
						io.printAttackScene(h,m, h.calculateDmg(m));
					} else {
						io.printDodgeScene(m);
					}
					
					updateMonsterTeam(monsterList);
					validAction = true;
					break;
				case "S":
					if(h.getInventory().getSpellList().size() == 0) {
						System.out.println("You cannot case any spells. Choose another action.");
					} else {
						Spell s = h.chooseSpell();
						if(s == null) {
							continue;
						}
						if(s.getManaCost() > h.getMana() ) {
							System.out.println(s.getName() + " costs more than the mana you have. Choose another action");
						} else {
							m = chooseMonster(monsterList);
							
							hit = h.spellCast(s,m);
							if(hit) {
								io.printSpellScene(h, m, s, h.spellDamage(s, m));
							} else {
								io.printDodgeScene(m);
							}
							
							updateMonsterTeam(monsterList);
							validAction = true;
						}
						
						
					}
					break;
				case "E":
					boolean res = h.useItem();
					if(res) {
						validAction = true;
					}
					break;
				}
			}
			
			int res = checkRoundResults(p);
			if(res == 1) {
				return;
			}
		}
	}
	
	/*
	 * Function to update monster team to remove monsters that have died
	 */
	private void updateMonsterTeam(List<Monster> list) {
		for(int i = list.size()-1; i >= 0; i-- ) {
			if(list.get(i).getHp() <= 0) {
				list.remove(i);
			}
		}
		
	}



	/*
	 * Function for player to choose which monster to attack
	 */
	public Monster chooseMonster(List<Monster> monsterList) {
		int index = io.parseMonsterChoice(monsterList);
		return monsterList.get(index);
	}
	
	/*
	 * Function to run monster phase of the battle
	 */
	public void monsterPhase(LegendsPlayer p) {
		Random r = new Random();
		boolean isValidHit = false;
		for(Monster m : monsterList) {
			
			isValidHit = false;
			while(!isValidHit) {
				int idx = r.nextInt(p.getHeroes().size());
				if(p.getHeroes().get(idx).getHp() > 0) {
					isValidHit = true;
					
					boolean hit = m.attack(p.getHeroes().get(idx));
					if(hit) {
						io.printAttackScene(m, p.getHeroes().get(idx), m.calculateDmg(p.getHeroes().get(idx)));
					} else {
						io.printDodgeScene(p.getHeroes().get(idx));
					}
				}
				
			}
		}
	}
	
	
	/*
	 * Function to print stats of each hero and monster
	 */
	public void printBattleStats(LegendsPlayer p) {
		io.printHeroes(p.getHeroes());
		System.out.println("============================================================================================");
		io.printMonsters(monsterList);
		System.out.println();
	}
	
	/*
	 * Function to check roundResults.
	 * Returns 
	 * 0 if battle is ongoing
	 * 1 if player wins fight
	 * 2 if monsters win fight
	 * 
	 */
	public int checkRoundResults(LegendsPlayer p ) {
		printBattleStats(p);
		if(monsterList.size() == 0) {
			return 1;
		} 
		
		boolean allDead = true;
		for(Hero h : p.getHeroes()) {
			if(h.getHp() > 0) {
				allDead = false;
				break;
			}
		}
		
		if(allDead) {
			return 2;
		}
		
		return 0;
		
	}
	
	/*
	 * Function to run events when player loses
	 */
	private void playerLoseResults(LegendsPlayer p) {
		io.printASCIIArt("YOU LOST");
		// TODO Auto-generated method stub
		for(Hero h : p.getHeroes()) {
			h.setHp(h.getLevel());
			h.setMana(h.getLevel());
			h.setWallet(h.getWallet().multiply(new BigDecimal(0.5)));
		}
	}

	/*
	 * Function to run events when player wins
	 */
	private void playerWinsResults(LegendsPlayer p) {
		io.printASCIIArt("YOU WIN");
		io.playSound("battlewin");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(Hero h : p.getHeroes()) {
			if(h.getHp() == 0) {
				h.resurrect();
				
			} else {
				h.gainExp();
				BigDecimal moneyGain = new BigDecimal("100").multiply(new BigDecimal(h.getLevel()));
				h.setWallet(h.getWallet().add(moneyGain));
			}
		}
		
	}
}
