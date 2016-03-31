package cinderthorne;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cinderthorne.util.RenderingUtil;

public class Game {
	public static boolean gameStarted = false;

	JFrame frame = null;
	public JPanel panel = null;

	public Game() {
		frame = new JFrame("Cinderthorne");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics gee) {
				RenderingUtil.doRendering(gee, this, Game.this);
			}
		};
		panel.setDoubleBuffered(false);
		RenderingUtil.setGui(new GameGuiStart(), frame, panel);
		frame.add(panel);
		frame.setIgnoreRepaint(true);
		//panel.setDoubleBuffered(true)
		//We don't add listeners here anymore since GameGuis are now listeners themselves.
		frame.setVisible(true);
		frame.requestFocus();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
	}

	public void update() {
		if(RenderingUtil.getGameGui() != null){
			RenderingUtil.getGameGui().update();
		}
	}
}
