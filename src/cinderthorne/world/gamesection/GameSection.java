package cinderthorne.world.gamesection;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import cinderthorne.util.WorldUtil;
import cinderthorne.world.Tile;

public class GameSection {
	public int[][] tiles = null;
	public boolean boundsAreWalls = false;
	public HashMap<String,Boolean> tileWalls = new HashMap<String,Boolean>();
	public boolean defaultWalls = true;
	public ArrayList<Rectangle> polygonWalls;
	
	public GameSection(String name){
		WorldUtil.loadGameSectionFromFile(new File("Resources/lvl/"+name+".txt"),this);
	}

	public void update() {
	}

	public void render(Graphics2D g, int xOffset, int yOffset) {
		if(tiles != null){
			for(int y = 0; y < tiles.length; y++){
				for(int x = 0; x < tiles[y].length; x++){
					Tile t = Tile.getTile(tiles[y][x]);
					if(t != null){
						int i = Tile.TILESIZE;
						t.render(g, x*i+xOffset, y*i+yOffset, i, i);
					}
				}
			}
		}
	}

	public int[][] getTiles() { // I'm wanting to shift everything from directly accessing the tiles variable to using this method eventually
		return tiles;
	}

	public void addPolygonWall(Rectangle r) {
		if(polygonWalls == null){
			polygonWalls = new ArrayList<Rectangle>();
		}
		polygonWalls.add(r);
	}
}
