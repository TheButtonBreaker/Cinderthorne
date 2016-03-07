package cinderthorne.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import cinderthorne.world.gamesection.GameSection;
import cinderthorne.world.gamesection.GameSectionLoadHandler;

public class WorldUtil {
	public static HashMap<String, GameSectionLoadHandler> loadHandlers = new HashMap<String, GameSectionLoadHandler>();
	public static GameSectionLoadHandler tiles = new GameSectionLoadHandler(">tiles") {
		@Override
		public void processLine(String line, GameSection world, int lineNumero) {
			int lineNumber = lineNumero - 2;
			if (line != "\n" && line != null && line != "") {
				if (line.startsWith("size ")) {
					String sizePart = line.substring(5);
					int[] sizes = new int[2];
					String[] sizesString = sizePart.split(",");
					sizes[0] = Integer.parseInt(sizesString[0]);
					sizes[1] = Integer.parseInt(sizesString[1]);
					world.tiles = new int[sizes[0]][sizes[1]];
				} else {
					String[] tiles = line.split(",");
					for (int i = 0; i < tiles.length; i++) {
						Tile potentialTile = Tile.alibis.get(tiles[i]);
						if (potentialTile != null) {
							world.tiles[lineNumber][i] = potentialTile.id;
						}
					}
				}
			}
		}
	};
	public static GameSectionLoadHandler currentLoadHandler = null;

	public static void loadGameSectionFromFile(File file, GameSection world) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			int lineNumber = 0;
			for (String line; (line = br.readLine()) != null;) {
				lineNumber = lineNumber + 1;
				line = line.toLowerCase();
				if (line.startsWith(">")) {
					currentLoadHandler = loadHandlers.get(line);
					lineNumber = 0;
				} else {
					currentLoadHandler.processLine(line, world, lineNumber);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
