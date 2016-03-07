package cinderthorne.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cinderthorne.Game;
import cinderthorne.GameGui;

public class RenderingUtil {
	public static final Dimension RESOLUTION = new Dimension(1200, 720);

	private static GameGui gui = null;

	public static void doRendering(Graphics gee, JPanel p, Game game) {
		Graphics2D g = (Graphics2D) gee;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int pwidth = p.getWidth();
		int pheight = p.getHeight();
		int width = RESOLUTION.width;
		int height = RESOLUTION.height;

		g.scale(pwidth / RESOLUTION.getWidth(), pheight / RESOLUTION.getHeight());
		g.setColor(Color.black);
		g.fillRect(0, 0, RESOLUTION.width, RESOLUTION.height);

		if (gui != null) {
			// gui = new GameGuiStart();
			gui.draw(g, width, height);
		} else {
			System.out.println("Warning, Game gui is null");
		}
	}

	public static void setGui(GameGui newGui, JFrame frame, JPanel panel) {
		if (gui != null) {
			frame.removeMouseListener(gui);
			frame.removeKeyListener(gui);
			frame.removeMouseMotionListener(gui);
		}
		frame.addMouseListener(newGui);
		frame.addKeyListener(newGui);
		frame.addMouseMotionListener(newGui);
		RenderingUtil.gui = newGui;
	}

	public static GameGui getGameGui() {
		return gui;
	}
}
