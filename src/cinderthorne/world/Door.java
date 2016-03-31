package cinderthorne.world;

import cinderthorne.entity.Entity;
import cinderthorne.world.gamesection.GameSection;

public abstract class Door {
	protected String name;
	public int posX;
	public int posY;
	
	public Door(String n, int x, int y){
		name = n;
		posX = x;
		posY = y;
	}
	
	public String getName(){
		return name;
	}
	
	public abstract String getGameSection1();
	public abstract String getGameSection2();
	
	public abstract void playerEntered(Entity plr, GameWorld world, GameSection map);

	public boolean acceptsEntity(Entity e) {
		return true;
	}
}
