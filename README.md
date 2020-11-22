# CS611-Legends
Name: Richard Andreas
BUID: U78371851
Email: ra7296@bu.edu

Name: YuChen Guo
BUID: U86016090
Email: ycg@bu.edu

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

HeroTeam.java       - class that creates a list of heroes in the players team.


Space.java			- Space abstract class for a single space on the map

ValorSpace.java		- Space class that represents the a single space on the map of legends of Valor

BushSpace.java		- This class represents the bush space

CaveSpace.java		- This class represents the cave space

InaccessibleSpace.java		- This class represents the inaccessible space

KoulouSpace.java			- Class that creates a koulou space that buffs heroes strength

NexusSpace.java				- Class represents the Nexus

PlainSpace.java				- Class represents the plain

isBuffableSpace.java		- Interface for the spaces that have buff or debuff

Market.java			- Market class that consists of shoppable items and where heroes can buy and sell items

GridMap.java		- Grid map abstract class that has number of rows and cols

ValorMap.java		- Map class that represents the map for legends of Valor

Location.java		- Class that keep tracks where a unit is on the map.


Main.java			- main java class that runs game

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
4. Sound is available in this game, feel free to turn up the volume.
5. Minimum number of rows is 4, max is 20. Min Lane = 1;  Max Lanes = 5; Min Size of Lane = 1, Max Size of Lane = 5;
6. Maximum number of players is 4 and max heroes per team is 4. Due to current number of heroes parsed.
7. Player has to Type (T) to end turn in order to end his turn after movement or other actions.
8. The heroes don't share their vision, and a hero can only see the rows lower or equal to his/her highest explored row. So a hero can only teleport to the rows (in other lanes) lower or equal to his/her highest explored row. 
9. A hero is forbidden to move into other heroes' Nexus.
10. Files should be kept in src Folder. ConfigFile directory is stored in src. Please do not change src folder name. This will mess up the parser.


How to run:
-------------------------------------------------------------------------------------------------
1. Unzip the Valor.rar using Winrar
2. Copy the src file into a directory and cd into directory and src.
3. You should see a src/ConfigFiles directory as well.
4. Run the Main.java file in src
    > cd <Directory>/src/
    > javac Main.java
    > java Main
5. Configure size of map and heroes per team.
6. Play the game and have fun.

7. If running in Eclipse/IntelliJ, Please copy all files and directories into src.
