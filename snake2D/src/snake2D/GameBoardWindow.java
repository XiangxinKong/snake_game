package snake2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.accessibility.Accessible;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
*
* @author Mohammed.Talaat (mtala3t)
* @version 1.0
*/
@SuppressWarnings("serial")
public class GameBoardWindow extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newGameMenuItem ;
	private JMenuItem exitGameMenuItem;

	public GameBoardWindow(int mapnumber, int level, int color, int mode) {

		setTitle("Snake2D Remake - 404");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 5, 1100, 700);
		setResizable(false);

		add(new GameBoardPanel(mapnumber, level, color, mode));//容器添加

		newGameMenuItem = new JMenuItem("New Game");//顶层菜单
		exitGameMenuItem = new JMenuItem("Exit");

		fileMenu = new JMenu("File");
		fileMenu.add(newGameMenuItem);
		fileMenu.add(exitGameMenuItem);

		menuBar = new JMenuBar();
		menuBar.add(fileMenu);

		setJMenuBar(menuBar);

		newGameMenuItem.addActionListener(this);//继承自Abstract button，接收ActionListener
		exitGameMenuItem.addActionListener(this);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		Object source = event.getSource();

		if (source == newGameMenuItem) {
			dispose();
			new MainScreen();
		}

		if (source == exitGameMenuItem) {
			System.exit(0);
		}

	}
}
