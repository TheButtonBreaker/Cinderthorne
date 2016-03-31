package cinderthorne.world.gamesection;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import cinderthorne.entity.Entity;
import cinderthorne.util.WorldUtil;
import cinderthorne.world.Door;
import cinderthorne.world.Tile;

public class GameSection {
	public int[][] tiles = null;
	public boolean boundsAreWalls = false;
	public HashMap<String, Boolean> tileWalls = new HashMap<String, Boolean>();
	public boolean defaultWalls = true;
	public ArrayList<Rectangle> polygonWalls;
	public HashMap<String, Door> doors = new HashMap<String, Door>();

	public int nullTile = 2;
	public String name;

	public GameSection(String name) {
		this.name = name;
		WorldUtil.loadGameSectionFromFile(new File("Resources/lvl/" + name + ".txt"), this);
	}

	public void update() {
	}

	public void render(Graphics2D g, int xOffset, int yOffset, int width, int height) {
		if(tiles != null){
			int i = Tile.TILESIZE;
			Tile wallTile = Tile.getTile(nullTile);
			int wallOffsetX = xOffset % Tile.TILESIZE;
			int wallOffsetY = yOffset % Tile.TILESIZE;
			
			for(int y = -1; y < height/i+2; y++){
				for(int x = -1; x < width/i+1; x++){
					int renderX = (x*i) + wallOffsetX;
					int renderY = (y*i) + wallOffsetY;
					wallTile.render(g, renderX, renderY, i, i);
				}
			}
			
			for(int y = 0; y < tiles.length; y++){
				for(int x = 0; x < tiles[y].length; x++){
					int renderX = x*i+xOffset;
					int renderY = y*i+yOffset;
					if(renderX+i > 0 && renderY+   i > 0){
						if(renderX < width && renderY < height){
							Tile t = Tile.getTile(tiles[y][x]);
							if(t != null){
								t.render(g, renderX, renderY, i, i);
							}
						}
					}
				}
			}
		}
	}

	public int[][] getTiles() {
		return tiles;
	}
	public void addPolygonWall(Rectangle r) {
		if (polygonWalls == null) {
			polygonWalls = new ArrayList<Rectangle>();
		}
		polygonWalls.add(r);
	}

	public void addDoor(Door door) {
		doors.put(door.getName(),door);
	}

	public Door getDoor(String string) {
		return doors.get(string);
	}

	public Door getOverlappingDoor(Entity e) {
		Collection<Door> allDoors = doors.values();
		Iterator<Door> it = allDoors.iterator();
		while(it.hasNext()){
			Door d = it.next();
			int doorX = d.posX*Tile.TILESIZE;
			int doorY = d.posY*Tile.TILESIZE;
			int size = Tile.TILESIZE;
			Rectangle r = new Rectangle(doorX,doorY,size,size);
			if(r.intersects(e.getHitbox())&&d.acceptsEntity(e)){
				return d;
			}
		}
		return null;
	}
}
