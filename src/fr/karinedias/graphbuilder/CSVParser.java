
package fr.karinedias.graphbuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fr.karinedias.graphbuilder.utils.*;

public class CSVParser extends CSVReader {

	public CSVParser() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom du fichier ?");
			CSVReader.fileName = sc.nextLine();
			if (correctName(CSVReader.fileName)) {
				System.out.println("Nom du fichier correct = " + CSVReader.fileName);	
		} else {
			System.out.println("Nom du fichier incorrect !");
		}
		try {
			parseKeys(CSVReader.fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<String> parseKeys(String filename) throws IOException {
		//récupère tous les champs du fichier CSV
		ArrayList<String> keys = new ArrayList<String>(2); //pour ne pas utiliser trop de mémoire CPU
		
		String firstLine = null;
		//chemin de base du fihchier à chercher :
		String defaultPath = System.getProperty("user.dir") + File.separator + "csv" + File.separator;
		BufferedReader br = new BufferedReader(new FileReader(defaultPath + filename));
		firstLine = br.readLine();
		
		System.out.println(firstLine);
		String[] keysArray = new String[10];
		keysArray = firstLine.split(",");
		System.out.println(keysArray.toString());
		
		return keys;
		
	}
}


/*
 * TODO: méthode pour avoir en argument un nom de fichier et renvoyer
 * 
 * 
*/
