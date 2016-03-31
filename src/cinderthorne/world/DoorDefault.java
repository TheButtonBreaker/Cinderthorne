package cinderthorne.world;

import cinderthorne.entity.Entity;
import cinderthorne.world.gamesection.GameSection;

public class DoorDefault extends Door{
	public boolean allowsEntry;
	//Doors go from 1 to 2.
	//But if the player is already in 2, it takes the player to 1
	public String section1;
	public String section2;
	
	public DoorDefault(String[] params){
		super(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2]));
		
		if(params[3].equals("no_entry")){
			allowsEntry = false;
		}else if(params[3].equals("allows_entry")){
			allowsEntry = true;
		}
		
		section1 = params[4];
		section2 = params[5];
	}

	@Override
	public String getGameSection1() {
		return section1;
	}

	@Override
	public String getGameSection2() {
		return section2;
	}

	@Override
	public void playerEntered(Entity plr, GameWorld world, GameSection map) { //the section provided is the current section
		int plrX,plrY;
		if(allowsEntry){
			if(map.name.equals(section2)){
				world.currentSection = new GameSection(section1);
				Door exit = world.currentSection.getDoor(name);
				plrX = exit.posX * Tile.TILESIZE;
				plrY = exit.posY * Tile.TILESIZE;
			}else{
				world.currentSection = new GameSection(section2);
				Door exit = world.currentSection.getDoor(name);
				plrX = exit.posX * Tile.TILESIZE;
				plrY = exit.posY * Tile.TILESIZE;
			}
			plr.posX = plrX;
			plr.posY = plrY;
		}
	}
	
	@Override
	public boolean acceptsEntity(Entity e) {
		return allowsEntry;
	}
}
