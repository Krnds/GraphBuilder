
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
		System.out.println(myfile.getAxisX());
		System.out.println(myfile.getKeys());
		

	}

	// à quoi sert cette méthode ?
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
	public ArrayList<String> getKeys() throws IOException {

		String[] keysArray = new String[10]; // pourquoi 10 ?
		ArrayList<String> keys = new ArrayList<String>();
		String firstLine = null;

		BufferedReader br = getBufferedReader();
		firstLine = br.readLine();

		keysArray = firstLine.replaceAll("\"[^\\\"]*", "").split(",");
		//transfer all the data from keysArray to the ArrayList keys :
		for (int i = 0; i < keysArray.length; i++) {
			keys.add(keysArray[i]);
		}

		br.close();
		return keys;

	}
	
	//method for gathering all the 1st column (x axis) data :
	public ArrayList<String> getAxisX() throws IOException {
		
		ArrayList<String> axisX = new ArrayList<String>();
		String defaultPath = System.getProperty("user.dir") + File.separator + "csv" + File.separator;
		String csv = defaultPath + this.fileName; // ou utiliser this.fileName ou encore getFileName ?
		File csvfile = new File(csv);

		List<String> lines1 = Files.readAllLines(csvfile.toPath(), StandardCharsets.UTF_8);

		for (String values : lines1) {
			String[] array = values.split(",");
			axisX.add(array[0]);

		}

		axisX.remove(0);
		return axisX;
		
	}

	public ArrayList<String> getPoints() throws IOException {

		ArrayList<String> points = new ArrayList<String>();

		String defaultPath = System.getProperty("user.dir") + File.separator + "csv" + File.separator;
		String csv = defaultPath + (FileName()); // ou utiliser this.fileName ou encore getFileName ?
		File csvfile = new File(csv);

		List<String> lines1 = Files.readAllLines(csvfile.toPath(), StandardCharsets.UTF_8);

		for (String values : lines1) {
			String[] array = values.split(",");
			points.add(array[1]);

		}

		points.remove(0);
		return points;
	}

	//utile ?
	public ArrayList<String> getData() throws IOException {
		return getPoints();
	}

//TODO: refactoriser le code dans la méthode getPoints pour utiliser cette méthode-ci au lieu de refaire la lecture du BufferedReader;
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

// TODO: comptage de lignes pour l'axe x OK
// TODO: compter les "points" cad les valeurs des données : extraire les valeurs
// de la 2eme colonne OK, nb de points = points.length();
// TODO: ajouter les valeurs de la 1ere colonne en tant que valeurs sur l'axe x
// correspondant à chaque barre
