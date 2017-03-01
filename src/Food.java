import java.util.Random;


/** Food Object that is an item that players collect to replenish their hunger and 
 *  stay alive.
 * @author Alex Ng
 */
public class Food extends Item
{
	/** Stores food names 
	 * @author Alex Ng
	 */
	private final String[] foodNames = {"Burger","Taco","Apple","Pie"};
	
	/**Stores amounts of hunger replenished from eating foods
	 * @author Alex Ng 
	 */
	private final int[] replenishAmounts = {50,100,25,75};
	private int replenishAmount;
	
	/** Constructs a random food object
	 * @author Alex Ng
	 */
	public Food()
	{
		super();
		setName(randomFood());
	}
	
	/** Constructs a foor object with the following information
	 * @author Alex Ng
	 * @param name
	 */
	public Food(String name)
	{
		super(name);
	}
	
	/** Constructs a food object with the following information
	 * @author Alex Ng
	 * @param name
	 * @param x
	 * @param y
	 */
	public Food(String name, int x, int y)
	{
		super(name, x, y);
	}
	
	/**
	 * @author Alex Ng
	 * @return String name
	 */
	public String randomFood()
	{
		String name = null;
		Random random = new Random();
		int index = random.nextInt(foodNames.length);
		name = foodNames[index];
		replenishAmount = replenishAmounts[index];
		
		return name;
	}
}
