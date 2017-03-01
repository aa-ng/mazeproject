import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;


/** Cell that stores items within the maze
 * @author Alex Ng
 */
public class Chest extends Cell
{
	/** Stores the items inside the chest
	 * @author Alex Ng
	 */
	private ArrayList<Item> items = new ArrayList<Item>();
	
	/** Generates random numbers 
	 * @author Alex Ng
	 */
	private Random random = new Random();
	
	/** The maximum amount of items a chest can store
	 * @author Alex Ng
	 */
	protected int MAXNUMBEROFITEMS = 4;
	
	/** Chest Constructor
	 * @author Alex Ng
	 * @param id
	 * @param label
	 * @param x
	 * @param y
	 */
	public Chest(String id, int x, int y) 
	{
		super(id, null, x, y);
		randomItems();
	}
	
	/** Randomly adds items to the chest
	 * @author Alex Ng
	 */
	public void randomItems()
	{
		int numberOfItems = random.nextInt(MAXNUMBEROFITEMS)+1;
		int temp = 0;
		while (numberOfItems > 0)
		{
			items.add(new Potion());
			System.out.println(items.get(temp).getName());
			temp++;
			items.add(new Food());
			System.out.println(items.get(temp).getName());
			temp++;
			numberOfItems--;
			
		}
	}
	
	/** Returns the items stored in a chest
	 * @author Alex Ng
	 * @return ArrayList<Item> items
	 */
	public ArrayList<Item> getItems()
	{
		return items;
	}
}
