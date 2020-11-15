import java.util.ArrayList;
import java.util.List;

public class ValorGame extends RPGGame {
	
	private List<Hero> warriorList;
	private List<Hero> paladinList;
	private List<Hero> sorcererList;
	private List<Monster> monsters;
	private Inventory itemInven; 
	private ioUtility io;
	private List<LegendsPlayer> playerList;
	private int numHeroesTeam = 3;
	private List<String> iconList;
	
	
	public void startGame() {
		
		
//		setPlayers();
//		
//		gameMovement();
//		io.playSound("ending");
//		System.out.println("Thank you for playing! See you soon!");
//		io.printASCIIArt("GOODBYE");
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		io.stopAudio();
	}
	
	public ValorGame() {
		
		super(null,"ValorGame",1);
		io = new ioUtility();
		io.printWelcomeMessage();
		//io.playSound("opening");
		iconList = new ArrayList<String>();
		//adding default icons 
		iconList.add("M");
		iconList.add("#");
		iconList.add(" ");
		iconList.add("P");
		iconList.add("K");
		iconList.add("B");
		iconList.add("C");
		Parser p = new Parser();
		warriorList = p.parseWarriors();
		paladinList = p.parsePaladins();
		sorcererList = p.parseSorcerers();
		monsters = p.parseMonsters();
		
		ValorMap map = new ValorMap();
		super.setMap(map);
		map.printMap();
		io.printFullValorMap(map);

		
		
		
	}

}
