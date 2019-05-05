package fr.karinedias.graphbuilder;

import java.io.File;

public class App {
	
	public static void main(String[] args) {
		String defaultPath = System.getProperty("user.dir") + File.separator + "csv" + File.separator;
		System.out.println(defaultPath);
	}
}