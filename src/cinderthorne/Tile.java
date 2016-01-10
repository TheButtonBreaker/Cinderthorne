package cinderthorne;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	public String name;
	public BufferedImage texture;
	public BufferedImage[] modTextures;
	
	public Tile(String string) {
		this.name = string;
		try {
			texture = ImageIO.read(new File("Resources/"+name+".png"));
			File modFiles = new File("Resources/"+name+"/");
			if(modFiles.exists()){
				File[] modFilesArr = modFiles.listFiles();
				modTextures = new BufferedImage[modFilesArr.length];
				for(int i = 0; i < modFilesArr.length; i++){
					modTextures[i] = ImageIO.read(modFilesArr[i]);
				}
			}
		} catch (IOException e) {
			texture = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
			e.printStackTrace();
		}
	}
	
	public static final int SIZE = 32;
	public static final Tile GRASS = new Tile("Grass");
	
	public void drawTile(Graphics2D g, Point drawPoint, int xID, int yID){ //The 2 IDs are for later
		g.drawImage(texture, drawPoint.x, drawPoint.y, SIZE, SIZE, null);
		
		long seed = (xID+1)*(yID+1);
		MathUtil.setSeed(seed);
		if(modTextures.length > 0 && MathUtil.randInt(1, 10) == 3){
			g.drawImage(modTextures[MathUtil.randInt(0, modTextures.length-1)], drawPoint.x+MathUtil.randInt(-2, 14), drawPoint.y+MathUtil.randInt(-2, 14), null);
		}
	}
}
