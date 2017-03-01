import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

/** Countdown timer
 * @author Alex Ng
 */
public class CountDownTimer extends Timer
{   
    /** Stores seconds left
     * @author Alex Ng
     */
    private int seconds = 120;
    
    /** Stores count down duration in seconds
     * @author Alex Ng
     */
    private int countDown;
    
    /** Allocates a task upon time event
     * @author Alex Ng
     */
    private Task timerTask; 
    
    /** Stores if the timer is paused
     * @author Alex Ng
     */
    private boolean pause = true;
    
    /** Stores the amount of milliseconds in a second
     * @author Alex Ng
     */
    protected final static int MILLISECOND = 1000;
    
    /** Stores seconds in a minute
     * @author Alex Ng
     */
    protected final static int MINUTE = 60;
    
    /** Stores the amount of seconds in an hour
     * @author Alex Ng
     */
    protected final static int HOUR = 3600;
    
    /** Count down time constructor parameter is in seconds
     * @author Alex Ng
     * @param countDown
     */
    public CountDownTimer(int countDown)
    {
    	saveCountDown(countDown);
    	resetCountDown();
    	timerTask = new Task(); 
    	scheduleAtFixedRate(timerTask,1000,1000); 
    }
    
    /** Returns the seconds left
     * @author Alex Ng
     * @return int seconds
     */
    public int getSeconds()
    {
		return seconds;
    }
    
    /** Returns the minutes left
     * @author Alex Ng
     * @return int seconds/MINUTE
     */
    public int getMinutes()
    {
		return seconds/MINUTE;
    }
    
    /** Returns hours left
     * @author Alex Ng
     * @return int seconds/HOUR
     */
    public int getHours()
    {
		return seconds/HOUR;
    }
    
    /** Draws the time left in seconds at specified coordinates
     * on a JPanel
     * @author Alex Ng
     * @param g
     * @param x
     * @param y
     */
    public void drawTime(Graphics g,int x, int y)
    {
    	g.setColor(Color.WHITE);
    	g.drawString(""+seconds, x, y);
    }
    
    /** Sets if the timer is paused
     * @author Alex Ng
     * @param pause
     */
    public void setPause(boolean pause)
    {
    	this.pause = pause; 
    }
    
    /** toggles the state of the timer running
     * @author Alex Ng
     */
    public void togglePause()
    {
    	if (pause == true)
    		pause = false;
    	else 
    		pause = true;
    }
    
    /** Returns objects designated count down
     * @author Alex Ng
     * @return int countDown
     */
    public int getCountDown()
    {
    	return countDown;
    }
    
    /** Resets the time left to objects count down
     * @author Alex Ng
     */
    public void resetCountDown()
    {
    	setSeconds(getCountDown());
    }
    
    /** Sets the seconds left
     * @author Alex Ng
     * @param seconds
     */
    private void setSeconds(int seconds)
    {
    	this.seconds = seconds;
    }
    
    /** Saves the amount of seconds to count down from
     * @author Alex Ng
     * @param countDown
     */
    private void saveCountDown(int countDown)
    {
    	this.countDown = countDown;
    }
    
    /** Decrements time left while timer is running
     * @author Alex Ng
     */
    public class Task extends TimerTask
    {
    	public void run()
    	{
    		if (pause == false)
    			seconds-=1;
    	}
    }
}