import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CellPanel extends JPanel implements Commons, MouseListener, MouseMotionListener
{
	
	private static final long serialVersionUID = 1L;

	private Cell[][] population;
	
	public CellPanel()
	{
		setPreferredSize(new Dimension(WWIDTH, WHEIGHT));
		setBackground(Color.DARK_GRAY.brighter() );
		addMouseListener(this);
		addMouseMotionListener(this);
		init();
	}
	
	public void init()
	{
		population = new Cell[RCELLS][CCELLS];
		
		for(int i = 0; i < population.length; i++)
			for(int j = 0; j < population[i].length; j++)
				population[i][j] = new Cell(CELLDIM * i, CELLDIM * j, CELLDIM, CELLDIM);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i < population.length; i++)
			for(int j = 0; j < population[i].length; j++)
				population[i][j].render(g);
	}
	
	public void update()
	{
		for(int i = 0; i < population.length; i++)
		{
			for(int j = 0; j < population[i].length; j++)
			{
				population[i][j].queueDead(true);
				if(population[i][j].isDead())
				{
					if(i > 0 && j > 0 && i < RCELLS - 1 && j < CCELLS - 1)
					{
						int neighbors = 0;
						for(int k = -1; k < 2; k++)
						{
							if(!population[i + k][j - 1].isDead())
								neighbors++;
							if(!population[i + k][j + 1].isDead())
								neighbors++;
						}
						if(!population[i - 1][j].isDead())
							neighbors++;
						if(!population[i + 1][j].isDead())
							neighbors++;
						
						if(neighbors == 3)
							population[i][j].queueDead(false);
					}
				}
				else
				{
					if(i > 0 && j > 0 && i < RCELLS - 1 && j < CCELLS - 1)
					{
						int neighbors = 0;
						for(int k = -1; k < 2; k++)
						{
							if(!population[i + k][j - 1].isDead())
								neighbors++;
							if(!population[i + k][j + 1].isDead())
								neighbors++;
						}
						
						if(!population[i - 1][j].isDead())
							neighbors++;
						if(!population[i + 1][j].isDead())
							neighbors++;
				
						if(neighbors > 3 || neighbors < 2)
							population[i][j].queueDead(true);
						else
							population[i][j].queueDead(false);
					}
				}
			}
		}
		
		for(int i = 0; i < population.length; i++)
			for(int j = 0; j < population.length; j++)
				if(i > 0 && j > 0 && i < RCELLS - 1 && j < CCELLS - 1)
					if(population[i][j].queuedDead())
						population[i][j].setDead(true);
					else
						population[i][j].setDead(false);
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		double mx = e.getX() / CELLDIM;
		double my = e.getY() / CELLDIM;
		
			if(e.getButton() == MouseEvent.BUTTON1) //LEFT CLICK == ALIVE
				population[(int)mx][(int)my].setDead(false);
				
			if(e.getButton() == MouseEvent.BUTTON3) //RIGHT CLICK == DEAD
				population[(int)mx][(int)my].setDead(true);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		double mx = e.getX() / CELLDIM;
		double my = e.getY() / CELLDIM;
	
		if(SwingUtilities.isLeftMouseButton(e)) 
				population[(int)mx][(int)my].setDead(false);
				
		if(SwingUtilities.isRightMouseButton(e)) //RIGHT CLICK == DEAD
				population[(int)mx][(int)my].setDead(true);
	}
	
	//------UNUSED METHODS------//
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
