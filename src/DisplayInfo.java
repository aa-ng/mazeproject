import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


/** Displays game related information to the user.
 * @author Alex Ng
 */
public class DisplayInfo extends JPanel
{
	/** Displays the type of information shown
	 * @author Alex Ng
	 */
	private final String descriptions[] = {"Level","Hunger","Health","Experience","Gold","Depth"};
	
	/** Amount of columns 
	 * @author Alex Ng
	 */
	private final int COLUMNSIZE = 12;
	
	/** Stores the game which data is displayed
	 * @author Alex Ng
	 */
	private Game game;
	
	/** Stores the player which data is displayed
	 * @author Alex Ng
	 */
	private Player player;
	
	/** Stores the graphical labels 
	 * @author Alex Ng
	 */
	private JLabel labels[] = new JLabel[COLUMNSIZE];
	
	/** Stores the values displayed
	 * @author Alex Ng
	 */
	private int values[] = new int[descriptions.length];
	
	
	
	/** Constructs a display information object
	 * @author Alex Ng
	 * @param game
	 * @param player
	 */
	public DisplayInfo(Game game, Player player)
	{
		setLayout(new GridLayout(1,COLUMNSIZE));
		setGame(game);
		setPlayer(player);
		loadValues();
		initializeLabels();
		refreshLabels();
	}
	
	/** Adds information to the labels in the display information object
	 * @author Alex Ng
	 */
	public void initializeLabels()
	{
		for (int x = 0; x < COLUMNSIZE/2; x++)
		{
			labels[x] = new JLabel(descriptions[x]);
			add(labels[x]);
			labels[x] = new JLabel(""+values[x]);
			add(labels[x]);
		}
	}
	
	/** Refreshes the labels in the display information object
	 * @author Alex Ng
	 */
	public void refreshLabels()
	{
		loadValues();
		for (int x = 0; x < COLUMNSIZE; x++)
		{
			if (x%2==0 && x < 6)
				labels[x].setText(""+values[x]);
		}
	}
	
	/** Loads information for the labels in the display information object
	 * @author Alex Ng
	 */
	public void loadValues()
	{
		//loads a player's current level
		values[0] = getPlayer().getLevel();
		//loads a player's current hunger
		values[1] = getPlayer().getHunger();
		//loads a player's current health
		values[2] = getPlayer().getHealth();
		//loads a player's current experience
		values[3] = getPlayer().getEXP();
		//loads a player's current amount of goals
		//values[4] = player.getGold();
		//loads a game's current level
		//values[5] = game.getLevel();
	}
	
	/** Sets the displayed hunger of the player
	 * @author Alex Ng
	 * @param hunger
	 */
	public void setHunger(int hunger)
	{
		values[1] = hunger;
		labels[1].setText(""+hunger);
	}
	
	/** Sets the displayed level of the game
	 * @author Alex Ng
	 * @param level
	 */
	public void setLevel(int level)
	{
		values[5] = level;
		labels[5].setText(""+level);
	}
	
	/** Sets the current player that is being displayed information about
	 * @author Alex Ng
	 * @param player
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	/** Returns the player that is being displayed information about
	 * @author Alex Ng
	 * @return Player player
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/** Sets the game that is being displayed information about
	 * @author Alex Ng
	 * @param game
	 */
	public void setGame(Game game)
	{
		this.game = game;
	}
	
	/** Returns the game that is being displayed information about 
	 * @author Alex Ng
	 * @return Game game
	 */
	public Game getGame()
	{
		return game;
	}
}
