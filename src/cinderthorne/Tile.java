package cinderthorne;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	public String name;
	public BufferedImage texture;
	
	public Tile(String string) {
		this.name = string;
		try {
			texture = ImageIO.read(new File("Resources/"+name+".png"));
		} catch (IOException e) {
			texture = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
			e.printStackTrace();
		}
	}
	
	public static final int SIZE = 32;
	public static final Tile GRASS = new Tile("Grass");


	public void drawTile(Graphics2D g, Point drawPoint, int xID, int yID){ //The 2 IDs are for later
		g.drawImage(texture, drawPoint.x, drawPoint.y, SIZE, SIZE, null);
	}
}
