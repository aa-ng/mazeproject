import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/** Level object that has monsters, items, mazes, rooms, ect and 
 *  can contain players.
 * @author Alex Ng
 */
public class Level 
{
	/** Stores the maximum number of rooms
	 * @author Alex Ng
	 */
	private final int MAXROOMS = 5;
	
	/** Stores the size of the level on the x-axis
	 * @author Alex Ng
	 */
	private int xSize;
	
	/** Stores the size of the level on the y-axis
	 * @author Alex Ng
	 */
	private int ySize;
	
	/** Stores the images displayed on the level
	 * @author Alex Ng
	 */
	private final ImageIcon images[] = {new ImageIcon("Images/Levels/Wall.png"),
										new ImageIcon("Images/Levels/Floor.png"),
										new ImageIcon("Images/Levels/Background.png"),
										new ImageIcon("Images/Player/Player.png"),
										new ImageIcon("Images/Levels/Start.png"),
										new ImageIcon("Images/Levels/End.png"),
										new ImageIcon("Images/Monsters/Gargoyle.png"),
										new ImageIcon("Images/Levels/Chest.png")};
										
	
	/** Generates randomn numbers
	 * @author Alex Ng
	 */
	private Random random = new Random();
	
	/** Stores room objects to be placed on the map of the level
	 * @author Alex Ng
	 */
	private Room rooms[];
	
	/** Stores the perfect maze to be placed on the map of the level
	 * @author Alex Ng
	 */
	private PerfectMaze pMaze;
	
	/** Stores all the cells of the level as a map
	 * @author Alex Ng
	 */
	private Cell[][] map;
	
	/** Stores monsters on the level
	 * @author Alex Ng
	 */
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	
	/** Stores the chests on the level
	 * @author Alex Ng
	 */
	private ArrayList<Chest> chests;
	
	/** Stores the open cells on the level
	 * @ author Alex Ng
	 */
	private ArrayList<Cell> openCells = new ArrayList<Cell>();
	
	/** Stores the closed cells on the level
	 * @author Alex Ng
	 */
	private ArrayList<Cell> closedCells = new ArrayList<Cell>();
	
	/** Creates a new random level
	 * @author Alex Ng
	 * @param xSize
	 * @param ySize
	 */
	public Level(int xSize, int ySize)
	{
		setXSize(xSize);
		setYSize(ySize);
		initializeLevel();
		generateLevel();
	}
	
	/** Create a new level from a text document
	 * @author Alex Ng
	 * @param load
	 */
	public Level(TextRead load)
	{
		
	}
	
	/** Returns the distance between two cells
	 * @author Alex Ng
	 * @param cell1
	 * @param cell2
	 * @return int distance
	 */
	public int getDistance(Cell cell1, Cell cell2)
	{
		int a = 0;
		int x1 = cell1.getX();
		int y1 = cell1.getY();
		int x2 = cell2.getX();
		int y2 = cell2.getX();
		int b = Math.abs(x1-x2);
		int c = Math.abs(y1-y2);
		a = (int)Math.sqrt((Math.pow(b,2))+(Math.pow(c, 2)));
		return a;
		
	}
	
	/** Adds a random amount of items to the level
	 * @author Alex Ng
	 */
	public void addItems()
	{
		int numberOfChests;
		numberOfChests = random.nextInt(6);
		while (numberOfChests < 3)
		{
			numberOfChests = random.nextInt(6);
		}
		//chests = new Chest[numberOfChests];
		while (numberOfChests >= 0)
		{
			//chests[numberOfChests-1] = new Chest();
		}
	}
	
	/** Randomly places and generates a new monster on the level.
	 * @author Alex Ng
	 */
	public void generateMonster()
	{
		int x = random.nextInt(getXSize());
		int y = random.nextInt(getYSize());
		while (getMap()[x][y].getID().equals(".")==false || getDistance(getMap()[x][y], getPlayer())<5)
		{
			x = random.nextInt(getXSize());
			y = random.nextInt(getYSize());
		}
		monsters.add(new Monster(x,y));
		getMap()[x][y] = getMonster(x,y);
		getMap()[x][y].getLabel().setIcon(getMonsterIcon());
	}
	
	/** Returns the cell that stores the player on this level
	 * @author Alex Ng
	 * @return Cell player
	 */
	public Cell getPlayer()
	{
		for (int y = 1; y < getYSize()-1; y++)
		{
			for (int x = 1; x < getXSize()-1; x++)
			{
				if (getMap()[x][y].getID().equals("P"))
					return getMap()[x][y];
			}
		}
		return null;
	}
	
	public Cell getEndCell()
	{
		Cell end = null;
		for (int y = 0; y < getYSize(); y+=getYSize()-1)
		{
			for (int x = 0; x < getXSize(); x++)
			{
				if (getMap()[x][y].getID().equals("E"))
					return getMap()[x][y];
			}
		}
		return end;
	}
	
