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
		currentSection = new GameSection("main");
		player = new EntityHumanoid("Player", 32, 48);
		Door mainDoor = currentSection.getDoor("main");
		player.posX = mainDoor.posX*Tile.TILESIZE;
		player.posY = mainDoor.posY*Tile.TILESIZE;
	}
	
	boolean doorCooldown = false;
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
		Door inDoor = currentSection.getOverlappingDoor(player);
		if(inDoor != null){
			if(!doorCooldown){
				inDoor.playerEntered(player, this, currentSection);
				doorCooldown = true;
			}
		}else{
			doorCooldown = false;
		}
	}

	public void render(Graphics2D g, int width, int height) {
		updateOffsets(width, height);
		if(currentSection != null){
			currentSection.render(g, xOffset, yOffset, width, height);
		}
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			Entity e = it.next();
			e.draw(g, xOffset, yOffset);
		}
		player.forceDraw(g, width/2-player.size/2, height/2-player.size/2);
	}
	private void updateOffsets(int width, int height){
		xOffset = width/2 - player.posX - player.size/2;
		yOffset = height/2 - player.posY - player.size/2;
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
