import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/** Determines paths from a starting point to the end.
 * @author Alex Ng
 */
public class Path 
{
	private Level level;
	private Cell start;
	private Cell end;
	private ArrayList<Cell> path;
	private ArrayList<Cell> solve;
	private String[][] maze;
	
	/** Creates a path object with a level
	 * @param level
	 */
	public Path(Level level)
	{
		setLevel(level);
		saveMaze();
	}
	
	/** Creates a path object with a start, end and level
	 * @param level
	 * @param start
	 * @param end
	 */
	public Path(Level level, Cell start, Cell end)
	{
		setLevel(level);
		setStart(start);
		setEnd(end);
	}
	
	public void setLevel(Level level)
	{
		this.level = level;
	}
	
	public void setEnd(Cell cell)
	{
		end = cell;
	}
	
	public Cell getEnd()
	{
		return end;
	}
	
	public void setStart(Cell cell)
	{
		start = cell;
	}
	
	public Cell getStart()
	{
		return start;
	}
	
	/** Recursively pathfinds to a goal cell from a path cell
	 * @author Alex Ng
	 * @param currentCell
	 * @param goal
	 * @return
	 */
	public void findPath(int x1, int y1, int x2, int y2)
	{
		if ((x2 >= maze.length && y2 >= maze.length) || (x2 < 0 && y2 < 0))
			printMaze();
		if (x1 == x2 && y1 == y2 || maze[x1][y1].equals("g"))
		{
			maze[x1][y1] = "f";
			//System.exit(1);
			/*
			try 
			{
				throw new Exception();
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			traceText();
			return;
		}
		if (maze[x1-1][y1].equals(".")&&x1-1>=0)
		{
			maze[x1-1][y1] = "x";
			traceText();
			findPath(x1-1,y1,x2,y2);
		}
		else if (maze[x1+1][y1].equals(".")&&x1+1<level.getXSize())
		{
			maze[x1+1][y1] = "x";
			traceText();
			findPath(x1+1,y1,x2,y2);
		}
		else if (y1-1>=0&&maze[x1][y1-1].equals("."))
		{
			maze[x1][y1-1] = "x";
			traceText();
			findPath(x1,y1-1,x2,y2);
		}
		if (y1+1<level.getYSize()&&maze[x1][y1+1].equals("."))
		{
			maze[x1][y1+1] = "x";
			traceText();
			findPath(x1,y1+1,x2,y2);
		}
		else if (maze[x1-1][y1].equals("x")&&x1-1 >= 0)
		{
			maze[x1][y1] = "b";
			maze[x1-1][y1] = "x";
			traceText();
			findPath(x1-1,y1,x2,y2);
		}
		else if (maze[x1+1][y1].equals("x") && x1+1 < level.getXSize())
		{
			maze[x1][y1] = "b";
			maze[x1+1][y1] = "x";
			traceText();
			findPath(x1+1,y1,x2,y2);
		}
		else if (y1-1 >= 0 && maze[x1][y1-1].equals("x"))
		{
			maze[x1][y1] = "b";
			maze[x1][y1-1] = "x";
			traceText();
			findPath(x1,y1-1,x2,y2);
		}
		else if (y1+1 < level.getYSize() && maze[x1][y1+1].equals("x"))
		{
			maze[x1][y1] = "b";
			maze[x1][y1+1] = "x";
			traceText();
			findPath(x1,y1+1,x2,y2);
		}
		else if (x1-1>=0 && maze[x1-1][y1].equals("s"))
		{
			maze[x1][y1] = "b";
			maze[x1-1][y1] = "s";
			findPath(x1-1,y1,x2,y2);
		}
		else if (x1+1<level.getXSize()&&maze[x1+1][y1].equals("s"))
		{
			maze[x1][y1] = "b";
			maze[x1+1][y1] = "s";
			traceText();
			findPath(x1+1,y1,x2,y2);
		}
		else if (y1-1>=0&&maze[x1][y1-1].equals("s"))
		{
			maze[x1][y1] = "b";
			maze[x1][y1-1] = "s";
			traceText();
			findPath(x1,y1-1,x2,y2);
		}
		else if (y1+1<level.getYSize()&&maze[x1][y1+1].equals("s"))
		{
			maze[x1][y1] = "b";
			maze[x1][y1+1] = "b";
			traceText();
			findPath(x1,y1+1,x2,y2);
		}	
		printMaze();
	}
	
	/** Saves a maze from the current level
	 * @author Alex Ng
	 */
	public void saveMaze()
	{
		maze = new String[level.getXSize()][level.getYSize()];
		for (int y = 0; y < level.getYSize(); y++)
		{
			for (int x = 0; x < level.getXSize();x++)
			{
				if (level.getMap()[x][y].getID().equals(".") || level.getMap()[x][y].getID().equals("*"))
					maze[x][y] = level.getMap()[x][y].getID();
				else
					maze[x][y] = level.getBackground(x, y).getID();
			}
		}
	}
	
	/** Prints the maze
	 * @author Alex Ng
	 */
	public void printMaze()
	{
		System.out.println();
		for (int y =0; y < level.getYSize(); y++)
		{
			for (int x = 0; x < level.getXSize(); x++)
			{
				if (x < maze.length-1)
					System.out.print(maze[x][y]);
				else 
					System.out.println(maze[x][y]);
			}
		}
	}
	
	int step = 0;
	/** Saves all steps to solve maze to seperate Text Documents
	 * @author Alex Ng
	 */
	public void traceText()
	{
		try 
		{
			PrintWriter printWriter = new PrintWriter("Saves/save"+step+".txt");
			for (int y =0; y < level.getYSize(); y++)
			{
				for (int x = 0; x < level.getXSize(); x++)
				{
					if (x < maze.length-1)
						printWriter.print(maze[x][y]);
					else 
						printWriter.println(maze[x][y]);
				}
				
			}
			printWriter.close();
			step++;
		} 
		catch (IOException e) 
		{
			new MessageWindow("Error","/Saves is missing");
			e.printStackTrace();
		}
	}
	
	/** Returns the amount of cells stored in the path
	 * @return
	 */
	public int getDistanceOfPath()
	{
		return path.size();
	}
}
