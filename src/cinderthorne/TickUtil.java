package cinderthorne;

public class TickUtil {
	private static long sleep = 10; //How long we should let the Thread sleep
	private static long tickDelay = 20; //Wait __ milliseconds before doing another tick
	private static long lastTickTime = System.currentTimeMillis()-tickDelay;
	
	public static void startTicking(){
		Thread threaderino = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					long currentTime = System.currentTimeMillis();
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					while(currentTime - lastTickTime >= tickDelay){
						//Do this stuff until we are caught up with our ticks
						doUpdates();
						lastTickTime+=tickDelay;
					}
				}
			}
		});
		threaderino.start();
	}

	protected static void doUpdates() {
		CINDERTHORNE.game.update();
		CINDERTHORNE.game.panel.repaint();
	}
}
