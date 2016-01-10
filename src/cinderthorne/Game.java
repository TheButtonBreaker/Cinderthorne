package cinderthorne;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	public TileMap map = null;

	public static boolean gameStarted = false;

	JFrame frame = null;
	JPanel panel = null;

	public Game() {
		frame = new JFrame("Cinderthorne");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics gee) {
				Rendering.doRendering(gee, this, Game.this);
			}
		};
		frame.add(panel);
		frame.setIgnoreRepaint(true);
		//panel.setDoubleBuffered(true);
		frame.setVisible(true);
		frame.requestFocus();
		
		
//		Timer timer = new Timer(16, new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				panel.repaint();
//			}
//		});
//		timer.start();
		TickUtil.startTicking();
	}

	public void update() {
		
	}
}
