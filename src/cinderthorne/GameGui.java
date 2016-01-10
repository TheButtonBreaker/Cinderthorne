package cinderthorne;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class GameGui {
	protected static HashMap<Character,Boolean> downKeys = new HashMap<Character,Boolean>();
	public void draw(Graphics2D g, int width, int height){
	}
	public void mouseDown(MouseEvent arg0) {
	}
	public void mouseUp(MouseEvent arg0) {
	}
	public void mouseMoved(MouseEvent arg0) {
	}
	public void keyDown(KeyEvent arg0) {
		downKeys.put(arg0.getKeyChar(), true);
	}
	public void keyUp(KeyEvent arg0) {
		downKeys.put(arg0.getKeyChar(), false);
	}
	public void update() { // Done before 'draw' is called
	}
	public boolean isKeyDown(char key){
		boolean defaultVal = false;
		
		try{
			return downKeys.get(key);
		}catch(Exception e){
			downKeys.put(key, false);
			return defaultVal;
		}
	}
}
