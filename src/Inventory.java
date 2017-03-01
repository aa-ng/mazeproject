import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/** Displays a players inventory
 * @author Alex Ng
 */
public class Inventory extends JPanel implements ActionListener
{
	/** Stores the display of the items
	 * @author Alex Ng
	 */
	ArrayList<JLabel> labels = new ArrayList<JLabel>();
	
	/** Stores the images displayed
	 * @author Alex Ng
	 */
	ImageIcon images[] = {new ImageIcon("Images/Items/BasePotion.png")};
	
	/** Stores the player thats inventory is displayed
	 * @author Alex Ng
	 */
	Player player;
	
	/** Closes the window
	 * @author Alex Ng
	 */
	JButton close = new JButton("Close");
	
	/** Inventory constructor
	 * @author Alex Ng
	 * @param player
	 */
	public Inventory(Player player)
	{
		setPlayer(player);
		setLayout(null);
		setLabels();
		addClose();
	}
	
	/** Sets the labels for the players inventory
	 * @author Alex Ng
	 */
	public void setLabels()
	{
		int x = 50;
		int y = 50;
		for (int i = 0; i < player.getInventory().length; i++)
		{
			labels.add(new JLabel(player.getInventory()[i].getName()));
			if (player.getInventory()[i] instanceof Potion)
				labels.get(i).setIcon(images[0]);
			add(labels.get(i));
			labels.get(i).setBounds(x,y,x+150,y+100);
			if (i % 4 != 0 && i != 0)
				x+=150;
			else 
			{
				y+=75;
				x = 50;
			}
		}
	}
	
	public void addClose()
	{
		add(close);
		close.addActionListener(this);
		close.setBounds(400, 20, 150, 40);
	}
	
	/** Sets the inventory's player
	 * @author Alex Ng
	 * @param player
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	/** -W.I.P-
	 * @author Alex Ng
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		
	}

	/** Handles action events
	 * @author Alex Ng
	 * @param event
	 */
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		JFrame app = (JFrame) this.getTopLevelAncestor();
		app.dispose();
	}
}
