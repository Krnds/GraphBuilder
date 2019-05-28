package fr.karinedias.graphbuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import fr.karinedias.graphbuilder.utils.CSVReader;

public class App {

	public static void main(String[] args) throws IOException {
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
}