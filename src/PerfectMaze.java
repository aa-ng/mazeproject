import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;


/** Random Maze with 1 wide paths
 * @author Alex Ng
 *
 */
public class PerfectMaze 
{
	private int xSize;
	private int ySize;
	
	private Cell[][] map;
	private boolean accesible;
	
	/** Creates a perfect maze with the given sizes
	 * @author Alex Ng
	 * @param xSize
	 * @param ySize
	 */
	public PerfectMaze(int xSize, int ySize)
	{
		map = new Cell[xSize][ySize];
		setXSize(xSize);
		setYSize(ySize);
		generateMaze();
	}
	
	/** Generates the maze
	 * @author Alex Ng
	 */
	public void generateMaze()
	{
		initializeMaze();
		growingTree();
		placeChests();
		placeStart();
		placeEnd();
	}
	
	/** places the start of the maze randomly on either the top or bottom sides
	 * @author Alex Ng
	 */
	public void placeStart()
	{
		Random random = new Random();
		//random x value
		int x = random.nextInt(getXSize());
		//random percent
		int randomNum = random.nextInt(100)+1;
		//random binary choice between top or bottom of maze (50/50)
		int y = 0;
		if (randomNum > 50)
			y = 0;
		else 
			y = getYSize()-1;
		//stores the direct neighbors of the random cell
		ArrayList<Cell> neighbours;
		//stores the direct adjacent neighbors of each neighbor
		ArrayList<Cell> adjacents;
		boolean open = false;
		
		//Repeats unless the cell is valid, which means it is open and not a pathway
		while(map[x][y].getID().equals(".")||open==false)
		{
			//stores if all necessary neighbors of the randomly chosen coordinate are open
			open = false;
			//random x value
			x = random.nextInt(getXSize());
			//determines the neighbors of the random cell
			neighbours = map[x][y].neighborCells(getMap(), getXSize(), getYSize());
			//cycles though the neighbors of the random cell
			for (int neighbour = 0; neighbour < neighbours.size(); neighbour++)
			{
				//if the neighbor is a pathway it is open and vice versa
				if (neighbours.get(neighbour).getID().equals("."))
					open = true;
				else 
					open = false;
				//if the neighbor is a pathway/is open then continue
				if (open == true)
				{
					//determine the adjacent Cells of the open neighbor
					adjacents = neighbours.get(neighbour).neighborCells(getMap(), getXSize(), getYSize());
					//cycle though the adjacents Cells of the open neighbor
					for (Cell adjacent : adjacents)
					{
						//if a adjacent of the open neighbor is also a pathway/open this is a valid location and exit the loop 
						if (adjacent.getID().equals("."))
						{
							open = true;
							break;
						}
						//vice versa
						else
							open = false;
					}
				}
				//if both a neighbor and a adjacent are open this is a valid location and exit the loop
				if (open == true)
					break;
			}
		}
		//valid location has been found
		//place the player 
		placePlayer(x,y);
		//mark the start of the maze on the map
		map[x][y].setID("S");
	}
	
