
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
	
	private String fileName;
	

	public CSVParser(String file) {
		// TODO Auto-generated constructor stub
		super();
		this.fileName = file;
	}
	
	public String FileName() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom du fichier ?");
		CSVReader.fileName = sc.nextLine();
		if (correctName(CSVReader.fileName)) {
			System.out.println("Nom du fichier correct = " + CSVReader.fileName);
		} else {
			System.out.println("Nom du fichier incorrect !");
		}
		
		return CSVReader.fileName;
	}
	
	public String getFileName() {
		
		return this.fileName;
	}

	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Nom du fichier ?");
//		
//		
//		String f = sc.nextLine();
//		
//		CSVReader.openCSV(f);
//		if (correctName(CSVReader.fileName)) {
//			System.out.println("Nom du fichier correct = " + CSVReader.fileName);
//		} else {
//			System.out.println("Nom du fichier incorrect !");
//		}
//		try {
//
//			ArrayList<String[]> test = new ArrayList<String[]>();
//			test.add(new String[10]);
//
//			
//			for (int i = 0; i < test.size(); i++) {
//				test.get(i);
//			}
//
//			int arrayLength = parseKeys(CSVReader.fileName).length;
//
//			System.out.println("Les données sont :");
//			for (int i = 0; i < arrayLength; i++) {
//				System.out.println(parseKeys(CSVReader.fileName)[i]);
//
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		/*
		 * TESTS !
		 */
		CSVParser myfile = new CSVParser("test.csv");
		System.out.println(myfile.getFileName());
		System.out.println("Les datas intéressantes : ");
		System.out.println(myfile.getPoints());
		
//		for (int i = 0; i < 2; i++) {
//		System.out.println(myfile.parseKeys()[i]);
//		}
		
		
		
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
	
	
	public ArrayList<Integer> getPoints () throws IOException {
		
		ArrayList<Integer> points = new ArrayList<Integer>();
		BufferedReader br = getBufferedReader();
		String line = br.readLine();
		
		
		int nLines = 0;
		while ((line != null)) {
			  nLines++;
			  line = br.readLine();
			}
		nLines = nLines - 1; //pour avoir le nimbre de lignes qu'il faut parser pour récupérer les data

		int[] dataArray = new int[nLines];
		
		//code pour récupérer les data de la 2eme colonne :
		String[] columns = new String[2];
		for (int i = 0; i <= 2; i++) {
		columns = line.split(",");
		}	
		for (int i = 0; i <= columns.length; i++) {
			dataArray[i] = Integer.parseInt(columns[i]);
			points.add(dataArray[i]);
		}
		
		
		
		
		//TODO: comptage de lignes pour l'axe x OK
		//TODO: compter les "points" cad les valeurs des données : extraire les valeurs de la 2eme colonne
		//TODO: ajouter les valeurs de la 1ere colonne en tant que valeurs sur l'axe x correspondant à chaque barre
		
		return points;
	}
	
	
	public ArrayList<Integer> getData() throws IOException {
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
