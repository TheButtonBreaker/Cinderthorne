package cinderthorne;

import java.awt.Graphics2D;

import cinderthorne.world.GameWorld;

public class GameGuiGame extends GameGui {
	public GameWorld world;
	
	public GameGuiGame(){
		world = new GameWorld();
	}
	
	public void update(){
		boolean wDown = isKeyDown('w');
		boolean aDown = isKeyDown('a');
		boolean sDown = isKeyDown('s');
		boolean dDown = isKeyDown('d');
		if(wDown){
			GameWorld.player.attemptMove(0, -1, world.currentSection);
		}
		if(aDown){
			GameWorld.player.attemptMove(-1, 0, world.currentSection);
		}
		if(sDown){
			GameWorld.player.attemptMove(0, 1, world.currentSection);
		}
		if(dDown){
			GameWorld.player.attemptMove(1, 0, world.currentSection);
		}
		GameWorld.player.setMoving(wDown||aDown||sDown||dDown);
		
		world.update();
	}
	
	@Override
	public void draw(Graphics2D g, int width, int height){
		world.render(g, width, height);
	}	
}
