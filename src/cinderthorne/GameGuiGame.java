package cinderthorne;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class GameGuiGame extends GameGui {
	public TileMap tileMap;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public Entity playerEntity = null;
	
	public GameGuiGame(){
		tileMap = new TileMap();
		Tile[][] tiles = new Tile[10][10];
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[x].length; y++){
				tiles[x][y] = Tile.GRASS;
			}
		}
		tileMap.setTiles(tiles);
		
		playerEntity = new Entity(32);
		
		entities.add(playerEntity);
	}
	
	public void update(){
		boolean wDown = isKeyDown('w');
		boolean aDown = isKeyDown('a');
		boolean sDown = isKeyDown('s');
		boolean dDown = isKeyDown('d');
		if(wDown){
			playerEntity.attemptMove(0, -1, tileMap);;
		}
		if(aDown){
			playerEntity.attemptMove(-1, 0, tileMap);;
		}
		if(sDown){
			playerEntity.attemptMove(0, 1, tileMap);;
		}
		if(dDown){
			playerEntity.attemptMove(1, 0, tileMap);;
		}
	}
	
	@Override
	public void draw(Graphics2D g, int width, int height){
		int xOff = 0;
		int yOff = 0;
		
		xOff = width/2-playerEntity.getHitbox().width/2 - playerEntity.locX;
		yOff = height/2-playerEntity.getHitbox().height/2 - playerEntity.locY;
		
		tileMap.drawAllTiles(g, xOff,yOff);
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			Entity e = it.next();
			e.draw(g, xOff, yOff);
		}
	}
}
