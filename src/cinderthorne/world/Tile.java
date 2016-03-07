package cinderthorne.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Tile {
	public static final int TILESIZE = 32*2;
	
	public static HashMap<Integer, Tile> tiles = new HashMap<Integer, Tile>();
	public static HashMap<String, Tile> alibis = new HashMap<String, Tile>();
	private static int currentID = 1;
	
	public int id;
	public String name;
	public String alibi;
	
	public Tile(String name, String alibi){
		this.name = name;
		tiles.put(currentID, this);
		id = currentID;
		this.alibi = alibi;
		alibis.put(alibi, this);
		currentID++;
	}
	
	BufferedImage texture = null;
	boolean hasTexture = true;
	public void render(Graphics2D g, int x, int y, int width, int height){
		if(texture == null && hasTexture){
			String textureName = "Resources/tile "+name+".png";
			try {
				texture = ImageIO.read(new File(textureName));
			} catch (IOException e) {
				hasTexture = false;
				System.out.println(name + " doesn't have a texture! Looking for: " + textureName);
			}
		}
		
		g.drawImage(texture, x, y, width, height, null);
	}
	public static Tile getTile(int i) {
		return tiles.get(i);
	}
	
	public static Tile none = new Tile("none", "_");
	public static Tile woodPattern = new Tile("wood pattern", "w");
	public static Tile woodPlank = new Tile("wood plank","q");
}