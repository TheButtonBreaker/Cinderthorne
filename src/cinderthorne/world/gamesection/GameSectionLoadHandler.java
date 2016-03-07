package cinderthorne.world.gamesection;

import cinderthorne.world.WorldUtil;

public abstract class GameSectionLoadHandler {
	public GameSectionLoadHandler(String name){
		WorldUtil.loadHandlers.put(name, this);
	}
	public abstract void processLine(String line, GameSection world, int lineNumber);
}
