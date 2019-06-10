package fr.karinedias.graphbuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.*;

import com.sun.xml.internal.fastinfoset.stax.events.StAXEventReader;

import fr.karinedias.graphbuilder.utils.CSVReader;

public class App {
	/*
	 * Classe de tests, à supprimer une fois le projet terminé
	 */

	public static void test() throws IOException {
		String defaultPath = System.getProperty("user.dir") + File.separator + "csv" + File.separator;
		System.out.println(defaultPath);

		String[] keys = new String[10];

		Scanner sc = new Scanner(System.in);
		System.out.println("Nom du fichier ?");
		CSVReader.fileName = sc.nextLine();
		BufferedReader br = new BufferedReader(new FileReader(defaultPath + CSVReader.fileName));
		String firstLine = br.readLine();

		String t = sc.nextLine();
		String[] tab = t.split(","); // à modifier, ne fonctionne pas
		for (int i = 0; i < tab.length; i++) {
			if (t.matches("\".*\"")) {
				tab[i] = tab[i].replaceAll("\".*\"", "");
				System.out.println(tab[i]);
			}

		}
	}

	public static void main(String[] args) throws IOException {

//		String defaultPath = System.getProperty("user.dir") + File.separator + "csv" + File.separator;
//		BufferedReader br1 = new BufferedReader(new FileReader(defaultPath + "test.csv"));
//
//		ArrayList<Integer> points = new ArrayList<Integer>();
//		BufferedReader br = br1;
//		String line = br.readLine();
//		
//
//
//		int nLines = 0;
//		while ((line = br.readLine()) != null) {
//			
//			nLines++;
//			line += br.readLine();
//			//System.out.println(line); //pourquoi affiche une ligne vide ? la boucle devrait s'arrêter avant ?
//			String[] s1 = line.split("(\r\n|\r|\n)", -1);
//			
//			for (int i = 0; i < 1; i++) {
//				System.out.println(s1[i]);
//			}
//			
//		}
//		
//		//TEST FROM THE INTERNET :
//
//		
//
//		
//
//		System.out.println(nLines);
//		int[] dataArray = new int[nLines];
//
//		// code pour récupérer les data de la 2eme colonne :
//		String[] columns = new String[2];
		
//if (line != null) {
//			String[] s1 = line.split(",");
//
//}

//		for (int i = 0; i <= columns.length; i++) {
//			dataArray[i] = Integer.parseInt(columns[i]);
//			points.add(dataArray[i]);
//		}
//		br.close();
//
//		for (int j = 0; j < points.size(); j++) {
//			System.out.println(poin		br.close();ts.get(j));
//		}
		
		
		
		//test
		
		String str = "12";
		ArrayList<String> test = new ArrayList<String>();
		test.add("4");
		test.add("9");
		ArrayList<Integer> fin = new ArrayList<Integer>();
		for (int i = 0; i< test.size(); i++) {
		fin.add(Integer.valueOf(Integer.parseInt(test.get(i))));
		}
		
		int a = Integer.parseInt(test.get(0));
		
	}
	
	
	//TODO: utiliser read pour prendre en compte les retour charriots (carriage return)
}