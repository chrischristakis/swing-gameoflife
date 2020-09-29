import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements Commons, ActionListener
{

	private static final long serialVersionUID = 1L;

	private JButton[] buttons = new JButton[2];
	
	private Core c;
	
	public ButtonPanel(Core c)
	{
		this.c = c;
		setLayout(new FlowLayout());
		setBackground(Color.GRAY);
		init();
	}
	
	public void init()
	{
		buttons[0] = new JButton("Unpause");
		buttons[1] = new JButton("Instructions");
		
		for(JButton b : buttons)
		{
			add(b);
			b.addActionListener(this);
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == buttons[0])
			if(!c.isPaused())
			{
				c.setPaused(true);
				buttons[0].setText("Unpause");
			}
			else
			{
				c.setPaused(false);
				buttons[0].setText("Pause");
			}
		if(e.getSource() == buttons[1])
		{
			JOptionPane.showMessageDialog(c, "Instructions:"
											+ "\nTo convert cells, game should be paused."
											+ "\nLeft click to ressurect cell, right click to kill a cell that is alive."
											+ "\n\nGame rules: Black cell = Alive, White cell = dead"
											+ "\nIn this example below, 'n' means it is a neighbor of the center cell, 'c'.\n\n"
						  					+ "n n n\n"
											+ "n c n\n"
											+ "n n n\n\n"
											+ "\n-A cell will die if it has less than 2 or more than 3 neighboring cells"
											+ "\n-If a dead cell has exactly 3 neighbors, it will become alive.");
		}
	}

}
