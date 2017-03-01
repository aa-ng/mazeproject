import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame 
{
	/** Stores the icon of the window
	 * @author Alex Ng
	 */
	private Image icon = new ImageIcon("Images/Icon.jpeg").getImage();
	private final static String TITLE = "Maze project";
	
	//standard window data
	protected final int CELLSIZE = 20;
	protected final int WINDOWORGIN = 0;
	protected final int WINDOWMARGIN = 5;
	
	public Window(JPanel panel, String title)
	{
		super(title);
		setIconImage(icon);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		setVisible(true);
		setSize(650,600);
	}
	
	/** Constructs a game window
	 * @author Alex Ng
	 * @param game
	 * @param display
	 */
	public Window(Game game, DisplayInfo display)
	{
		super(TITLE);
		setIconImage(icon);
		setLayout(null);
		add(game);
		game.setBounds(WINDOWORGIN,CELLSIZE,(game.getXSize()*CELLSIZE),(game.getYSize()*CELLSIZE));
		add(display);
		display.setBounds(WINDOWORGIN,WINDOWORGIN,(game.getXSize()*CELLSIZE),CELLSIZE);
		setVisible(true);
		setSize((game.getXSize()*CELLSIZE)+WINDOWMARGIN,((game.getYSize()+2)*CELLSIZE)+WINDOWMARGIN);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
