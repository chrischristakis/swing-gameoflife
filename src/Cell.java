import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Cell extends Rectangle
{
	
	private final int SPACER = 1;
	
	private boolean dead,
					queueDead;
	
	public Cell(int x, int y, int w, int h)
	{
		setBounds(x, y, w, h);
		dead = true; //Initially, all cells are dead until brought to life by the user.
	}
	
	public void render(Graphics g)
	{
		if(dead)
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.BLACK);
		
		g.fillRect(x + SPACER, y + SPACER, width - SPACER, height - SPACER);
	}
	
	public boolean isDead()
	{
		return dead;
	}
	
	public void setDead(boolean dead)
	{
		this.dead = dead;
	}
	
	public boolean queuedDead()
	{
		return queueDead;
	}
	
	public void queueDead(boolean queueDead)
	{
		this.queueDead = queueDead;
	}

}
