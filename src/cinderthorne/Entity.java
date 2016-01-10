package cinderthorne;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class Entity {
	public int locX;
	public int locY;
	private int size;
	public int movespeed = 3;

	public Entity(int size) {
		this.size = size;
	}

	public Rectangle getHitbox() {
		return new Rectangle(locX, locY, size, size);
	}

	public Rectangle getHitbox(int x, int y) {
		return new Rectangle(x, y, size, size);
	}

	public boolean canMoveTo(Point targetPoint, TileMap map) {
		Rectangle hitbox = getHitbox(targetPoint.x, targetPoint.y);
		
		if (hitbox.x < 0 || hitbox.x + hitbox.getWidth() > map.getTiles().length * Tile.SIZE) {
			return false;
		}
		if (hitbox.y < 0 || hitbox.y + hitbox.getHeight() > map.getTiles()[0].length * Tile.SIZE) {
			return false;
		}

		return true;
	}

	public void attemptMove(int right, int down, TileMap map) {
		down *= movespeed;
		right *= movespeed;
		if (canMoveTo(new Point(locX + right, locY + down), map)) {
			locX += right;
			locY += down;
		}
	}

	public void draw(Graphics2D g, int xOff, int yOff) {
		g.setColor(Color.red);
		g.drawRect(xOff + locX, yOff + locY, size, size);
	}
}
