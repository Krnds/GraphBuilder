package fr.karinedias.graphbuilder.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SVGWriter {

	public static void writeFile() {
		// donne le chemin du fichier en cours d'exécution :
		String directory = System.getProperty("user.dir");
		String outputFile = "test.svg"; // TODO: à changer
		String absolutePath = directory + File.separator + outputFile;
		// renvoie le fichier output dans le chemin en cours sans se soucier du
		// séparateur (car dépend des OS

		// écriture du fichier :
		try {
			FileWriter fileWriter = new FileWriter(absolutePath);
			String content = " "; // TODO: ce qui va être écrit dans mon fichier SVG
			fileWriter.write(content);
		} catch (IOException exc) {
			// TODO: excpetion handling
		}
	}

	public static void main(String[] args) {

		System.out.println(File.separator);

	}

}
