import java.awt.ItemSelectable;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/** Player item that stores relevant user information within the game
 * @author Alex Ng
 */
public class Player extends Mover
{
	//constant game data
	protected final int FULLHUNGER = 250;
	protected final int BASEEXPTONEXTLEVEL = 10;
	protected final int BASESTRENGTH = 10;
	
	/** Stores items held by the player
	 * @author Alex Ng
	 */
	private Item inventory[] = new Item[12];
	
	/** Stores the players current weapon
	 * @author Alex Ng
	 */
	private Weapon weapon;
	
	/** Stores the experience of the player
	 * @author Alex Ng
	 */
	private int exp;
	
	/** Stores the necessary exp to level up the player
	 * @author Alex Ng
	 */
	private int expToNextLevel;
	
	/** Stores the level{experience} of the player
	 * @author Alex Ng
	 */
	private int level;
	/** Stores the strength of the player which dictates the caliber of weapons usable by the player
	 * @author Alex Ng
	 */
	private int strength;
	/** Stores the current hunger of the player
	 * @author Alex Ng
	 */
	private int hunger;
	
	/** Player constructor
	 * @author Alex Ng
	 * @param x
	 * @param y
	 */
	public Player (double x, double y) 
	{
		super(x,y);
		setFullHealth(STARTHEALTH);
		setHealth(getFullHealth());
		setHunger(FULLHUNGER);
		initializeInventory();
	}
	
	/** Returns if a player has leveled up and improves the players stats if true
	 * @author Alex Ng
	 * @return boolean true/false
	 */
	public boolean levelUp()
	{
		if (getEXP() >= getEXPRequired())
		{
			int extraEXP = getEXP()-getEXPRequired();
			resetEXP();
			addEXP(extraEXP);
			incrementEXPRequired(2);
			incrementLevel();
			incrementStrength();
			return true;
		}
		return false;
	}
	
	/** Initializes the players inventory
	 * @author Alex Ng
	 */
	public void initializeInventory()
	{
		for (int i = 0; i < inventory.length; i++)
		{
			inventory[i] = new Item("Empty");
		}
	}
	
	/** Returns the player's inventory
	 * @author Alex Ng 
	 * @return
	 */
	public Item[] getInventory()
	{
		return inventory;
	}
	
	/** Determines a path to a cell
	 * @author Alex Ng
	 * @param cell
	 */
	public void setPath(JLabel cell)
	{
		
	}
	
	/** Sets the hunger of the player
	 * @author Alex Ng
	 * @param amount
	 */
	public void setHunger(int amount)
	{
		if (amount >= 0)
			hunger = amount;
	}
	
	/** Decrements the hunger by one 
	 * @author Alex Ng
	 */
	public void decrementHunger()
	{
		setHunger(getHunger()-1);
	}
	
	/** Resets the hunger back to full
	 * @author Alex Ng
	 */
	public void resetHunger()
	{
		setHunger(FULLHUNGER);
	}
	
	/** Returns the players current hunger
	 * @author Alex Ng
	 * @return
	 */
	public int getHunger()
	{
		return hunger;
	}
	
	/** Sets the experience of the player
	 * @author Alex Ng
	 * @param amount
	 */
	public void setEXP(int amount)
	{
		if (amount >= 0)
			exp = amount;
	}
	
	/** Adds a provided amount of experience to the player
	 * @author Alex Ng
	 * @param amount
	 */
	public void addEXP(int amount)
	{
		setEXP(getEXP()+amount);
	}
	
	/** Sets the amount of experience of the player back to 0
	 * @author Alex Ng
	 */
	public void resetEXP()
	{
		setEXP(0);
	}
	
	/** Returns the current amount of experience the player has
	 * @author Alex Ng
	 * @return
	 */
	public int getEXP()
	{
		return exp;
	}
	
	/** Sets the amount of experience necessary for a player to level up
	 * @author Alex Ng
	 * @param amount
	 */
	public void setEXPRequired(int amount)
	{
		if (amount >= BASEEXPTONEXTLEVEL)
			expToNextLevel = amount;
	}
	
	/** Increments the amount of experience necessary to level up
	 * @author Alex Ng
	 * @param amount
	 */
	public void incrementEXPRequired(int amount)
	{
		setEXPRequired(getEXPRequired()+amount);
	}
	
	/** Returns the amount of experience requires or the player to level up
	 * @author Alex Ng
	 * @return
	 */
	public int getEXPRequired()
	{
		return expToNextLevel;
	}
	
	/** Sets the level{experience} of the player
	 * @author Alex Ng
	 * @param level
	 */
	public void setLevel(int level)
	{
		if (level >= 0)
			this.level = level;
	}
	
	/** increments the level{experience} of the player
	 * @author Alex Ng
	 */
	public void incrementLevel()
	{
		setLevel(getLevel()+1);
	}
	
	/** Returns the level{experience} of the player
	 * @author Alex Ng
	 * @return int level
	 */
	public int getLevel()
	{
		return level;
	}
	
	/** Sets the strength of the player
	 * @author Alex Ng
	 * @param strength
	 */
	public void setStrength(int strength)
	{
		if (strength >= BASESTRENGTH)
			this.strength = strength;
	}
	
	/** Increments the strength of the player by 1 
	 * @author Alex Ng
	 */
	public void incrementStrength()
	{
		setStrength(getStrength()+1);
	}
	
	/** Returns the strength of the player
	 * @author Alex Ng
	 * @return
	 */
	public int getStrength()
	{
		return strength;
	}
	
	/** Removes an item from the players inventory
	 * @author Alex Ng
	 * @param index
	 */
	public void dropItem(int index)
	{
		inventory[index] = new Item(null);
	}
	
	public void pickupItem(Item found)
	{
		for (int item = 0; item < inventory.length; item++)
		{
			if (inventory[item].getName().equals("Empty"))
			{
				inventory[item] = found;
				break;
			}
		}
	}
	
	/** Returns a player cell
	 * @author Alex Ng
	 * @param x
	 * @param y
	 * @return Cell player
	 */
	public Cell getCell(int x, int y)
	{
		return new Cell("P",new JLabel(new ImageIcon("Images/Player/Player.png")),x,y);
	}
	
	/** Moves a player to a coordinate
	 * @author Alex Ng
	 * @param event
	 * @param floor
	 * @param x
	 * @param y
	 */
	public void movePlayer(MouseEvent event, Level floor, int x, int y)
	{
		
	}
}
