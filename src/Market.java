import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/*
 * Market.java - market class that creates a market instance
 */
public class Market {
	private ioUtility io;
	private Inventory inventory;
	
	public Market(Inventory iv) {
		io = new ioUtility();
		this.inventory = iv;
	}
	
	/*
	 * function for player to choose which heroes would like to enter the store
	 */
	public void shop( LegendsPlayer p) {
		System.out.println("Which hero would like to enter the store? 0 - Exit");
		io.playSound("shop");
		io.printHeroes(p.getHeroes());
		
		int res = io.parseHeroId(p.getHeroes(), true);
		if(res < 0) {
			return;
		} else {
			Hero h = p.getHeroes().get(res);
			buySell(p,h,io);
			shop(p);
		}
		
		
	}
	
	/*
	 * Function to choose whether a hero buys or sells in the market
	 */
	public void buySell(LegendsPlayer p, Hero h, ioUtility io) {
		System.out.println("Would you like to buy or sell an item for " + h.getName() +"?");
		System.out.println("Exit (0)");
		System.out.println("Buy (1)");
		System.out.println("Sell (2)");
		int res = io.parseInt();
		switch(res) {
		case 0:
			System.out.println(h.getName() + " has nothing else to do in the store, time to walk out.");
			break;
		case 1:
			buy(p,h,io);
			break;
		case 2:
			sell(p,h,io);
			break;
		default:
			io.printErrorParse();
			buySell(p,h,io);
			break;
		}
	}
	
/*
 * function for player to buy an item for a hero
 */
  	public void buy(LegendsPlayer p, Hero h, ioUtility io) {
 
		
			boolean yn = true;
			while(yn) {
				String s = io.shopMenu("buy");
				List<?> list = null;
				switch(s) {
					case "S":
						list = inventory.getSpellList();
						io.printSpellShop((List<Spell>)list,true);
						break;
					case "W":
						list = inventory.getWeaponList();
						io.printWeaponShop((List<Weapon>)list,true);
						break;
					case "A":
						list = inventory.getArmorList();
						io.printArmorShop((List<Armor>)list,true);
						break;
					case "P":
						list = new ArrayList<>(inventory.getPotionMap().keySet());
						io.printPotionShop((List<Potion>) list,true);
						break;
					case "E":
						list= null;
						break;
				}
				if(list != null) {
					System.out.println("Enter id of item you would to buy. 0 - Exit");
					int selected = io.parseChoice(list,0);
					if(selected == -1) {
						continue;
					}
					Item item = (Item) list.get(selected);
					if(item.getMinLevelReq() > h.getLevel()) {
						System.out.println("Unable to purchase weapon. Minimum level required to purchase this item = " + item.getMinLevelReq());
					} else if (item.getPrice().compareTo(h.getWallet()) > 0) {
						System.out.println("Unable to purchase weapon." + h.getName() + " Does not have enough funds");
					} else {
						if(item instanceof Weapon || item instanceof Armor || item instanceof Spell) {
							if(h.getInventory().getWeaponList().contains(item) || h.getInventory().getArmorList().contains(item)
									|| h.getInventory().getSpellList().contains(item)) {
								System.out.println("You have purchased this equipment. You do not need more");
							
							} else {
								h.setWallet(h.getWallet().subtract(item.getPrice()));
								if(item instanceof Armor) {
									h.addItemIntoInventory(((Armor)item).clone());
								} else if(item instanceof Weapon) {
									h.addItemIntoInventory(((Weapon)item).clone());
								} else {
									h.addItemIntoInventory(item);
									
								}
								
							}
						} else {
							h.setWallet(h.getWallet().subtract(item.getPrice()));
							
								h.addItemIntoInventory(item);
							
							
						}
							
						
						
					}
					System.out.println("Would you like to purchase other items for " + h.getName() + "? (Y/N)");
					yn = io.parseYesNo();
				} else {
					break;
				}
					
			}
			buySell(p,h,io);
		
	}
	
	
  	/*
  	 * function for player to sell an item for a hero
  	 */
  	public void sell(LegendsPlayer p,Hero h, ioUtility io) {
		
		
			boolean yn = true;
			while(yn) {
				if(h.getInventory().isEmpty()) {
					yn = false;
					System.out.println(ConsoleColors.GREEN + "There are no items in your inventory. Nothing to sell." + ConsoleColors.RESET);
					continue;
				}
				String s = io.shopMenu("sell");
				
				List<?> list = null;
				switch(s) {
					case "S":
						list = h.getInventory().getSpellList();
						if(list.size() == 0) {
							System.out.println("There are no spells in your inventory.");
						} else {
							io.printSpellShop((List<Spell>)list, false);
						}
						
						break;
					case "W":
						
						list = h.getInventory().getWeaponList();
						
						if(list.size() == 0) {
							System.out.println("There are no weapons in your inventory.");
						} else {
							io.printWeaponShop((List<Weapon>)list, false);
						}
						
						
						break;
					case "A":
						list = h.getInventory().getArmorList();
						if(list.size() == 0) {
							System.out.println("There are no armor in your inventory.");
						} else {
							io.printArmorShop((List<Armor>)list, false);
						}
						
						break;
					case "P":
						list = new ArrayList<>(inventory.getPotionMap().keySet());
						if(list.size() == 0) {
							System.out.println("There are no potions in your inventory.");
						} else {
							io.printPotionShop((List<Potion>)list, false);
						}
						
						break;
					case "E":
						break;
				}
				if(list != null && list.size() > 0) {
					System.out.println("Enter id of item you would to sell. 0 - Exit");
					int selected = io.parseChoice(list,0);
					if(selected == -1) {
						continue;
					}
					Item item = (Item) list.get(selected);
					
					if(item instanceof isEquipable) {
						((isEquipable) item).unequip(h);
					}
					
					if(item instanceof Weapon) {
						h.getInventory().getWeaponList().remove(selected);
					} else if(item instanceof Armor) {
						h.getInventory().getArmorList().remove(selected);
					} else if(item instanceof Spell) {
						h.getInventory().getSpellList().remove(selected);
					} else if(item instanceof Potion) {
						if(h.getInventory().getPotionMap().get(item) == 1) {
							h.getInventory().getPotionMap().remove(item);
						} else {
							h.getInventory().getPotionMap().put((Potion)item, h.getInventory().getPotionMap().get(item) - 1);
						}
						
					}
						
					h.setWallet(h.getWallet().add(item.getPrice().multiply(new BigDecimal("0.5"))));
					
				}
				System.out.println("Would you like to sell other items for " + h.getName() + "? (Y/N)");
				yn = io.parseYesNo();
			}
			buySell(p,h,io);
	}
}
