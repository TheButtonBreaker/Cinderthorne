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
	public int size;
	public int movespeed = 3;
	public int direction = 0;
	protected boolean moving = false;
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 1;
	public static final int DIRECTION_SOUTH = 2;
	public static final int DIRECTION_WEST = 3;

	public Entity(int size) {
		this.size = size;
	}

	public Rectangle getHitbox() {
		return new Rectangle(posX, posY, size, size);
	}

	public Rectangle getHitbox(int x, int y) {
		return new Rectangle(x, y, size, size);
	}

	public void setDirection(int direction){
		this.direction = direction;
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
	
	public void setMoving(boolean moving){
		this.moving = moving;
	}
	public boolean isMoving(){
		return moving;
	}

	public void attemptMove(int right, int down, GameSection map) {
		int xMult = 1;
		if(right < 0){
			xMult = -1;
		}
		int yMult = 1;
		if(down < 0){
			yMult = -1;
		}
		while(right != 0){
			right -= 1*xMult;
			attemptMove2(1*xMult,0,map);
		}
		while(down != 0){
			down -= 1*yMult;
			attemptMove2(0,1*yMult,map);
		}
		//attemptMove2(right,down,map);
	}
	//part 2 of the beloved function
	private void attemptMove2(int right, int down, GameSection map){
		down *= movespeed;
		right *= movespeed;
		if (canMoveTo(new Point(posX + right, posY + down), map)) {
			posX += right;
			posY += down;
		}
		if(down == 0){
			if(right<0){
				this.setDirection(DIRECTION_WEST);
			}else{
				this.setDirection(DIRECTION_EAST);
			}
		}else{
			if(down<0){
				this.setDirection(DIRECTION_NORTH);
			}else{
				this.setDirection(DIRECTION_SOUTH);
			}
		}
	}

	public void draw(Graphics2D g, int xOff, int yOff) {
		forceDraw(g,posX+xOff,posY+yOff);
	}
	
	public void forceDraw(Graphics2D g, int x, int y) {
		g.setColor(Color.red);
		g.drawRect(x, y, size, size);
	}

	public void update() {
	}
}
