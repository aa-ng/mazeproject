import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


/** Sorts thought data/ Organizes data into preferred formats
 * @author Alex Ng * Bill Ko
 *
 */
public class Sort 
{
	private final int WORDSSIZE = 100; 
	/** Stores all words.
	 * @author Alex Ng
	 */
	private ArrayList<String> words = new ArrayList<String>(); 
	
	/** Stores, moves thought and reads thought text documents
	 * 
	 */
	private static TextRead textRead;
	
	/** Creates a Sort Object
	 * @author Alex Ng
	 */
	public Sort()
	{
		loadwords(); 
	}
	
	/** Forms a word in a single String varaible from an array
	 * of letters
	 * @author Alex Ng
	 * @param selectedLetters
	 * @return String word
	 */
	public String formWord(ArrayList<String> selectedLetters)
	{
		String word = "";
		
		if (selectedLetters.size()<2)
			return null;
		for(String letter: selectedLetters)
		{
			word = word+letter;
		}
		
		return word;
	}
	
	/** Determines and returns if a string variable is a word
	 * @author Alex Ng
	 * @param string
	 * @return boolean true/false
	 */
	public boolean isAWord(String string)
	{
		for (String word : words)
		{
			if (string.equals(word))
				return true;
		}
		return false;
	}
	
	/** Loads words for dictionary from a text document
	 * @author Alex Ng
	 */
	private void loadwords()
	{
		textRead = new TextRead("Data/Words.txt", WORDSSIZE);
		for (int i = 0; i < WORDSSIZE; i++)
		{
			textRead.setFound();
			words.add(textRead.getFoundString());
		}
	}
	
	/** Prints out dictionary to java system console
	 * @author Bill Ko
	 */
	public void listWords()
	{
		for (String s : words)
		{
			System.out.println(s);
		}
	}
	
	/** Removes a word from the word list
	 * @author Alex Ng
	 * @param word
	 */
	public void removeWord(String word)
	{
		words.remove(word);
	}
	
	/** Adds a word to the word list text document
	 * @author Alex Ng
	 * @param word
	 */
	public void addWord(String word)
	{
		try 
		{
			PrintWriter printWrite = new PrintWriter("Data/Dictionary.txt");
			words.add(word);
			for (String s : words)
			{
				printWrite.println(s);
			}
			printWrite.println(word);
			printWrite.close();
		}
		catch (FileNotFoundException e) 
		{
			new MessageWindow("Error","Dictionary.txt missing");
			e.printStackTrace();
		}
	}
	
	/** Sorts numbers (int[]) in increasing order
	 * @author Alex Ng & Bill Ko
	 * @param numbers
	 * @param maxVal
	 * @return int numbers[]
	 */
	public int[] buckitSort(int numbers[], int maxVal) 
	{
	      int [] bucket=new int[maxVal+1];
	 
	      for (int i=0; i<bucket.length; i++) 
	      {
	         bucket[i]=0;
	      }
	 
	      for (int i=0; i<numbers.length; i++) 
	      {
	         bucket[numbers[i]]++;
	      }
	 
	      int outPos=0;
	      for (int i=0; i<bucket.length; i++) 
	      {
	         for (int j=0; j<bucket[i]; j++) 
	         {
	            numbers[outPos++]=i;
	         }
	      }
	      
	      return numbers;
	}
	
	/** Swaps two integers (int)
	 * @author Alex Ng
	 * @param letter1
	 * @param letter2
	 */
	public void swapInt(int num1, int num2)
	{
		int temp = num1;
		num1 = num2;
		num2 = temp;
	}
	
	/** Swaps two objects (Object)
	 * @author Alex Ng
	 * @param obj1
	 * @param obj2
	 */
	public void swapObject(Object obj1, Object obj2)
	{
		Object temp = obj1;
		obj1 = obj2;
		obj2 = temp;
	}
}
