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
	

	public static String askFileName() {
		
		//TODO: pas besoin de valeur de retour

		Scanner sc = new Scanner(System.in);

		do {

			try {
				System.out.println("Nom du fichier CSV ?");
				fileName = sc.next();
				if (!correctName(fileName)) {
					System.out.println(("nom incorrect"));
				}
				// utile d'utiliser des blocs try, catch ?
				// que faire de mieux ?
			} catch (IllegalArgumentException exc) {
				System.out.println("Le nom du fichier n'est pas correct. Veuillez recommencer...");
			} catch (NullPointerException exc) {
				System.out.println("Veuillez entrer un nom de fichier svp.");
			}
		} while (!correctName(fileName));
		sc.close();

		return fileName;

	}

	// TODO: faire une méthode pour ouvrir un fichier CSV en entrée à
	/*
	 * à partir de la classe externe graphbuilder.CSVParser
	 * 
	 */

	public void openCSV(String fileName) {

		String CSVFile = askFileName(); // correct de le mettre ici
		String line = null;

		try {
			FileReader fileReader = new FileReader(CSVFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				// appeler une méthode pour lire le contenu...
			}
			bufferedReader.close();
		} catch (FileNotFoundException exc) {
			System.out.println("File " + fileName +  " not found."); // TODO: mettre autre chose ?
		} catch (IOException exc) {
			System.out.println("Error while reading file."); // TODO: correct ?
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
		return this.fileName;
	}

}

/*
 * TODO: Faire unr boucle si l'ouverture du fichier échoue ? TODO: quel chemin
 * de fichier chercher par défaut ? TODO: tester si cette classe fait son boulot
 * et implémenter d'autres fonctionnalités ?
 */