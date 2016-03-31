package cinderthorne.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import cinderthorne.util.ImageUtil;
import cinderthorne.world.Tile;

public class EntityHumanoid extends Entity{
	public String name;
	private int w;
	private int h;
	
	public EntityHumanoid(String name, int width, int height) {
		super(Tile.TILESIZE-10);
		this.name = name;
		w = width;
		h = height;
	}
	
	@Override
	public Rectangle getHitbox(int x, int y) {
		return new Rectangle(x, y, w, h);
	}
	
	String tileSheetName = null;
	BufferedImage tileSheet = null;
	public void forceDraw(Graphics2D g, int x, int y) {
		if(tileSheetName == null){
			tileSheetName = "Entity/"+name+".png";
		}
		if(tileSheet == null){
			tileSheet = ImageUtil.load(tileSheetName);
		}
//		int w = 32; //The size of the image on the actual file, not screen
//		int h = 48;
		BufferedImage sprite = tileSheet.getSubimage((w*currentAnim), (h*direction), w, h);
		int width = 32;
		int height = 48;
		g.drawImage(sprite, x, y, width, height, null);
	}
	
	private int currentAnim = 0;
	private int maxAnims = 3;
	private int animTick = 0;
	private int ticksPerAnim = 10;
	
	@Override
	public void setMoving(boolean moving){
		if(!this.moving && moving){
			currentAnim = 1;
		}
		super.setMoving(moving);
	}
	
	@Override
	public void update(){
		super.update();
		if(moving){
			animTick++;
			if(animTick > ticksPerAnim){
				currentAnim++;
				animTick = 0;
			}
			if(currentAnim > maxAnims){
				currentAnim = 0;
			}
		}else{
			animTick = 0;
			currentAnim = 0;
		}
	}
}
