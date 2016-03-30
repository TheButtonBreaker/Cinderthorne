package cinderthorne.util;

class FPSCounter extends Thread {
	private long lastTime;
	private double fps; // could be int or long for integer values

	public void run() {
		while(true){
			lastTime = System.nanoTime();
			try {
				Thread.sleep(1000); // longer than one frame
			} catch (InterruptedException e) {
			}
			fps = 1000000000.0 / (System.nanoTime() - lastTime);
			lastTime = System.nanoTime();
		}
	}

	public double fps() {
		return fps;
	}
}