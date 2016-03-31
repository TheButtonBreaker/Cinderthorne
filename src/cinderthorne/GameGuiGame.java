package cinderthorne;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import cinderthorne.world.GameWorld;

public class GameGuiGame extends GameGui {
	public GameWorld world;
	private boolean paused;
	private boolean escCooldown;

	public GameGuiGame(){
		world = new GameWorld();
	}
	
	public void update(){
		boolean escDown = isKeyDown(KeyEvent.VK_ESCAPE);
		boolean wDown = isKeyDown(KeyEvent.VK_W);
		boolean aDown = isKeyDown(KeyEvent.VK_A);
		boolean sDown = isKeyDown(KeyEvent.VK_S);
		boolean dDown = isKeyDown(KeyEvent.VK_D);
		boolean qDown = isKeyDown(KeyEvent.VK_Q);

		if(escDown){
			if(!escCooldown){
				paused = !paused;
			}
			escCooldown = true;
		}else{
			escCooldown = false;
		}
		if(!paused){
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
		}else if(qDown){
			System.exit(0);
		}
	}
	
	@Override
	public void draw(Graphics2D g, int width, int height){
		world.render(g, width, height);
		if(paused){
			g.setColor(new Color(0,0,0,200));
			g.fillRect(0, 0, width, height);
			g.setColor(Color.white);
			g.setFont(GameGuiStart.createCinderFont(0, 20));
			String str = "Paused! ('esc' to continue, 'q' to quit)";
			g.drawString(str, width/2-g.getFontMetrics().stringWidth(str)/2, height/2);
		}
	}	
}
