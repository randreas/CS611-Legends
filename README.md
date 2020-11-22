# CS611-Legends

Files
----------------------------
Items.java			- abstract class for an item

Armor.java			- armor class that extends items and implements isEquipable

Weapons.java		- weapon class that extends items and implements isEquipable

Spell.java			- abstract spell class that extends item 

FireSpell.java		- Firespell class that extends spell

IceSpell.java		- IceSpell class that extends spell

LightningSpell.java	- LightningSpell class that extends spell

Potion.java			- potion class that extends item

isConsumable.java 	- interface to see if potion is consumable

isEquipable.java	- interface for objects that are equipable

isCastable.java		- interface for objects that are castable (Spells)

isSellable.java		- interface for items that are sellable

Inventory.java		- inventory class that has a list of weapons, armors, spells and potions

SpellType.java		- enum for types of spells


Player.java			- player class that tracks a players name and Symbol

ValorPlayer.java	- Player class that represents the player for lengends of Valor

LegendsPlayer.java 	- Legends Player extends player has list of heroes and curr location on map

Character.java		- abstract class for an in-game character

Hero.java			- hero abstract class that extends character

Paladin.java		- paladin class that extends hero

Warrior.java		- warrior class that extends hero

Sorcerer.java		- sorcerer class that extends hero

Monster.java		- monster class that extends character

MonsterSpecies.java - Enum for monster species

Dragon.java			- Dragon monster class

Exoskeleton.java	- Exoskeleton monster class extends Monster

Spirit.java			- Spirit monster class extends Monster

Attacker.java 		- Interface to see if a character is able to attack

SpellCaster.java 	- Interface to see if a character is able to cast spells

Consumerer.java		- Interface to see if a character is able to consume consumable items (potions)

Stats.java			- stats class that has a stat type and amount of the stat

StatType.java		- enum for type of stat (Hp, Mana, Strength, Dex, Def, AGI)

HeroClass.java 		- enum for hero classes available



Space.java			- Space abstract class for a single space on the map

ValorSpace.java		- Space class that represents the a single space on the map of legends of Valor

BushSpace.java		- This class represents the bush space

CaveSpace.java		- This class represents the cave space

InaccessibleSpace.java		- This class represents the inaccessible space

KoulouSpace.java			- Class that creates a koulou space that buffs heroes strength

NexusSpace.java				- Class represents the Nexus

PlainSpace.java				- Class represents the plain

isBuffableSpace.java		- Interface for the spaces that have buff or debuff

BlockedSpace.java	- blockedspace is type of space that is inaccessible

MarketSpace.java	- marketspace class that extends space to show that area is a market

Market.java			- Market class that consists of shoppable items and where heroes can buy and sell items

CommonSpace.java	- common space  is a blank space that either is safe or battle

BattleRound.java	- battle round class for a battle between between players/monsters

GridMap.java		- Grid map abstract class that has number of rows and cols

ValorMap.java		- Map class that represents the map for legends of Valor

LegendsMap.java		- LegendsMap that extends GridMap which initializes the map itself

PVPBattleRound.java	- pvp battle rond class for a battle between players

Location.java		- Class that keep tracks where a unit is on the map.


Main.java			- main java class that runs game

LegendsGame.java	- legends game which has extends rpggame that runs the legends game logic

RPGGame.java		- rpggame abstract class that extends game which has a gridMap

Game.java    		- abstract game class has number of players and a name

ValorGame.java		- Game class which contians the game function for the legends of Valor

ioUtility.java		- ioUtility class for inputs and outputs

Parser.java			- parser class that parses text files into objects for game

ConsoleColors.java	- java class to add all the available colors for color printing


Notes:
-------------------------------------------------------------------------------------------------
1. Max Level is capped at 10. Modifiable in the Hero Code (Based on max level of monsters provided in config files)
2. Files to be parsed should be stored in ConfigFiles, for parser class to read class
3. Color printing has ben done for this project. Please run it on Eclipse with ANSI ConsolePlugin or IntelliJ
4. If players engage in a PVP battle, the loser is removed from the game, winner receives double the exp he has.
5. Sound is available in this game, feel free to turn up the volume.
6. Code was done and tested using Eclipse, hence printing of tabs might look different on IntelliJ
7. Minimum size of map is 4x4 and Maximum size of map is 20x20 and is a square map.
8. Maximum number of players is 4 and max heroes per team is 4. Due to current number of heroes parsed.
9. Player has to Type (T) to end turn in order to end his turn after movement or other actions.
10. The heroes don't share their vision, and a hero can only see the rows lower or equal to his/her highest explored row. So a hero can only teleport to the rows (in other lanes) lower or equal to his/her highest explored row. 
11. A hero is forbidden to move into other heroes' Nexus.


How to run:
-------------------------------------------------------------------------------------------------
1. Unzip the Legends.rar using Winrar
2. The txt files and wav files should be in ConfigFiles directory, and not with the src files.
	src -> Java files
	ConfigFiles -> text and audio files
3. Run the Main.java file
4. Configure size of map, number of players and number of heroes per team.
5. Play the game and have fun.