	/** Returns the distance between two cells
	 * @author Alex Ng
	 * @param cell1
	 * @param cell2
	 * @return int distance
	 */
	public int getDistance(Cell cell1, Cell cell2)
	{
		//total distance between cells
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
	
	/** Places the end/exit of a maze randomly at the top or bottom sides.
	 * @author Alex Ng
	 */
	public void placeEnd()
	{
		//mostly same as start need to - implement distance - 
		Random random = new Random();
		int x = random.nextInt(getXSize());
		int randomNum = random.nextInt(100)+1;
		int y = 0;
		if (randomNum > 50)
			y = 0;
		else 
			y = getYSize()-1;
		ArrayList<Cell> neighbours;
		ArrayList<Cell> adjacents;
		boolean open = false;
		
		while(map[x][y].getID().equals(".")||open==false || getDistance(getStart(),map[x][y])<10) 
		{
			open = false;
			x = random.nextInt(getXSize());
			neighbours = map[x][y].neighborCells(getMap(), getXSize(), getYSize());
			for (int neighbour = 0; neighbour < neighbours.size(); neighbour++)
			{
				if (neighbours.get(neighbour).getID().equals("."))
					open = true;
				else 
					open = false;
				
				if (open == true)
				{
					adjacents = neighbours.get(neighbour).neighborCells(getMap(), getXSize(), getYSize());
					for (Cell adjacent : adjacents)
					{
						if (adjacent.getID().equals("."))
						{
							open = true;
							break;
						}
						else
							open = false;
					}
				}
				if (open == true)
					break;
			}
		}
		map[x][y].setID("E");
	}
	
	public Cell getStart()
	{
		for (int y = 0; y < getYSize(); y++)
		{
			for (int x = 0; x < getXSize(); x++)
			{
				if (map[x][y].getID().equals("S"))
					return map[x][y];
			}	
		}
		return null;
	}
	
	/** Places Chests on the maze
	 * @author Alex Ng
	 */
	public void placeChests()
	{
		Random random = new Random();
		int amount = random.nextInt(5)+1;
		while (amount > 0)
		{
			int x = random.nextInt(getXSize());
			int y = random.nextInt(getYSize());
			boolean accessible = false;
			ArrayList<Cell> neighbors = map[x][y].neighborCells(getMap(), getXSize(), getYSize());
			while (map[x][y].getID().equals("*")==false || accessible == false)
			{
				accessible = false;
				x = random.nextInt(getXSize());
				y = random.nextInt(getYSize());
				neighbors = map[x][y].neighborCells(getMap(), getXSize(), getYSize());
				for (int neighbor = 0; neighbor < neighbors.size(); neighbor++)
				{
					if (map[x][y].getID().equals("*")&&neighbors.get(neighbor).getID().equals("."))
					{
						accesible = true;
						break;
					}
				}
				if (accesible == true)
					break;
			}
			map[x][y].setID("C");
			printMaze();
			amount--;
		}
	}
	
	/** Places the player randomly adjacently to give coordinates
	 * @author Alex Ng
	 * @param x
	 * @param y
	 */
	public void placePlayer(int x, int y)
	{
		ArrayList<Cell> neighbors = map[x][y].neighborCells(map, getXSize(), getYSize());
		
		for (Cell neighbor : neighbors)
		{
			if (neighbor.getID().equals("."))
			{
				map[neighbor.getX()][neighbor.getY()].setID("P");
				return;
			}
		}
	}
	
	/** Initializes the maze and all cells within the maze
	 * @author Alex Ng
	 */
	public void initializeMaze()
	{
		Random random = new Random();
		for (int y = 0; y < getYSize(); y++)
		{
			for (int x = 0; x < getXSize(); x++)
			{
				map[x][y] = new Cell("*", new JLabel(), x, y);
			}
		}
	}
	
	/** Generates a maze with the growing tree algorithm
	 * @author Alex Ng
	 */
	public void growingTree()
	{
		//stores all the carvable cells
		ArrayList<Cell> totalCells = new ArrayList<Cell>();
		//loops through carvable cells
		for (int y = 1; y < getYSize()-1; y++)
		{
			for (int x = 1; x < getXSize()-1; x++)
			{
				totalCells.add(map[x][y]);
			}
		}
		//stores the carved cells
		ArrayList<Cell> tree = new ArrayList<Cell>();
		//stores the neighbors of the current cell
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		//stores the adjacents of all the neighbor cells
		ArrayList<Cell> adjacents = new ArrayList<Cell>();
		//stores the current cell
		Cell currentCell;
		//random object
		Random random = new Random();
		//stores the x coordinate
		int x;
		//stores the y coordinate
		int y;
		//stores random index value for carvable cells
		int randomIndex;
		
		//pick a random cell
		randomIndex = random.nextInt(totalCells.size());
		//save that random cell
		currentCell = totalCells.get(randomIndex);
		//add that random cell to a list of carved cells
		tree.add(currentCell);
		//save the coordinate of that random cell
		x = currentCell.getX();
		y = currentCell.getY();
		//carve into cell
		map[x][y].setID(".");	
		//repeat while the list of carved cells/tree is not empty and the currentlySelected cell still has neighbors
		while (tree.size()>0 && currentCell.neighborCells(map, getXSize(), getYSize()).size() > 0)
		{
			//if there is still cells in the tree currentCell becomes the mosgt recent carved cell
			if (tree.size()>0)
				currentCell = tree.get(tree.size()-1);
			//neighbors become the neighbors of the currentCell
			neighbours = currentCell.neighborCells(map, getXSize(), getYSize());
			//cycle though the neighbors
			for (int neighbour = 0; neighbour < neighbours.size(); neighbour++)
			{
				//adjacents become the adjacent cells to the current neighbor cycle
				adjacents = neighbours.get(neighbour).neighborCells(map, getXSize(), getYSize());
				//counts the number of carvable cells
				int carvable = 0;
				//loops though the adjacent cells of the current neighbor cycle
				for (Cell adjacent : adjacents)
				{
					//if the adjacent is a wall it is potentially carvable so add it to the count
					if (adjacent.getID().equals("*"))
						carvable++;
				}
				//if there are three potentially carvable adjacents in one neighbor that is carvable, that neighbor is carvable
				if (carvable >= 3 && neighbours.get(neighbour).getID().equals("*"))
				{
					//current cell is equal to one of its random neighbors that is carvable
					currentCell = randomNeighbour(neighbours);
					//add that random neighbor/ currentCell to the list of carved cells
					tree.add(currentCell);
					//save the coordinates of the random neighbor/currentCell
					x = currentCell.getX();
					y = currentCell.getY();
					//carve into the random neighbor/currentCell
					map[x][y].setID(".");
					//exit loop/stop checking neighbors of the old currentCell 
					break;
				}
				//if a neighbor doesnt have 3 carvable adjacents remove the currentCell
				else
					tree.remove(currentCell);
			}
		}
	}
	
	/** Returns a valid random carvable neighbor from a given list of neighbors
	 * @param neighbors
	 * @return Cell neighbor
	 */
	public Cell randomNeighbour(ArrayList<Cell> neighbors)
	{
		Cell neighbour;
		ArrayList<Cell> adjacents;
		Random random = new Random();
		int index;
		int carvable;
		
		index = random.nextInt(neighbors.size());
		neighbour = neighbors.get(index);
		
		adjacents = neighbour.neighborCells(map, getXSize(), getYSize());
		carvable = 0;
		for (Cell adjacent : adjacents)
		{
			if (adjacent.getID().equals("*"))
				carvable++;
		}
		
		while (neighbour.getID().equals("*")==false || carvable < 3)
		{
			index = random.nextInt(neighbors.size());
			neighbour = neighbors.get(index);
			adjacents = neighbour.neighborCells(map, getXSize(), getYSize());
			carvable = 0;
			for (Cell adjacent : adjacents)
			{
				if (adjacent.getID().equals("*"))
					carvable++;
			}
		}
		return neighbour;
	}
	
	/** Prints the maze
	 * @author Alex Ng
	 */
	public void printMaze()
	{
		//loops though all y-values
		for (int y = 0; y < getYSize(); y++)
		{
			//loops though all x-values
			for (int x = 0; x < getXSize(); x++)
			{
				//not last id in row
				if (x < getXSize()-1)
					System.out.print(map[x][y].getID());
				//last id in row
				else
					System.out.println(map[x][y].getID());
			}
		}
	}
	
	/** Returns a map of the maze
	 * @author Alex Ng
	 * @return Cell[][] map
	 */
	public Cell[][] getMap()
	{
		return map;
	}
	
	/** Sets the size of the maze on the y-axis
	 * @author Alex Ng
	 * @param size
	 */
	public void setYSize(int size)
	{
		ySize = size;
	}
	
	/** Returns the size of the maze on the y-axis
	 * @author Alex Ng
	 * @return int ySize
	 */
	public int getYSize()
	{
		return ySize;
	}
	
	/** Sets the size of the maze on the x-axis
	 * @author Alex Ng
	 * @param size
	 */
	public void setXSize(int size)
	{
		xSize = size;
	}
	
	/** Returns the size of the maze on the x-axis
	 * @author Alex Ng
	 * @return int xSize
	 */
	public int getXSize()
	{
		return xSize;
	}
}
