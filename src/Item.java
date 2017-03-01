
/** A variety of different objects that have both good and bad possible effects on the player
 *  Items are stored in the players inventory and randomly located across levels.
 * @author Alex Ng
 */
public class Item 
{
	String name;
	int x;
	int y;
	
	/** Constructs a blank item
	 * @author Alex Ng
	 */
	public Item()
	{
		
	}
	
	/** Constructs a item with the following information
	 * @author Alex Ng
	 * @param name
	 */
	public Item(String name)
	{
		setName(name);
	}
	
	/** Constructs a item with the following information
	 * @author Alex Ng
	 * @param name
	 * @param x
	 * @param y
	 */
	public Item(String name, int x, int y)
	{
		setName(name);
		setX(x);
		setY(y);
	}
	
	/** Sets the name of the item
	 * @author Alex Ng
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	/** Sets the x-coordinate that the item is stored at
	 * @author Alex Ng
	 * @param x
	 */
	public void setX(int x)
	{
		this.x = x; 
	}
	
	/** Returns the x-coordinate that the item is stored at.
	 * @author Alex Ng
	 * @return
	 */
	public int getX()
	{
		return x; 
	}
	
	/** Sets the y-coordinate that the item is stored at
	 * @author Alex Ng
	 * @param y
	 */
	public void setY(int y)
	{
		this.y = y; 
	}
	
	/** Returns the y-coordinate that the item is stored at
	 * @author Alex Ng
	 * @return
	 */
	public int getY()
	{
		return y;
	}
}
