package cinderthorne.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import cinderthorne.world.Tile;
import cinderthorne.world.gamesection.GameSection;

public class Entity {
	public int posX;
	public int posY;
	private int size;
	public int movespeed = 3;

	public Entity(int size) {
		this.size = size;
		System.out.println("hello, size of " + size);
	}

	public Rectangle getHitbox() {
		return new Rectangle(posX, posY, size, size);
	}

	public Rectangle getHitbox(int x, int y) {
		return new Rectangle(x, y, size, size);
	}

	public boolean canMoveTo(Point targetPoint, GameSection map) {
		Rectangle hitbox = getHitbox(targetPoint.x, targetPoint.y);
		
		if (hitbox.x < 0 || hitbox.x + hitbox.getWidth() > map.getTiles().length * Tile.TILESIZE) {
			return false;
		}
		if (hitbox.y < 0 || hitbox.y + hitbox.getHeight() > map.getTiles()[0].length * Tile.TILESIZE) {
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