	/** Initializes all cells within the level
	 * @author Alex Ng
	 */
	public void initializeLevel()
	{
		map = new Cell[getXSize()][getYSize()];
		
		for (int y = 0; y < getYSize(); y++)
		{
			for (int x = 0; x < getXSize(); x++)
			{
				getMap()[x][y] = new Cell("*", new JLabel(images[2]),x,y); 
				openCells.add(new Cell("*", null, x, y));
			}
		}
	}
	
	/** Generates a new level
	 * @author Alex Ng
	 */
	public void generateLevel()
	{
		chests = new ArrayList<Chest>();
		pMaze = new PerfectMaze(getXSize(),getYSize());
		for (int y = 0; y < getYSize(); y++)
		{
			for (int x = 0; x < getXSize(); x++)
			{
				String id = pMaze.getMap()[x][y].getID();
				
				if (id.equals("*"))
					getMap()[x][y] = getWall(x,y);
				else if (id.equals("."))
					getMap()[x][y] = getBackground(x,y);
				else if (id.equals("S"))
					getMap()[x][y] = getStart(x,y);
				else if (id.equals("E"))
					getMap()[x][y] = getEnd(x,y);
				else if (id.equals("P"))
					getMap()[x][y] = getPlayer(x,y);
				else if (id.equals("C"))
				{
					getMap()[x][y] = getChest(x,y);
					chests.add(new Chest("C", x, y));
				}
			}
		}
	}
	
	/** Determines if there is space left for a room at certain coordinates
	 * @param room
	 * @return boolean true/false
	 */
	public boolean spaceLeft(Room room)
	{
		for (int y = 0; y < getYSize()-room.getHeight(); y++)
		{
				for (int x = 0; x < getXSize()-room.getWidth(); x++)
				{
					for (int pos = x; pos < room.getWidth(); pos++)
					{
						if (getMap()[pos][y].getID().equals("O")==false)	
							return false;
					}
				}
		}
		return true;
	}
	
	/** Determines if a room object is overlaping other constructs 
	 *  withing the map on the x-axis
	 *  @author Alex Ng
	 * @param check
	 * @param x
	 * @return boolean true/false
	 */
	public boolean overlapX(Room check, int x)
	{
		int saveX = x;
		for (int i = 0; i < check.getWidth()*2; i++)
		{
			for (int y = 0; y < getYSize(); y++)
			{
				if (getMap()[x][y].getID().equals("*")==false)
					return true;
			}
			if (i < check.getWidth())
				x++;
			else if (i == check.getWidth())
				x = saveX;
			else if (x-check.getWidth() > 0)
				x--;
		}
		return false;
	}
	
	/** Determines if a room object is overlaping other constructs
	 * 	within the map on the y-axis
	 * @author Alex Ng
	 * @param check
	 * @param y
	 * @return boolean true/false
	 */
	public boolean overlapY(Room check, int y)
	{
		int saveY = y;
		for (int i = 0; i < check.getHeight()*2; i++)
		{
			for (int x = 0; x < getXSize(); x++)
			{
				if (getMap()[x][y].getID().equals("*"))
					return true;
			}
		
			if (i < check.getHeight())
				y++;
			else if (i == check.getHeight())
				y = saveY;
			else if (y-check.getWidth() > 0) 
				y--;
		}
		return false;

	}
	
	/** Adds a room to the map of the level
	 * @author Alex Ng
	 * @param room
	 * @param x
	 * @param y
	 */
	public void addRoom(Room room, int x, int y)
	{
		int saveX = x;
		for (int column = 0; column < room.getHeight(); column++)
		{
			for (int row = 0; row < room.getWidth(); row++)
			{
				if (room.getRoom()[row][column].equals("X"))
					getMap()[x][y] = new Cell(room.getRoom()[row][column], new JLabel(images[0]),row,column);
				else if (room.getRoom()[row][column].equals("+"))
					getMap()[x][y] = new Cell(room.getRoom()[row][column], new JLabel(images[1]),row,column);
				x++;
			}
			x = saveX;
			y++;
		}
	}
	
	/** Prints the map of the level
	 * @author Alex Ng
	 */
	public void printLevel()
	{
		for (int y = 0; y < getYSize(); y++)
		{
			for (int x = 0; x < getXSize(); x++)
			{
				if (x == getXSize()-1)
					System.out.println(getMap()[x][y].getID());
				else
					System.out.print(getMap()[x][y].getID());
			}
		}
	}
	
	/** Saves the current location of monsters in the map 
	 *  on the map.
	 *  @author Alex Ng
	 */
	public void saveMonsters()
	{ 
		for (Monster monster : monsters)
		{
			int x = monster.getX();
			int y = monster.getY();
			getMap()[x][y] = monster.getCell(x,y);
		}
	}
	
