package cinderthorne;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
		
		panel.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				Rendering.gui.mouseDown(arg0);
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Rendering.gui.mouseUp(arg0);
			}
		});
		panel.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent arg0) {
				Rendering.gui.mouseMoved(arg0);
			}
			@Override
			public void mouseMoved(MouseEvent arg0) {
				Rendering.gui.mouseMoved(arg0);
			}
		});
		
		frame.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				Rendering.gui.keyDown(arg0);
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				Rendering.gui.keyUp(arg0);
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				//Useless skrublord
			}
		});
		
		frame.setVisible(true);
		frame.requestFocus();
		
		TickUtil.startTicking();
	}

	public void update() {
		
	}
}
