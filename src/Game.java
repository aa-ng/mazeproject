import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;


/** Game object that implements game objects and features. 
 *  This object is also directly responsible for storing graphical
 *  elements to be displayed on the main window/frame. 
 * @author Alex Ng
 */
public class Game extends JPanel implements MouseListener, ActionListener, KeyListener
{
	/** Stores the pixels in a cell
	 * @author Alex Ng
	 */
	private final int CELLSIZE = 20;
	
	/** Stores the maze levels
	 * @author Alex Ng
	 */
	private ArrayList<Level> levels = new ArrayList<Level>();
	
	/** Stores player information
	 * @author Alex Ng
	 */
	private Player player;
	
	/** Displays player information
	 * @author Alex Ng
	 */
	private DisplayInfo display;
	
	/** Animation timer to activate the actionperformed method
	 * @author Alex Ng
	 */
	private Timer aTime = new Timer(0,this);
	
	/** The size of the window on the x-axis
	 * @author Alex Ng
	 */
	private int xSize;
	
	/** The size of the window on the y-axis
	 * @author Alex Ng
	 */
	private int ySize;
	
	/** The current level number of game
	 * @author Alex Ng
	 */
	private int currentLevel = 0; 
	
	/** The current dirction the player traveled to get to the current level
	 * @author Alex Ng
	 */
	private int direction = 0;
	
	/** Counts down from a given amount.
	 * 	used to determine when a monster should be spawned
	 * @author Alex Ng
	 */
	private CountDownTimer timer = new CountDownTimer(15);
	
	/** Constructs a standard game object
	 * @author Alex Ng
	 * @param xSize
	 * @param ySize
	 */
	public Game(int xSize, int ySize)
	{
		player = new Player(0,0);
		setXSize(xSize);
		setYSize(ySize);
		setLayout(new GridLayout(ySize, xSize));
		addLevel();
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		aTime.start();
		timer.togglePause();
	}
	
	/** Adds the current level to the panel
	 * @author Alex Ng
	 */
	public void addLevel()
	{
		if (direction!=-1)
			levels.add(new Level(getXSize(),getYSize()));
	
		for (int y = 0; y < getYSize(); y++)
		{
			for (int x = 0; x < getXSize(); x++)
			{
				if (levels.get(getLevel()).getMap()[x][y].getID().equals("P"))
				{
						player.setX(x);
						player.setY(y);
						display = new DisplayInfo(this,player);
				}
				if (direction == 0)
					add(levels.get(currentLevel).getMap()[x][y].getLabel());
				else 
					levels.get(0).getMap()[x][y].getLabel().setIcon(levels.get(currentLevel).getMap()[x][y].getLabel().getIcon());
			}
		}
	}
	
	/** Removes all cells from the current level
	 * @author Alex Ng
	 */
	public void removeLevel()
	{
		for (int y = 0; y < getYSize(); y++)
		{
			for (int x = 0; x < getXSize(); x++)
			{
				remove(levels.get(currentLevel).getMap()[x][y].getLabel());
			}
		}
	}
	
	/** Determines which way a player is exiting a level and 
	 *	loads the according level.
	 * @author Alex Ng
	 * @param cell
	 */
	public void exitLevel(Cell cell)
	{
		if (cell.getID().equals("S")&&getLevel()!=0)
			previousLevel();
		else if (cell.getID().equals("E"))
			nextLevel();
	}
	
	/**Returns the current level
	 * @author Alex Ng
	 * @return
	 */
	public int getLevel()
	{
		return currentLevel;
	}
	
	/** Sets the current level
	 * @param level
	 */
	public void setLevel(int level)
	{
		this.currentLevel = level;
	}
	
	/** Sets the current level to the next level.
	 * @author Alex Ng
	 */
	public void nextLevel()
	{
		setLevel(getLevel()+1);
		direction = 1;
		addLevel();
	}
	