	/** Returns the chests within the level
	 * @author Alex Ng
	 * @return ArrayList<Chest> chests
	 */
	public ArrayList<Chest> getChests()
	{
		return chests;
	}
	
	/** Sets a cell on the map of the level
	 * @author Alex Ng
	 * @param cell
	 * @param x
	 * @param y
	 */
	public void setCell(Cell cell, int x, int y)
	{
		getMap()[x][y] = cell;
	}
	
	/** Returns the map of the level
	 * @author Alex Ng
	 * @return Cell[][] map
	 */
	public Cell[][] getMap()
	{
		return map;
	}
	
	/** Sets the size of the level on the x-axis
	 * @author Alex Ng
	 * @param size
	 */
	public void setXSize(int size)
	{
		xSize = size;
	}
	
	/** Returns the current size of the level on the x-axis
	 * @author Alex Ng
	 * @return int xSize
	 */
	public int getXSize()
	{
		return xSize;
	}
	
	/** Sets the size of the level on the y-axis
	 * @author Alex ng
	 * @param size
	 */
	public void setYSize(int size)
	{
		ySize = size;
	}
	
	/** Returns the current size of the level on the y-axis
	 * @author Alex Ng
	 * @return int ySize
	 */
	public int getYSize()
	{
		return ySize;
	}
	
	/** Returns a wall ImageIcon
	 * @author Alex Ng
	 * @return ImageIcon wall
	 */
	public ImageIcon getWallIcon()
	{
		return images[0];
	}
	
	/** Returns a wall Cell
	 * @author Alex Ng
	 * @param x
	 * @param y
	 * @return Cell wall
	 */
	public Cell getWall(int x, int y)
	{
		return new Cell("*", new JLabel(getWallIcon()), x, y);
	}
	
	/** Returns a floor ImageIcon
	 * @author Alex Ng
	 * @return ImageIcon floor
	 */
	public ImageIcon getFloorIcon()
	{
		return images[1];
	}
	
	/** Returns a floor Cell 
	 * @author Alex Ng
	 * @param x
	 * @param y
	 * @return Cell floor
	 */
	public Cell getFloor(int x, int y)
	{
		return new Cell(".", new JLabel(getFloorIcon()), x, y);
	}
	
	/** Returns a background ImageIcon
	 * @author Alex Ng
	 * @return ImageIcon background
	 */
	public ImageIcon getBackgroundIcon()
	{
		return images[2];
	}
	
	/** Returns a background Cell
	 * @author Alex Ng
	 * @param x 
	 * @param y
	 * @return Cell background
	 */
	public Cell getBackground(int x, int y)
	{
		return new Cell(".", new JLabel(getBackgroundIcon()), x, y);
	}
	
	/** Returns a player ImageIcon
	 * @author Alex Ng
	 * @return ImageIcon player
	 */
	public ImageIcon getPlayerIcon()
	{
		return images[3];
	}
	
	/** Returns a player Cell
	 * @author Alex Ng
	 * @param x
	 * @param y
	 * @return Cell player
	 */
	public Cell getPlayer(int x, int y)
	{
		return new Cell("P", new JLabel(getPlayerIcon()), x, y);
	}
	
	/** Returns a start ImageIcon
	 * @author Alex Ng
	 * @return ImageIcon start
	 */
	public ImageIcon getStartIcon()
	{
		return images[4];
	}
	
	/** Returns a start cell
	 * @author Alex Ng
	 * @param x
	 * @param y
	 * @return Cell start
	 */
	public Cell getStart(int x, int y)
	{
		return new Cell("S", new JLabel(getStartIcon()), x, y);
	}
	
	/** Returns an end ImageIcon
	 * @author Alex Ng
	 * @return ImageIcon end
	 */
	public ImageIcon getEndIcon()
	{
		return images[5];
	}
	
	/** Returns an end Cell
	 * @author Alex Ng
	 * @param x
	 * @param y
	 * @return Cell end
	 */
	public Cell getEnd(int x, int y)
	{
		return new Cell("E", new JLabel(getEndIcon()), x, y);
	}
	
	/** Returns a monster ImageIcon
	 * @author Alex Ng
	 * @return ImageIcon monster
	 */
	public ImageIcon getMonsterIcon()
	{
		return images[6];
	}
	
	/** Returns a monster Cell
	 * @author Alex Ng
	 * @param x
	 * @param y
	 * @return Cell monster
	 */
	public Cell getMonster(int x, int y)
	{
		return new Cell("M", new JLabel(getMonsterIcon()), x, y);
	}
	
	public ImageIcon getChestIcon()
	{
		return images[7];
	}
	
	public Cell getChest(int x, int y)
	{
		return new Cell("C", new JLabel(getChestIcon()), x, y);
	}
	
	//public ImageIcon getPlayerLoose()
	{
		//return 
	}
}
