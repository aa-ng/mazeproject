import java.util.ArrayList;

import javax.swing.JLabel;


/** Cell object that makes it easy to store multiple pieces of information in one array
 * 	for the cell grid
 * @author Alex Ng
 */
public class Cell 
{
	/** Stores the graphical label of the cell
	 * @author Alex Ng
	 */
	private JLabel label;
	
	/** Stores the identifier for the cell
	 * @author Alex Ng
	 */
	private String id;
	
	/** Stores the x-coordinate of the cell
	 * @author Alex Ng
	 */
	private int x;
	
	/** Stores the y-coordinate of the cell
	 * @author Alex Ng
	 */
	private int y;
	
	/** Stores if the cell has been visited
	 * @author Alex Ng
	 */
	private boolean visited;
	
	/** Creates a new cell and stores given information
	 * @author Alex Ng
	 * @param id
	 * @param label
	 * @param x
	 * @param y
	 */
	public Cell(String id, JLabel label, int x, int y)
	{
		setID(id);
		setLabel(label);
		setX(x);
		setY(y);
	}
	
	/** Returns the cell's neighbors in grids/maps 
	 *  in the following order {up,left,right,down}
	 * @author Alex Ng
	 * @param map
	 * @param xSize
	 * @param ySize
	 * @return ArrayList<Cell> neighbors
	 */
	public ArrayList<Cell> neighborCells(Cell[][] map, int xSize, int ySize)
	{
		//stores all neighbors
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		//left neighbor translation
		int left = getX()-1;
		//right neighbor translation
		int right = getX()+1;
		//up neighbor translation
		int up = getY()-1;
		//down neighbor translation
		int down = getY()+1;
		
		//cycles though all cells
		for (int y = 0; y < ySize; y++)
		{
			for (int x = 0; x < xSize; x++)
			{
				//if the current map cell matches any of the translated cells save it aproprietly
				//also ignores coordinates outside the level
				if (map[x][y].getX() == left && map[x][y].getY() == getY() && left >= 0 )
					neighbors.add(map[x][y]);
				if (map[x][y].getX() == right && map[x][y].getY() == getY() && right < xSize)
					neighbors.add(map[x][y]);
				if (map[x][y].getX() == getX() && map[x][y].getY() == up && up >= 0)
					neighbors.add(map[x][y]);
				if (map[x][y].getX() == getX() && map[x][y].getY() == down && down < ySize)
					neighbors.add(map[x][y]);
			}
		}
		//returns found neighbours
		return neighbors;
	}
	
	/** Sets the location on the x-axis
	 * @author Alex Ng
	 * @param x
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/** Gets the location on the x-axis
	 * @author Alex Ng
	 * @return int x
	 */
	public int getX()
	{
		return x;
	}
	
	/** Sets the location on the y-axis
	 * @author Alex Ng
	 * @param y
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/** Gets the location on the y-axis
	 * @author Alex Ng
	 * @return int y 
	 */
	public int getY()
	{
		return y;
	}
	
	/**Sets the cell's label
	 * @author Alex Ng
	 * @param label
	 */
	public void setLabel(JLabel label)
	{
		this.label = label;
	}
	
	/** Gets the cell's label
	 * @author Alex Ng
	 * @return JLabel label
	 */
	public JLabel getLabel()
	{
		return label;
	}
	
	/** Sets the identifier for the cell
	 * @author Alex Ng
	 * @param id
	 */
	public void setID(String id)
	{
		this.id = id;
	}
	
	/** Returns the identifier used for the cell
	 * @author Alex Ng
	 * @return String identifier (id)
	 */
	public String getID()
	{
		return id;
	}
	
	/** Sets if the cell has been visited
	 * @author Alex Ng
	 * @param walkable
	 */
	public void setVisited(boolean walkable)
	{
		this.visited = walkable;
	}
	
	/** Returns if the cell has been visited
	 * @author Alex Ng
	 * @return boolean true/false
	 */
	public boolean getVisited()
	{
		return visited;
	}
}
