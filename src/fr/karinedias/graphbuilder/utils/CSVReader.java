package fr.karinedias.graphbuilder.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

	// method for checking if name of the CSV file is correct aka .csv :
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

}

/*
 * TODO: Faire unr boucle si l'ouverture du fichier échoue ? TODO: quel chemin
 * de fichier chercher par défaut ? TODO: tester si cette classe fait son boulot
 * et implémenter d'autres fonctionnalités ?
 */