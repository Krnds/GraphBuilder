
package fr.karinedias.graphbuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import fr.karinedias.graphbuilder.utils.*;

public class CSVParser extends CSVReader {

	public CSVParser() {
		// TODO Auto-generated constructor stub
		super();
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom du fichier ?");
		CSVReader.fileName = sc.nextLine();
		if (correctName(CSVReader.fileName)) {
			System.out.println("Nom du fichier correct = " + CSVReader.fileName);
		} else {
			System.out.println("Nom du fichier incorrect !");
		}
		try {

			ArrayList<String[]> test = new ArrayList<String[]>();
			test.add(new String[10]);
			// for (int i = 0; (parseKeys(CSVReader.fileName)).length; i++) {
			// test.add(parseKeys(CSVReader.fileName));
			// }
			for (int i = 0; i < test.size(); i++) {
				test.get(i);
			}

			int arrayLength = parseKeys(CSVReader.fileName).length;

			System.out.println("Les données sont :");
			for (int i = 0; i < arrayLength; i++) {
				System.out.println(parseKeys(CSVReader.fileName)[i]);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// méthode qui récupère tous les champs du fichier CSV
	public static String[] parseKeys(String filename) throws IOException {

		String[] keys = new String[10];
		String firstLine = null;

		// chemin de base du fichier à chercher :
		String defaultPath = System.getProperty("user.dir") + File.separator + "csv" + File.separator;
		BufferedReader br = new BufferedReader(new FileReader(defaultPath + filename));
		firstLine = br.readLine();

		keys = firstLine.replaceAll("\"[^\\\"]*", "").split(",");

		br.close();
		return keys;

	}
}

/*
 * TODO: méthode pour avoir en argument un nom de fichier et renvoyer TODO:
 * convertir l'array String[] renvoyé par parseKeys en ArrayList dans une autre
 * méthode ? ou dans le package utils ? avec des méthodes sur les
 * array/arrayList ? TODO: convertir tous les chiffres du fichier CSV avec
 * ParseInt();
 * 
 */
