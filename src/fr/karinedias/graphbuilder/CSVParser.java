
package fr.karinedias.graphbuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.karinedias.graphbuilder.utils.*;

public class CSVParser extends CSVReader {
	

	private String fileName;
	

	public CSVParser(String file) {
		super();
		this.fileName = file;
	}
	
	
	public static void main(String[] args) throws IOException {

		/*
		 * TESTS !
		 */
		CSVParser myfile = new CSVParser("test.csv");
		System.out.println(myfile.getPoints());

		
	}
	
	
	//à quoi sert cette méthode ?
	public String FileName() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom du fichier ?");
		String f = sc.nextLine();
		if (correctName(f)) {
			System.out.println("Votre fichier est bien un CSV :)");
		} else {
			System.out.println("Nom du fichier incorrect !");
		}
		
		return f;
	}
	
	public String getFileName() {
		
		return this.fileName;
	}


	// méthode qui récupère tous les champs du fichier CSV
	public String[] parseKeys() throws IOException {

		String[] keys = new String[10]; //pourquoi 10 ?
		String firstLine = null;

		BufferedReader br = getBufferedReader();
		firstLine = br.readLine();

		keys = firstLine.replaceAll("\"[^\\\"]*", "").split(",");

		br.close();
		return keys;

	}
	
	
	public ArrayList<String> getPoints () throws IOException {
		
		ArrayList<String> points = new ArrayList<String>();
		BufferedReader br = getBufferedReader();
		String line = br.readLine();
		
		String defaultPath = System.getProperty("user.dir") + File.separator + "csv" + File.separator;
		String csv = defaultPath + (FileName()); //ou utiliser this.fileName ou encore getFileName ?
		File csvfile = new File(csv);
		
		List<String> lines1 = Files.readAllLines(csvfile.toPath(), StandardCharsets.UTF_8); 
		for (String values : lines1) { 
		   String[] array = values.split(","); 
		   points.add(array[1]);
		   System.out.println(array[1]); 
		
		
		
		//TODO: comptage de lignes pour l'axe x OK
		//TODO: compter les "points" cad les valeurs des données : extraire les valeurs de la 2eme colonne
		//TODO: ajouter les valeurs de la 1ere colonne en tant que valeurs sur l'axe x correspondant à chaque barre
		
		}
		return points;
	}
	
	
	public ArrayList<String> getData() throws IOException {
		return getPoints();
	}
	
	public static void parseData() {
		
		
	}
	
	
	
	public BufferedReader getBufferedReader() throws FileNotFoundException {
		
		String defaultPath = System.getProperty("user.dir") + File.separator + "csv" + File.separator;
		BufferedReader br = new BufferedReader(new FileReader(defaultPath + fileName));
		 
		return br;
		
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
