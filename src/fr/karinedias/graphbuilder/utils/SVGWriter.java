package fr.karinedias.graphbuilder.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SVGWriter {
	
	private static String directory = System.getProperty("user.dir");
	private static String outputFile = "output.svg"; // TODO: à changer et à lier avec le fichier de sortie des classes LineChart et BarChart
	private static String absolutePath = directory + File.separator + outputFile;

	public static void writeFile() {
		// donne le chemin du fichier en cours d'exécution :

		// renvoie le fichier output dans le chemin en cours sans se soucier du
		// séparateur (car dépend des OS)

		// écriture du fichier :
		try {
			FileWriter fileWriter = new FileWriter(absolutePath);
			String content = " "; // TODO: ce qui va être écrit dans mon fichier SVG
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException exc) {
			// TODO: excpetion handling
		}
	}

	
	//getter pour avoir l'emplacement du fichier :
	public static String getPath() {
		return absolutePath;
	}

}
