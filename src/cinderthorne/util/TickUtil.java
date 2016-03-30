package cinderthorne.util;

import cinderthorne.CINDERTHORNE;

public class TickUtil {
	private static long sleep = 10; // How long we should let the Thread sleep
	private static long tickDelay = 5*1000000; // Wait __ milliseconds before updating
	private static long lastTickTime = System.nanoTime() - tickDelay;
	
	private static FPSCounter counter;
	public static void startTicking() {
		counter = new FPSCounter();
		counter.start();
		while (true) {
			long currentTime = System.nanoTime();
			while (currentTime - lastTickTime >= tickDelay) {
				doUpdates();
				lastTickTime += tickDelay;//currentTime;
				doRepaints();
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
			//doSleep();
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
