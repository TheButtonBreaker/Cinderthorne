package cinderthorne.util;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import cinderthorne.world.DoorDefault;
import cinderthorne.world.Tile;
import cinderthorne.world.gamesection.GameSection;
import cinderthorne.world.gamesection.GameSectionLoadHandler;

public class WorldUtil {
	public static HashMap<String, GameSectionLoadHandler> loadHandlers = new HashMap<String, GameSectionLoadHandler>();
	public static GameSectionLoadHandler tiles = new GameSectionLoadHandler(">tiles") {
		@Override
		public void processLine(String line, GameSection world, int lineNumero) {
			int lineNumber = lineNumero - 2;
			if (line.startsWith("size ")) {
				String sizePart = line.substring(5);
				world.tiles = new int[Integer.parseInt(sizePart)+1][0];
			} else {
				String[] tiles = line.split(",");
				world.tiles[lineNumber] = new int[tiles.length];
				for (int i = 0; i < tiles.length; i++) {
					Tile potentialTile = Tile.alibis.get(tiles[i]);
					if (potentialTile != null) {
						world.tiles[lineNumber][i] = potentialTile.id;
					}
				}
			}
		}
	};
	public static GameSectionLoadHandler alibis = new GameSectionLoadHandler(">alibis") {
		@Override
		public void processLine(String line, GameSection world, int lineNumber) {
			String[] lines = line.split(" ");
			if (lines.length >= 2) {
				String alibi = lines[0];
				for (int i = 1; i < lines.length; i++) {
					Tile.alibis.put(alibi, Tile.getTileFromName(lines[i]));
				}
			}
		}
	};
	public static GameSectionLoadHandler walls = new GameSectionLoadHandler(">walls") {
		@Override
		public void processLine(String line, GameSection world, int lineNumber) {
			if(line.equals("allbounds")){
				world.boundsAreWalls = true;
				return;
			}else if(line.equals("nobounds")){
				world.boundsAreWalls = false;
				return;
			}
			if(line.startsWith("setwalls ")){
				String wallName = line.substring(9);
				world.tileWalls.put(wallName, true);
				return;
			}else if(line.startsWith("setnotwalls ")){
				String wallName = line.substring(12);
				world.tileWalls.put(wallName, false);
				return;
			}
			if(line.equals("defaultwalls")){
				world.boundsAreWalls = true;
				world.defaultWalls = true;
			}else if(line.equals("nodefaults")) {
				world.boundsAreWalls = false;
				world.defaultWalls = false;
			}
			if(line.startsWith("addpolygon ")){
				String[] paramsFake = line.substring(11).split(",");
				int[] params = new int[4];
				for(int i = 0; i < params.length; i++){
					params[i] = Integer.parseInt(paramsFake[i])*Tile.TILESIZE;
				}
				Rectangle r = new Rectangle(params[0],params[1],params[2],params[3]);
				world.addPolygonWall(r);
			}
		}
	};
	public static GameSectionLoadHandler doors = new GameSectionLoadHandler(">doors") {
		@Override
		public void processLine(String line, GameSection world, int lineNumber) {
			String[] params = line.split(" ");
			world.addDoor(new DoorDefault(params));
		}
	};
	
	
	public static GameSectionLoadHandler currentLoadHandler = null;

	public static void loadGameSectionFromFile(File file, GameSection world) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			int lineNumber = 0;
			for (String line; (line = br.readLine()) != null;) {
				if (!line.startsWith("#") && line != "\n" && line != null && line != "" && line.length() > 0) {
					lineNumber = lineNumber + 1;
					line = line.toLowerCase();
					if (line.startsWith(">")) {
						currentLoadHandler = loadHandlers.get(line);
						lineNumber = 0;
					} else {
						currentLoadHandler.processLine(line, world, lineNumber);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