	/** Sets the current level to the previous level
	 * @author Alex Ng
	 */
	public void previousLevel()
	{
		if (getLevel()>0)
		{
			setLevel(getLevel()-1);
			direction = -1;
			addLevel();
		}
	}
	
	/** Saves the current level to a text document
	 * @author Alex Ng
	 */
	public void saveLevel()
	{
		String level = ""+getLevel();
	}
	
	/** Swaps two cells on both the identifier grid and in the displayed grid
	 * @author Alex Ng
	 * @param cell1
	 * @param cell2
	 */
	public void swapCells(Cell cell1, Cell cell2)
	{
		Icon tempIcon = cell1.getLabel().getIcon();
		String tempID = cell1.getID();
		player.setX(cell1.getX());
		player.setY(cell1.getY());
		levels.get(0).getMap()[cell1.getX()][cell1.getY()].setID(cell2.getID());
		levels.get(0).getMap()[cell1.getX()][cell1.getY()].getLabel().setIcon(cell2.getLabel().getIcon());
		levels.get(0).getMap()[cell2.getX()][cell2.getY()].setID(tempID);
		levels.get(0).getMap()[cell2.getX()][cell2.getY()].getLabel().setIcon(tempIcon);
		levels.get(currentLevel).getMap()[cell1.getX()][cell1.getY()] = levels.get(0).getMap()[cell1.getX()][cell1.getY()];
		levels.get(currentLevel).getMap()[cell2.getX()][cell2.getY()] = levels.get(0).getMap()[cell2.getX()][cell2.getY()];
		
	}
	
	/** Responds to action events and spawns in monsters in designated times
	 * @author Alex Ng
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		display.refreshLabels();
		if (timer.getSeconds()==0)
		{
			//level.generateMonster();
			//timer.resetCountDown();
			//timer.setPause(false);
		}
		if (player.getHealth()==0)
		{
			//levels.get(0).getMap()[player.getX()][player.getY()].getLabel().setIcon(levels.get(0).getPlayerLoose());
		}
		repaint();
	}
	
	/** -W.I.P-
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event)
	{
	
	}

	/** -W.I.P-
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent event) 
	{
		
	}

	/** -W.I.P-
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent event)
	{
		
	}

	/** -W.I.P-
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent event) 
	{
		
	}

	/** -W.I.P-
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent event) 
	{
		
	}
	
	/** Returns the size of the game on the x-axis
	 * @author Alex Ng
	 * @return int xSize
	 */
	public int getXSize()
	{
		return xSize;
	}
	
	/** Sets the size of the game on the x-axis
	 * @author Alex Ng 
	 * @param size
	 */
	public void setXSize(int size)
	{
		xSize = size;
	}
	
	/** Returns the size of the game on the y-axis
	 * @author Alex Ng
	 * @return int ySize
	 */
	public int getYSize()
	{
		return ySize;
	}
	
	/** Sets the size of the game on the y-axis
	 * @author Alex Ng
	 * @param size
	 */
	public void setYSize(int size)
	{
		ySize = size;
	}
	
