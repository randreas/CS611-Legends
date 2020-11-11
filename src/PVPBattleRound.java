import java.math.BigDecimal;

/*
 * PVPBattleRound.java - PVPBattleRound class that puts 2 players to fight
 */
public class PVPBattleRound {
	
	private ioUtility io;
	
	public PVPBattleRound() {
		io = new ioUtility();
	}
	

	/*
	 * Function to run the pvp battle round fight logic
	 */
	public int pvpFight(LegendsPlayer p1, LegendsPlayer p2) {
		int winner = 0;
		io.playSound("trainerbattle");
		printBattleStats(p1,p2);
		boolean roundResult = false;
		while(!roundResult) {
			playerPhase(p1,p2);
			roundResult = playerWin(p2);
			if(roundResult) {
				playerWinsResults(p1);
				winner = 1;
				p2.setIsActive(false);
				break;
			}
			playerPhase(p2,p1);
			roundResult = playerWin(p1);
			if(roundResult) {
				playerWinsResults(p2);
				winner = 2;
				p1.setIsActive(false);
				break;
			}
		}
		return winner;
		
		
	}
	
	/*
	 * Function to print battle status of both players
	 */
	private void printBattleStats(LegendsPlayer p1, LegendsPlayer p2) {
		System.out.println(p1.getName() + "'s Team:");
		io.printHeroes(p1.getHeroes());
		System.out.println("========================================================================================================");
		System.out.println(p2.getName() + "'s Team:");
		io.printHeroes(p2.getHeroes());
		System.out.println();
		
	}
	
	/*
	 * Function that runs the player phase of the battle 
	 */
	public void playerPhase(LegendsPlayer p1, LegendsPlayer p2) {
		System.out.println(p1.getName() + ", it is your turn to fight");
		for(Hero h : p1.getHeroes()) {
			
			if(h.getHp()== 0) {
				continue;
			}
			System.out.println("It is " + h.getName() + "\'s Turn");
			String action;
			Hero m = null;
			boolean validAction = false;
			while(!validAction) {
				action = io.parseBattleAction();
				switch(action) {
				case "A":
					m = chooseOpponentHero(p2);
					
					boolean hit = h.attack(m);
					if(hit) {
						io.printAttackScene(h,m, h.calculateDmg(m));
					} else {
						io.printDodgeScene(m);
					}
					
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
							m = chooseOpponentHero(p2);
							
							hit = h.spellCast(s,m);
							if(hit) {
								io.printSpellScene(h, m, s, h.spellDamage(s, m));
							} else {
								io.printDodgeScene(m);
							}
							
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
			printBattleStats(p1,p2);
			
		}
	}
	
	/*
	 * Function for player to choose which hero to attack
	 */
	public Hero chooseOpponentHero(LegendsPlayer p) {
		int index = -1;
		boolean validHero = false;
		while(!validHero) {
			io.printHeroes(p.getHeroes());
			index= io.parseHeroId(p.getHeroes(), false);
			if(p.getHeroes().get(index).getHp() == 0) {
				System.out.println("That opponent is already dead. Don't waste your energy.");
			} else {
				return  p.getHeroes().get(index);
			}
			
		}
		return null;
	}	
	
	/*
	 * Function to check if the current player is winning/opponent is dead
	 */
	public boolean playerWin(LegendsPlayer opp ) {
		boolean allDead = true;
		for(Hero h : opp.getHeroes()) {
			if(h.getHp() > 0) {
				allDead = false;
				break;
			}
		}
		
		if(allDead) {
			return true;
		}
		
		return false;
		
	}
	
	
	/*
	 * Function to run actions when a player wins
	 */
	private void playerWinsResults(LegendsPlayer p) {
		io.printASCIIArt(p.getIcon() + " WINS!");
		io.playSound("trainerbattlewin");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(Hero h : p.getHeroes()) {
			if(h.getHp() == 0) {
				h.resurrect();
				
			} else {
				h.gainExp(h.getExp());
				BigDecimal moneyGain = new BigDecimal("100").multiply(new BigDecimal(h.getLevel()));
				h.setWallet(h.getWallet().add(moneyGain));
			}
		}
		
	}
	 
}
