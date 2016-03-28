package cinderthorne.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Tile {
	public static final int TILESIZE = 32 * 2;

	public static HashMap<Integer, Tile> tiles = new HashMap<Integer, Tile>();
	public static HashMap<String, Tile> alibis = new HashMap<String, Tile>();
	private static int currentID = 1;

	public HashMap<String,Object> properties = new HashMap<String,Object>();
	
	public int id;
	public String name;
	
	public Tile(String name) {
		init(name);
	}
	
	public static void loadAllTiles(ArrayList<String> lines){
		Tile currentTile = null;
		for(int i = 0; i < lines.size(); i++){
			String line = lines.get(i);
			if(line != null && !line.equals("")){
				if(line.startsWith(">")){
					currentTile = new Tile(line.substring(1));
				}else if(currentTile != null){
					String[] params = line.split("=");
					currentTile.properties.put(params[0], params[1]);
				}
			}
		}
	}
	
	private void init(String name){
		this.name = name;
		tiles.put(currentID, this);
		id = currentID;
		currentID++;
	}

	BufferedImage texture = null;
	boolean hasTexture = true;

	public boolean isDefaultWall(){
		return (boolean)properties.get("isWall").equals("true");
	}
	
	public void render(Graphics2D g, int x, int y, int width, int height) {
		if (texture == null && hasTexture) {
			String textureName = "Resources/Tile/" + name + ".png";
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

	public static Tile getTileFromName(String name) {
		Tile t;
		for (int i = 1; i < tiles.size() + 1; i++) {
			t = tiles.get(i);
			if (t.name.toLowerCase().equals(name)) {
				return t;
			}
		}
		return null;
	}

//	public static Tile dirtBlack = new Tile("BlackDirt",false);
//	public static Tile dirtBlackMushrooms = new Tile("BlackDirtMushrooms",false);
//	public static Tile dirtBlackRedMushrooms = new Tile("BlackDirtRedMushrooms",true);

}
