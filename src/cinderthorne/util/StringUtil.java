package cinderthorne.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StringUtil {
	public static ArrayList<String> getLinesFromFile(String file) {
		return getLinesFromFile(new File(file));
	}

	private static ArrayList<String> getLinesFromFile(File file) {
		ArrayList<String> ret = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String line; (line = br.readLine()) != null;) {
				ret.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}
