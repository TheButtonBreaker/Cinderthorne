package cinderthorne.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import cinderthorne.util.ImageUtil;
import cinderthorne.world.Tile;

public class EntityHumanoid extends Entity{
	public String name;
	
	public EntityHumanoid(String name) {
		super(Tile.TILESIZE);
		this.name = name;
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
		int w = 32; //The size of the image on the actual file, not screen
		int h = 48;
		BufferedImage sprite = tileSheet.getSubimage((w*currentAnim), (h*direction), w, h);
		int width = 32;
		int height = 48;
		int maxSize = this.getHitbox().height;
		g.drawImage(sprite, x - ((width-maxSize)/2), y - ((height-maxSize)/2), width, height, null);
		g.setColor(new Color(250,0,0,50));
		g.drawRect(x, y, maxSize, maxSize);
	}
	
	private int currentAnim = 0;
	private int maxAnims = 3;
	private int animTick = 0;
	private int ticksPerAnim = 20;
	
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
