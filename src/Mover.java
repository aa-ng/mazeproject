import java.util.Random;


/** Entity that moves across a level. The following can die and has health.
 * @author Alex Ng
 */
public abstract class Mover 
{
	protected final int STARTHEALTH = 50;
	private double x;
	private double y;
	private int health;
	private int fullHealth;
	
	/** Constructs a Mover with a location
	 * @author Alex Ng
	 * @param x
	 * @param y
	 */
	public Mover(double x, double y)
	{
		setX(x);
		setY(y);
	}
	
	/** Determines randomly with a percent chance if an action was
	 *  successful
	 *  @author Alex Ng
	 * @param chance
	 * @return boolean true/false
	 */
	public boolean sucsessfull(int chance)
	{
		Random random = new Random();
		int percent = random.nextInt(100)+1;
		if (percent <= chance)
			return true;
		else
			return false;
	}
	
	/** Sets the location on the x-axis
	 * @author Alex Ng
	 * @param x
	 */
	public void setX(double x)
	{
		this.x = x;
	}
	
	/** Returns the location on the x-axis (rounded to an integer)
	 * @author Alex Ng
	 * @return int x
	 */
	public int getX()
	{
		return (int) x;
	}
	
	/** Returns the location on the x-axis
	 * @author Alex Ng
	 * @return
	 */
	public double getRX()
	{
		return x;
	}
	
	/** Sets the location on the y-axis
	 * @author Alex Ng
	 * @param y
	 */
	public void setY(double y) 
	{
		this. y = y;
	}
	
	/** Returns the location on the y-axis (rounded to an integer)
	 * @author Alex Ng
	 * @return
	 */
	public int getY()
	{
		return (int) y;
	}
	
	/** Returns the location on the y-axis
	 * @author Alex Ng
	 * @return
	 */
	public double getRY()
	{
		return (double) y;
	}
	
	/** Sets the health of the mover.
	 *  Must be less then the maximum health of the mover
	 *  but not less then 0.
	 * @author Alex Ng
	 * @param amount
	 */
	public void setHealth(int amount)
	{
		if (amount <= getFullHealth() && amount >= 0)
			this.health = amount;
	}
	
	/** Removes health from the mover
	 * @author Alex Ng
	 * @param amount
	 */
	public void lostHealth(int amount)
	{
		setHealth(getHealth()-amount);
	}
	
	/** Determines if the mover was hit from an attack
	 *  and took damage
	 * @author Alex Ng
	 * @param damage
	 */
	public void attacked(int damage)
	{
		if (sucsessfull(65)==true)
			lostHealth(damage);
	}
	
	/** Adds health to the mover
	 * @author Alex Ng
	 * @param amount
	 */
	public void gainedHealth(int amount)
	{
		setHealth(getHealth()+amount);
	}
	
	/** Returns the health of the mover
	 * @author Alex Ng
	 * @return int health
	 */
	public int getHealth()
	{
		return health;
	}
	
	/** Sets the maximum health of the mover
	 * @author Alex Ng
	 * @param size
	 */
	public void setFullHealth(int size)
	{
		if (size > 0)
			fullHealth = size;
	}
	
	/** Adds to the maximum amount of health for a mover
	 * @author Alex Ng
	 * @param amount
	 */
	public void addFullHealth(int amount)
	{
		setFullHealth(getFullHealth()+amount);
	}
	
	/** Sets the maximum amount of health for a mover to the default
	 * @author Alex Ng
	 */
	public void resetFullHealth()
	{
		setFullHealth(STARTHEALTH);
	}
	
	/** Returns the maximum health of a mover
	 * @author Alex Ng
	 * @return int fullHealth
	 */
	public int getFullHealth()
	{
		return fullHealth;
	}
}
