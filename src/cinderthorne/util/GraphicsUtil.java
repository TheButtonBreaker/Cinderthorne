package cinderthorne.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicsUtil {
	public static BufferedImage loadImg(File f){
		try {
			return ImageIO.read(f);
		} catch (IOException e) {
			System.out.println("Could not find " + f.getAbsolutePath());
			try {
				return ImageIO.read(new File("assets/Missing.png"));
			} catch (IOException e1) {
				System.out.println("Why is 'Missing.png' missing? The irony is thick...");
			}
		}
		return null;
	}
}
