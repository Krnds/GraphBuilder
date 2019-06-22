package fr.karinedias.graphbuilder.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {
	
	public static String fileName =  null;
	


	// TODO: faire une méthode pour ouvrir un fichier CSV en entrée à
	/*
	 * à partir de la classe externe graphbuilder.CSVParser
	 * 
	 */

	public static FileReader openCSV(String fileName) {


		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
			}
			bufferedReader.close();
			return fileReader;
			
		} catch (FileNotFoundException exc) {
			System.out.println("File " + fileName +  " not found.");
			return null;
		} catch (IOException exc) {
			System.out.println("Error while reading file.");
			return null;
		}
		
		
	}

	// méthode qui vérifie que le nom du fichier se termine bien en ".csv"
	public static boolean correctName(String str) {

		Pattern p = Pattern.compile("(\\.(csv)){1}$");
		Matcher m = p.matcher(str);
		boolean correct = m.find();

		return correct;
	}
	
//getter 
	public String getFileName() {
		return CSVReader.fileName; //correct de faire ça ?
	}
	
	//TODO: à supprimer
	public String getCSVPath() {
		
		String directory = System.getProperty("user.dir");
		
		String absolutePath = directory + File.separator + getFileName();
		
		return absolutePath;
	}
	
	// méthode qui vérifie que le fichier csv donné en entrée existe bien
	public static boolean fileExists(String f) {

		String absolutePath = new File(f).getAbsoluteFile().getParentFile() + File.separator + "csv" + File.separator
				+ f;
		File absoluteFile = new File(absolutePath);
		return absoluteFile.isFile();

	}

}

/*
 * TODO: Faire unr boucle si l'ouverture du fichier échoue ? TODO: quel chemin
 * de fichier chercher par défaut ? TODO: tester si cette classe fait son boulot
 * et implémenter d'autres fonctionnalités ?
 */