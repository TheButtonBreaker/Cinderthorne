package cinderthorne;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Rendering {
	public static final Dimension RESOLUTION = new Dimension(1152,720);
	
	public static GameGui gui = null;
	
	public static void doRendering(Graphics gee, JPanel p, Game game){
		if(gui == null){
			gui = new GameGuiStart();
		}
		
		Graphics2D g = (Graphics2D) gee;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int pwidth = p.getWidth();
		int pheight = p.getHeight();
		int width = RESOLUTION.width;
		int height = RESOLUTION.height;
		
		g.scale(pwidth/RESOLUTION.getWidth(), pheight/RESOLUTION.getHeight());
		g.setColor(Color.black);
		g.fillRect(0, 0, RESOLUTION.width, RESOLUTION.height);
		
		gui.draw(g,width,height);
	}


}
