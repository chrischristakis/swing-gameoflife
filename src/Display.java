import javax.swing.JFrame;

public class Display extends JFrame
{

	private static final long serialVersionUID = 1L;

	public Display(String TITLE, Core c)
	{
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(c);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
