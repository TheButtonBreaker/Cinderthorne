package cinderthorne;

import cinderthorne.util.StringUtil;
import cinderthorne.util.TickUtil;
import cinderthorne.world.Tile;

public class CINDERTHORNE {
	public static Game game = null;

	public static void main(String[] args) {
		System.out.println("It begins.");
		Tile.loadAllTiles(StringUtil.getLinesFromFile("Resources/_DATA/Tile/Default.txt"));
		game = new Game();
		TickUtil.startTicking();
	}
}
