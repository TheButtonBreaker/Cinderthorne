package cinderthorne;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Rendering {
	private static Font cinderFont = null;
	
	public static final Dimension RESOLUTION = new Dimension(1152,720);
	
	private static GameGui gui = null;
	
	static double rot;
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
		
		if(game.map != null){
			game.map.drawAllTiles(g, game, RESOLUTION);
		}
		if(!Game.gameStarted){
			g.setFont(createCinderFont(0,100));
			g.setColor(Color.white);
			String str = "Cinderthorne";
			int strwidth = g.getFontMetrics().stringWidth(str);
			g.rotate(rot += 0.01, width/2+50, height/2+50);
			g.drawString(str, RESOLUTION.width/2-strwidth/2, g.getFontMetrics().getHeight()+20);
			g.drawRect(width/2, height/2, 100, 100);
		}
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
