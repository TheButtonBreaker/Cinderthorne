package cinderthorne;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
	public int locX;
	public int locY;
	private int size;
	public int speed = 3;
	
	public Entity(int size){
		this.size = size;
	}
	
	public Rectangle getHitbox(){
		return new Rectangle(locX,locY,size,size);
	}
	
	public void draw(Graphics2D g, int xOff, int yOff){
		g.setColor(Color.red);
		g.drawRect(xOff+locX, yOff+locY, size, size);
	}
}
