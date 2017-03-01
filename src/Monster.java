import javax.swing.ImageIcon;
import javax.swing.JLabel;


/** Mover that tracks and attacks the player
 * @author Alex Ng
 */
public class Monster extends Mover 
{

	/** Creates a monster with the following information
	 * @author Alex Ng
	 * @param x
	 * @param y
	 */
	public Monster(double x, double y) 
	{
		super(x, y);
	}
	
	/** Returns a monster Cell
	 * @author Alex Ng
	 * @param x
	 * @param y
	 * @return Cell monster
	 */
	public Cell getCell(int x, int y)
	{
		return new Cell("M",new JLabel(new ImageIcon("Images/Monsters/Monster.png")), x, y);
	}
}
