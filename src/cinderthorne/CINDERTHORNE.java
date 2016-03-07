package cinderthorne;

import cinderthorne.util.TickUtil;

public class CINDERTHORNE {
	public static Game game = null;

	public static void main(String[] args) {
		System.out.println("It begins.");

		game = new Game();
		TickUtil.startTicking();
	}
}
