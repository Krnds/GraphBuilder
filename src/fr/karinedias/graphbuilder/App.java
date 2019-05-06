package fr.karinedias.graphbuilder;

import java.io.File;

public class App {
	
	public static void main(String[] args) {
		String defaultPath = System.getProperty("user.dir") + File.separator + "csv" + File.separator;
		System.out.println(defaultPath);
		
		
		String[] keys = new String[10];
		keys = test.replaceAll("\\\"[^\\\"]*\\\"", "").split(",");

		for (int i = 0; i < keys.length; i++) {
			System.out.println(keys[i]);
		}
	}
}