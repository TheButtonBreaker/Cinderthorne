package cinderthorne.world.gamesection;

import java.awt.Graphics2D;
import java.io.File;

import cinderthorne.world.Tile;
import cinderthorne.world.WorldUtil;

public class GameSection {
	public int[][] tiles = null;
	
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
}
