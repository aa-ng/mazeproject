
/** Displays and implements the game
 * @author Alex Ng
 */
public class GUI 
{
	Window window;
	Game game;
	
	/** Constructs a Graphical user interface and all the implementations behind them.
	 * @author Alex Ng
	 */
	public GUI()
	{
		game = new Game(50,30);
		window = new Window(game, game.getDisplay());
		window.add(game);
		window.add(game.getDisplay());
	}
}
