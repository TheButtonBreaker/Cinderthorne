package cinderthorne;

import java.awt.Graphics2D;

public class GameGuiGame extends GameGui {
	public TileMap tileMap;
	
	public GameGuiGame(){
		tileMap = new TileMap();
		Tile[][] tiles = new Tile[10][10];
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[x].length; y++){
				tiles[x][y] = Tile.GRASS;
			}
		}
		tileMap.setTiles(tiles);
	}
	
	@Override
	public void draw(Graphics2D g, int width, int height){
		tileMap.drawAllTiles(g, 0,0);
	}
}
