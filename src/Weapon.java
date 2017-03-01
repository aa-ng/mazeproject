import java.util.Random;


/** Weapon object that is used to attack monsters
 * 	also scattered within the maze
 * @author Alex Ng
 */
public class Weapon extends Item
{
	/** Minimum strength needed to equip
	 * @author Alex Ng
	 */
	int strengthLevel;
	
	/** Maximum damage weapon can deal
	 * @author Alex Ng
	 */
	int damage;
	
	/** Constructs a weapon with a given strength level
	 * @author Alex Ng
	 * @param name
	 * @param strengthLevel
	 */
	public Weapon(String name, int strengthLevel)
	{
		super(name);
		setStrengthLevel(strengthLevel);
		randomizeDamage();
	}
	
	/** Sets the minimum strength level for the weapon 
	 * @author Alex Ng
	 * @param level
	 */
	public void setStrengthLevel(int level)
	{
		strengthLevel = level;
	}
	
	/** Returns the minimum strength level of the weapon
	 * @author Alex Ng
	 * @return int strengthLevel
	 */
	public int getStrengthLevel()
	{
		return strengthLevel;
	}
	
	/** Sets the maximum damage of the weapon
	 * @author Alex Ng
	 * @param damage
	 */
	public void setDamage(int damage)
	{
		if (damage >= 0)
			this.damage = damage;
	}
	
	/** Returns the maximum damage of the weapon
	 * @author Alex Ng
	 * @return int damage
	 */
	public int getDamage()
	{
		return damage;
	}
	
	/** Randomizes the damage of a weapon
	 * @author Alex Ng
	 */
	public void randomizeDamage()
	{
		Random random = new Random();
		setDamage(random.nextInt(getStrengthLevel()/2)+1);
	}
}
