package cinderthorne.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {

	public static BufferedImage load(String string) {
		String resource = "Resources/" + string;
		try {
			return ImageIO.read(new File(resource));
		} catch (IOException e) {
			System.err.println("Couldn't load: " + resource + "(Is the file there, is it a typo, etc.?)");
			e.printStackTrace();
		}
		return null;
	}

}
