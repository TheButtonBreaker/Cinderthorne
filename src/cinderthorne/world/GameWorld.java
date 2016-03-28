package cinderthorne.world;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import cinderthorne.entity.Entity;
import cinderthorne.entity.EntityHumanoid;
import cinderthorne.world.gamesection.GameSection;

public class GameWorld implements KeyListener{
	public GameSection currentSection = null;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public static Entity player;
	
	public int xOffset,yOffset;
	
	public GameWorld(){
		currentSection = new GameSection("test");
		player = new EntityHumanoid("player");
	}
	
	public void update() {
		if(currentSection != null){
			currentSection.update();
		}
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			Entity e = it.next();
			e.update();
		}
		player.update();
	}

	public void render(Graphics2D g, int width, int height) {
		updateOffsets(width, height);
		
		if(currentSection != null){
			currentSection.render(g, xOffset, yOffset, width, height);
		}
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			updateOffsets(width, height);
			Entity e = it.next();
			e.draw(g, xOffset, yOffset);
		}
		updateOffsets(width, height);
		player.draw(g, xOffset, yOffset);
	}
	private void updateOffsets(int width, int height){
		xOffset = width/2 - player.posX;
		yOffset = height/2 - player.posY;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
