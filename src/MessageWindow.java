import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/** Window that displays a message to user
 * @author Alex Ng
 */
public class MessageWindow extends JFrame implements ActionListener
{
	/** Stores display message and also is used to display the message
	 * @author Alex Ng
	 */
	JLabel message;
	
	/** Button that closes window
	 * @author Alex Ng
	 */
	JButton close;
	
	/** Stores standard design language
	 * @author Alex Ng
	 */
	protected final static int WINDOWORGIN = 0;
	protected final static int WINDOWXSIZE = 300;
	protected final static int WINDOWYSIZE = 125;
	protected final static int LABELWIDTH = 230;
	protected final static int LABELHEIGHT = 50;
	protected final static int BUTTONHEIGHT = 25;
	protected final static int BUTTONWIDTH = 75;
	protected final static int MARGIN = 10;
	protected final static Font COOLFONT = new Font("cool font", 2, 15);
	
	/** Constructs the a standard display window
	 * @author Alex Ng
	 * @param title
	 * @param message
	 */
	public MessageWindow(String title, String message)
	{
		super(title);
		setLayout(null);
		setSize(WINDOWXSIZE, WINDOWYSIZE);
		this.message = new JLabel(message);
		close = new JButton("Okay");
		addButton(this, close);
		addLabel(this, this.message);
		setResizable(false);
		setVisible(true);
	}
	
	/** Adds message/label to a Frame in standard procedure
	 * @author Alex Ng
	 * @param frame
	 * @param label
	 */
	private void addLabel(JFrame frame, JLabel label)
	{
		label.setFont(COOLFONT);
		add(label);
		label.setBounds(WINDOWXSIZE/2-LABELWIDTH/2, WINDOWORGIN, LABELWIDTH,LABELHEIGHT );
	}
	
	/** Adds a button in standard procedure
	 * @author Alex Ng
	 * @param frame
	 * @param button
	 */
	private void addButton(JFrame frame, JButton button)
	{
		button.addActionListener(this);
		add(button);
		button.setBounds(WINDOWXSIZE/2-BUTTONWIDTH/2, WINDOWYSIZE-MARGIN*4-BUTTONHEIGHT,BUTTONWIDTH, BUTTONHEIGHT);
	}
	
	/** Closes window upon triggering an action event
	 * @author Alex Ng
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event)
	{
		dispose();
	}
}
