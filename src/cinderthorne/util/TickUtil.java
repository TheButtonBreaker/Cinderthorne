package cinderthorne.util;

import cinderthorne.CINDERTHORNE;

public class TickUtil {
	private static long sleep = 10; // How long we should let the Thread sleep
	private static long tickDelay = 16*1000000; // Wait __ nanoseconds before updating
	private static long lastTickTime = System.nanoTime() - tickDelay;
	
	private static FPSCounter counter;
	public static void startTicking() {
		counter = new FPSCounter();
		counter.start();
		while (true) {
			long currentTime = System.nanoTime();
			doSleep();
			while (currentTime - lastTickTime >= tickDelay) {
				doUpdates();
				doRepaints();
				lastTickTime += tickDelay;//currentTime;
			}
		}
	}

	public static double getFps(){
		return counter.fps();
	}
	
	private static void doRepaints() {
		CINDERTHORNE.game.panel.repaint();	
		counter.interrupt();
	}

	protected static void doUpdates() {
		if (CINDERTHORNE.game != null) {
			CINDERTHORNE.game.update();
		} else {
			System.out.println("Warning, CINDERTHORNE.game is null");
		}
	}

	private static void doSleep() {
		if(sleep > 0){
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
