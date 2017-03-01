import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/** Basic text document reader
 * @author Alex Ng
 */
public class TextRead 
{ 	
	/** Stores text document location
	 * @author Alex Ng
	 */
	String path;
	
	/** Stores found information
	 * @author Alex Ng
	 */
	static String found; 
	
	/** Stores the amount of lines in text document
	 * @author Alex Ng
	 */
	static int size;
	
	/** Scans text document
	 * @author Alex Ng
	 */
	static Scanner input;
	
	/**initializes information needed about a text document to read a text file
	 * @author Alex Ng
	 * @param path
	 * @param size
	 */
	public TextRead(String path, int size)
	{
		if (this.path==null)
		{
			try
			{
				input = new Scanner(new File(path));
			}
			catch (FileNotFoundException e) 
			{
				System.err.print("No file found: "+path);
			} 
			setSize(size); 
			setPath(path);
			
		}	
	}
	
	/** Sets the amount of lines
	 * @author Alex Ng
	 * @param size
	 */
	public void setSize(int size) 
	{
		this.size = size; 
	}

	/**set the found information 
	 * @author Alex Ng
	 */
	public static void setFound() 
	{
		if (size>0)
		{
			size--;
			found = input.nextLine();
		}
	}
	/**Returns found information in String format
	 * @author Alex Ng
	 * @return (String)found
	 */
	public String getFoundString()
	{
		return found;		
	}
	
	/**Returns found information in integer format
	 * @author Alex Ng
	 * @return (int)found
	 */
	public int getFoundInt()
	{
		return Integer.parseInt(found);
	}
	
	/**Saves path of text document
	 * @author Alex Ng
	 * @param path
	 */
	private void setPath(String path) 
	{
		this.path=path;
	}
	
	/** Returns the path of the document 
	 * @author Alex Ng
	 * @return String path
	 */
	public String getPath()
	{
		return path;
	}
}
