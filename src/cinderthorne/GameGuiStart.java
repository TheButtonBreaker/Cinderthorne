package cinderthorne;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class GameGuiStart extends GameGui {
	private static Font cinderFont = null;
	static double rot;
	
	public GameGuiStart(){
		createFont();
	}
	
	public void draw(Graphics2D g, int width, int height){
		Dimension RESOLUTION = Rendering.RESOLUTION;
		g.setFont(createCinderFont(0,120));
		g.setColor(Color.white);
		String str = "Cinderthorne";
		int strwidth = g.getFontMetrics().stringWidth(str);
		g.rotate(rot, width/2+50, height/2+50);
		g.drawString(str, RESOLUTION.width/2-strwidth/2, g.getFontMetrics().getHeight()+20);
		
		str = "Press any key to start";
		g.setFont(createCinderFont(0,30));
		strwidth = g.getFontMetrics().stringWidth(str);
		g.drawString(str, RESOLUTION.width/2-strwidth/2, height/2+g.getFontMetrics().getHeight()+20);
	}
	
	@Override
	public void keyDown(KeyEvent k){
		Rendering.gui = new GameGuiGame();
	}
	
	public static Font createCinderFont(int style, float size){
		return cinderFont.deriveFont(style).deriveFont(size);
	}
	
	public static void createFont() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/Cinderthorne Font.ttf"));
			ge.registerFont(font);
			cinderFont = font;
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}
}
