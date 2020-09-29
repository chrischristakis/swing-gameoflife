import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class Core extends JComponent implements Runnable
{

	/**
	 * Made by Chris Christakis on 11/07/17 inspired by the original Game of Life by John Conway.
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean paused = true;
	
	private boolean running;
	
	private CellPanel cp;
	
	private ButtonPanel bp;
	
	private Thread t;
	
	public Core()
	{
		init();
		new Display("Game of Life", this);
	}
	
	public void init()
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		cp = new CellPanel();
		bp = new ButtonPanel(this);
		
		add(cp);
		add(bp);
		
		t = new Thread(this);
		running = true;
		t.start();
	}
	
	public void update()
	{
		cp.update();
	}
	
	public void render()
	{
		cp.repaint();
	}
	
	@Override
	public void run()
	{
		while(running)
		{
			if(!paused)
			{
				update();
				try { Thread.sleep(140); } catch (InterruptedException e) { e.printStackTrace();	}
			}
			else
				try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace();	} //Allows more responsive drawing.
			render();
		}
	}
	
	public void setPaused(boolean paused)
	{
		this.paused = paused;
	}
	
	public boolean isPaused()
	{
		return paused;
	}
	
	public static void main(String[] args) 
	{
		new Core();
	}

}
