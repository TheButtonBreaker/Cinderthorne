package cinderthorne;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

public abstract class GameGui implements KeyListener, MouseMotionListener, MouseListener {
	protected static HashMap<Integer,Boolean> downKeys = new HashMap<Integer,Boolean>();
	protected static HashMap<Integer,Boolean> downButtons = new HashMap<Integer,Boolean>();
	protected static int mouseX = 0;
	protected static int mouseY = 0;
	
	public void mousePressed(MouseEvent arg0){
		downButtons.put(arg0.getButton(), true);
	}
	public void mouseReleased(MouseEvent arg0){
		downButtons.put(arg0.getButton(), false);
	}
	public void mouseMoved(MouseEvent arg0){
		mouseX = arg0.getX();
		mouseY = arg0.getY();
	}
	
	public void keyPressed(KeyEvent arg0) {
		downKeys.put(arg0.getKeyCode(), true);
	}
	public void keyReleased(KeyEvent arg0) {
		downKeys.put(arg0.getKeyCode(), false);
	}
	
	public abstract void update(); // Done before 'draw' is called
	public abstract void draw(Graphics2D g, int width, int height);
	
	public boolean isKeyDown(int vkS){
		boolean defaultVal = false;
		try{
			return downKeys.get(vkS);
		}catch(Exception e){
			downKeys.put(vkS, false);
			return defaultVal;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		//wow, useless
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//wow, also useless
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//could you get more useless
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		//evidently you can
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}
}
