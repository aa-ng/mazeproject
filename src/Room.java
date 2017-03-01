import java.util.ArrayList;
import java.util.Random;

/** Room object that can be placed in levels
 * @author Alex Ng
 */
public class Room 
{
	/** Stores the smallest x-coordinate of the room
	 * @author Alex Ng
	 */
	private int x;
	
	/** Stores the smallest y-coordinate of the room
	 * @author Alex Ng
	 */
	private int y;
	
	/** Stores the width of the room
	 * @author Alex Ng
	 */
	private int width;
	
	/** Stores the height of the room
	 * @author Alex Ng
	 */
	private int height;
	
	/**
	 * @author Alex Ng
	 */
	private Random random = new Random();
	
	//constant game data
	protected static final int MINWIDTH = 5;
	protected static final int MINHEIGHT = 5;
	protected static final int MAXWIDTH = 10;
	protected static final int MAXHEIGHT = 10;
	
	/** Stores the map of the room
	 * @author Alex Ng
	 */
	private String map[][];
	
	/** Random Room constructor
	 * @author Alex Ng
	 */
	public Room()
	{
		generateSize();
		generateRoom();
	}
	
	/** Room constructor with the following infromation
	 * @author Alex Ng
	 * @param width
	 * @param height
	 */
	public Room(int width, int height)
	{
		setWidth(width);
		setHeight(height);
		generateRoom();
	}
	
	/** Loads a room from a text document
	 * @param load
	 */
	public Room (TextRead load)
	{
		
	}
	
	/** Generates random sizes within standard game data boundaries
	 * @author Alex Ng
	 */
	public void generateSize()
	{
		int randomWidth = random.nextInt(MAXWIDTH);
		while (randomWidth < MINWIDTH)
		{
			randomWidth = random.nextInt(MAXWIDTH);
		}
		setWidth(randomWidth);
		
		int randomHeight = random.nextInt(MAXHEIGHT);
		while (randomHeight < MINHEIGHT)
		{
			randomHeight = random.nextInt(MAXHEIGHT);
		}
		setHeight(randomHeight);
	}
	
	/** Generates a room
	 * @author Alex Ng
	 */
	public void generateRoom()
	{
		map = new String[getWidth()][getHeight()];
		for (int y = 0; y < getHeight(); y++)
		{
			for (int x = 0; x < getWidth(); x++)
			{
				map[x][y] = getBlock(x,y);
			}
		}
	}
	
	/** Returns the block in the room in the specified coordinates
	 * @author Alex Ng
	 * @param x
	 * @param y
	 * @return String block
	 */
	public String getBlock(int x, int y)
	{
		if (x == getWidth()-1 || x == 0 || y == 0 || y == getHeight()-1)
			return "X";
		else 
			return "F";
	}
	
	/** Sets the height of the room
	 * @author Alex Ng
	 * @param height
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	/** Returns the heigh of the room
	 * @author Alex Ng
	 * @return int height
	 */
	public int getHeight()
	{
		return height;
	}
	
	/** Sets the width of the room
	 * @author Alex Ng
	 * @param width
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	/** Returns the width of the room
	 * @author Alex Ng
	 * @return int width
	 */
	public int getWidth()
	{
		return width;
	}
	
	/** Returns the map of the room
	 * @author Alex Ng
	 * @return String[][] map
	 */
	public String[][] getRoom()
	{
		return map;
	}
	
	/** Returns the smallest x-coordinate of the room
	 * @author Alex Ng
	 * @return int x
	 */
	public int getX()
	{
		return x;
	}
	
	/** Sets the smallest x-coordinate of the room
	 * @author Alex Ng
	 * @param x
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/** Returns the smallest y-coordinate of the room
	 * @author Alex Ng
	 * @return int y
	 */
	public int getY()
	{
		return y;
	}
	
	/** Sets the smallest y-coordinate of the room
	 * @author Alex Ng
	 * @param y
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/** Prints the map of the room
	 * @author Alex Ng
	 */
	public void printRoom()
	{
		for (int y = 0; y < getHeight(); y++)
		{
			for (int x = 0; x < getWidth(); x++)
			{
				if (x == getWidth()-1)
					System.out.println(map[x][y]);
				else
					System.out.print(map[x][y]);
			}
		}
	}
}
