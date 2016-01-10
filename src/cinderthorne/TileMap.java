package cinderthorne;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public class TileMap {
	private Tile[][] tiles = null;
	
	public TileMap(){
		this(0,0);
	}
	
	public TileMap(int width, int height){
		this(new Tile[width][height]);
	}
	
	public TileMap(Tile[][] tiles){
		this.setTiles(tiles);
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public Tile getTile(int x, int y){
		return tiles[x][y];
	}
	
	public void drawAllTiles(Graphics2D g, Game game, Dimension resolution) {
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[x].length; y++){
				Tile t = getTile(x,y);
				if(t!=null){
					tiles[x][y].drawTile(g, new Point(x*Tile.SIZE,y*Tile.SIZE), x, y);
				}
			}
		}
	}
}
