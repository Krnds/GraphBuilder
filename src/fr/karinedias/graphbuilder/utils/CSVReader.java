package fr.karinedias.graphbuilder.utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {
	
	public static String fileName =  null;
	


	/*
	 * Classe qui vérifie si le fichier CSV est de type .csv et qu'il existe bien dans le dossier GraphBuilder/csv
	 */


	// méthode qui vérifie que le nom du fichier se termine bien en ".csv"
	public static boolean correctName(String str) {

		Pattern p = Pattern.compile("(\\.(csv)){1}$");
		Matcher m = p.matcher(str);
		boolean correct = m.find();

		return correct;
	}
	
	// méthode qui vérifie que le fichier csv donné en entrée existe bien
	public static boolean fileExists(String f) {

		String absolutePath = new File(f).getAbsoluteFile().getParentFile() + File.separator + "csv" + File.separator
				+ f;
		File absoluteFile = new File(absolutePath);
		return absoluteFile.isFile();

	}


}