	/** Updates the level upon a keypress from the user. The following method 
	 *	determines the movement and actions intended by the user in relation 
	 *	to the players coordinates, health, hunger and current level.
	 * @author Alex Ng
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent event) 
	{
		display.refreshLabels();
		int x = player.getX();
		int y = player.getY();
		int dx = 0;
		int dy = 0;

		switch (event.getKeyCode())
		{
		//player wants to move left
		case KeyEvent.VK_LEFT:
			if (levels.get(currentLevel).getMap()[x-1][y].getID().equals("."))
				dx = -1;
			else if (levels.get(currentLevel).getMap()[x+1][y].getID().equals("S")||levels.get(currentLevel).getMap()[x+1][y].getID().equals("E"))
				exitLevel(levels.get(currentLevel).getMap()[x-1][y]);
			break;
		//player wants to move right
		case KeyEvent.VK_RIGHT:
			if (levels.get(currentLevel).getMap()[x+1][y].getID().equals("."))
				dx = 1;
			else if (levels.get(currentLevel).getMap()[x+1][y].getID().equals("S")||levels.get(currentLevel).getMap()[x-1][y].getID().equals("E"))
				exitLevel(levels.get(currentLevel).getMap()[x+1][y]);
			break;
		//player wants to move up
		case KeyEvent.VK_UP:
			if (levels.get(currentLevel).getMap()[x][y-1].getID().equals("."))
				dy = -1;
			else if (levels.get(currentLevel).getMap()[x][y-1].getID().equals("S")||levels.get(currentLevel).getMap()[x][y-1].getID().equals("E"))
				exitLevel(levels.get(currentLevel).getMap()[x][y-1]);
			break;
		//player wants to move down
		case KeyEvent.VK_DOWN:
			if (levels.get(currentLevel).getMap()[x][y+1].getID().equals("."))
				dy = 1;
			else if (levels.get(currentLevel).getMap()[x][y+1].getID().equals("S")||levels.get(currentLevel).getMap()[x][y+1].getID().equals("E"))
				exitLevel(levels.get(currentLevel).getMap()[x][y+1]);
			break;
		//player wants to see a solution
		case KeyEvent.VK_SPACE:
			//creates a new path objects 
			Path path = new Path(levels.get(currentLevel));
			//determines the start and end coordinates for the level
			int x1 = levels.get(currentLevel).getPlayer().getX();
			int y1 = levels.get(currentLevel).getPlayer().getY();
			int x2 = levels.get(currentLevel).getEndCell().getX();
			int y2 = levels.get(currentLevel).getEndCell().getY();
			//recursively backtracks to the end of the maze
			path.findPath(x1,y1,x2,y2);
			break;
		//player opens their inventory
		case KeyEvent.VK_I:
			//opens a inventory window
			new Window(new Inventory(player), "Inventory");
			break;
		//player opens a chest
		case KeyEvent.VK_C:
			//collects all of the players neighbor cells
			ArrayList<Cell> neighbors = levels.get(currentLevel).getMap()[x][y].neighborCells(levels.get(currentLevel).getMap(), getXSize(), getYSize());
			//loops though players neighbor cells
			for (int neighbor = 0; neighbor < neighbors.size(); neighbor++)
			{
				//if a chest has been opened
				if (neighbors.get(neighbor).getID().equals("C"))
				{
					//collects all chests within the level
					ArrayList<Chest> chests = levels.get(currentLevel).getChests();
					//loops though all chests on the current level
					for (int chest = 0; chest < chests.size(); chest++)
					{
						//determines which chest was clicked
						if (levels.get(currentLevel).getMap()[chests.get(chest).getX()][chests.get(chest).getY()].getID().equals("C"))
						{
							//gets the items from the chets
							ArrayList<Item> items = chests.get(chest).getItems();
							//loops though items
							for (int item = 0; item < items.size(); item++)
							{
								//adds the items to the player
								player.pickupItem(items.get(item));
							}
							//clears the chest
							chests.get(chest).getItems().clear();
						}
					}
				}
			}
			break;
		}
		
		if (dx != 0 || dy != 0)
		{
			display.setHunger(player.getHunger());
			display.setLevel(getLevel());
			if (player.getHunger()==0)
				player.lostHealth(1);
			player.decrementHunger();
			swapCells(levels.get(currentLevel).getMap()[x+dx][y+dy], levels.get(currentLevel).getMap()[x][y]);
		}
	}

	/** -W.I.P-
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent event) 
	{
		
	}

	/** -W.I.P-
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent event) 
	{
		
	}
	
	/** Returns the Player object for the instance of the game
	 * @return
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/** Returns the Display information object for the instance of the game
	 * @author Alex Ng
	 * @return
	 */
	public DisplayInfo getDisplay()
	{
		return display;
	}
}
