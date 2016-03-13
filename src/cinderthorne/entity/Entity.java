package cinderthorne.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

import cinderthorne.world.Tile;
import cinderthorne.world.gamesection.GameSection;

public class Entity {
	public int posX;
	public int posY;
	private int size;
	public int movespeed = 4;

	public Entity(int size) {
		this.size = size;
	}

	public Rectangle getHitbox() {
		return new Rectangle(posX, posY, size, size);
	}

	public Rectangle getHitbox(int x, int y) {
		return new Rectangle(x, y, size, size);
	}

	public boolean canMoveTo(Point targetPoint, GameSection map) {
		Rectangle hitbox = getHitbox(targetPoint.x, targetPoint.y);

		for (int y = 0; y < map.getTiles().length; y++) {
			for (int x = 0; x < map.getTiles()[y].length; x++) {
				Tile t = Tile.getTile(map.getTiles()[y][x]);

				Rectangle hitbox2 = new Rectangle(x * Tile.TILESIZE, y * Tile.TILESIZE, Tile.TILESIZE, Tile.TILESIZE);

				if (hitbox.intersects(hitbox2)) {
					if (t == null) {
						return !map.boundsAreWalls;
					}
					if (t.isDefaultWall()) {
						return !map.defaultWalls;
					}
				}
				
				if(map.polygonWalls != null){
					Iterator<Rectangle> it = map.polygonWalls.iterator();
					while(it.hasNext()){
						Rectangle r = it.next();
						if(hitbox.intersects(r)){
							return false;
						}
					}
				}
			}
		}
		
		if (map.boundsAreWalls && hitbox.x < 0
				|| hitbox.x + hitbox.getWidth() > map.getTiles()[(hitbox.y + Tile.TILESIZE-1)/Tile.TILESIZE].length * Tile.TILESIZE) {
			return false;
		}
		if (map.boundsAreWalls && hitbox.x < 0
				|| hitbox.x + hitbox.getWidth() > map.getTiles()[Math.max((hitbox.y)/Tile.TILESIZE,0)].length * Tile.TILESIZE) {
			return false;
		}
		if (map.boundsAreWalls && hitbox.y < 0
				|| hitbox.y + hitbox.getHeight() > map.getTiles().length * Tile.TILESIZE) {
			return false;
		}

		return true;
	}

	public void attemptMove(int right, int down, GameSection map) {
		down *= movespeed;
		right *= movespeed;
		if (canMoveTo(new Point(posX + right, posY + down), map)) {
			posX += right;
			posY += down;
		}
	}

	public void draw(Graphics2D g, int xOff, int yOff) {
		g.setColor(Color.red);
		g.drawRect(xOff + posX, yOff + posY, size, size);
	}

	public void update() {
	}
}
