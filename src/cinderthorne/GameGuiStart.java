package cinderthorne;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import cinderthorne.util.ImageUtil;
import cinderthorne.util.RenderingUtil;

public class GameGuiStart extends GameGui {
	private static Font cinderFont = null;
	static double rot;
	
	public GameGuiStart(){
		createFont();
	}
	
	private float fadeAmount = 1f;
	private byte animStage = 0;
	// 0 = "A game by ZianGames and ThatCat"
	// 1 = Fade Out
	// 2 = Fade In
	// 3 = "Cinderthorne"
	// 4 = Press any key appears and blinks
	// (Nothing more, stuck at 4 for eternity until the GameGui is exited)
	
	public void draw(Graphics2D g, int width, int height){
		if(animStage == 0){
			g.setColor(Color.white);
			g.setFont(createCinderFont(0,20));
			String str = "A game by ZianGames and ThatCat";
			int strwidth = g.getFontMetrics().stringWidth(str);
			g.drawString(str, width/2-strwidth/2, height/2+g.getFontMetrics().getHeight()/2);
		}else if(animStage >= 3){
			BufferedImage titleImg = ImageUtil.load("Title.png");
			int cushion = 10;
			int imgWidth = width - cushion*2;
			int imgRatio = imgWidth/titleImg.getWidth();
			g.drawImage(titleImg, width/2-imgWidth/2, cushion, imgWidth, titleImg.getHeight()*imgRatio, null);
			
			if(animStage == 4){
				g.setColor(Color.white);
				String str = "Press any key to start";
				g.setFont(createCinderFont(0,30));
				int strwidth = g.getFontMetrics().stringWidth(str);
				g.drawString(str, width/2-strwidth/2, height/2+g.getFontMetrics().getHeight()+20);
			}
		}
		
		if(fadeAmount > 0){
			g.setColor(new Color(0,0,0,fadeAmount));
			g.fillRect(0, 0, width, height);
		}
	}

	int currentAnimLength = 0; //How long has the animation been playing
	@Override
	public void update(){
		int[] durations = {100,100,1000,1000,2};
		currentAnimLength++;
		
		if(currentAnimLength >= durations[animStage]){
			currentAnimLength = 0;
			if(animStage < durations.length-1){
				animStage++;
			}
		}
		
		float increment = 0.02f; //How much does the fade change every tick?
		fadeAmount = fadeAmountChange(increment);
	}
	private float fadeAmountChange(float f){
		return (float) Math.min(Math.max(0, f - 0.002),1);
	}
	
	@Override
	public void keyPressed(KeyEvent k){
		if(animStage == 4){
			RenderingUtil.setGui(new GameGuiGame(), CINDERTHORNE.game.frame, CINDERTHORNE.game.panel);;
		}else{
			animStage = 4;
		}

	}
	
	public static Font createCinderFont(int style, float size){
		return cinderFont.deriveFont(style).deriveFont(size);
	}
	
	public static void createFont() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/Cinderthorne Font.ttf"));
			ge.registerFont(font);
			cinderFont = font;
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}
}
