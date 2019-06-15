package fr.karinedias.graphbuilder.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.karinedias.graphbuilder.BarChart;
import fr.karinedias.graphbuilder.exceptions.CSVFormatException;
import fr.karinedias.graphbuilder.model.Graph;

public class CommandLineInterface {

	/*
	 * variable d'instance :
	 */

	private String filename;
	static final Scanner sc = new Scanner(System.in);

	public CommandLineInterface() {
		this.filename = fileName();
	}
	
	

	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	// méthode servant à regarder que le nom du fichier se fini bien en ".csv"
	public String fileName() {

		boolean correct = false;
		String f = "null";

		do {
			System.out.println("Nom du fichier ?");
			try {
				f = sc.next();
				if (!correctName(f)) {
					
					throw new CSVFormatException();
				}
			} catch (CSVFormatException e) {
				System.out.println("Le fichhier doit être de type .csv");
			}

			sc.close();

			return f;
		} while (!correct);

	}

	// méthode qui vérifie que le nom du fichier se termine bien en ".csv"
	public static boolean correctName(String str) {

		Pattern p = Pattern.compile("(\\.(csv)){1}$");
		Matcher m = p.matcher(str);
		boolean correct = m.find();

		return correct;
	}
	

	
	//méthodes du menu :

	public static void choice() {

		boolean correct = false;
		do {
			try {
				System.out.println("Que souhaitez-vous faire ?\n \t 1. Lire un fichier CSV \n\t 2. Quitter le menu");

				int reponse = sc.nextInt();
				if ((Integer) reponse instanceof Integer) {
					correct = true;
					if (reponse == 1)
						readCSV(); // correct ?
					else if (reponse == 2)
						System.exit(0); // moyen de quitter le menu proprement ?
				} else {
					throw new InputMismatchException();
				}
			} catch (InputMismatchException exc) {

				exc.getMessage();
				exc.printStackTrace();
				correct = false;

			}
		} while (!correct);
	}

	public static void readCSV() {

		CommandLineInterface cli = new CommandLineInterface();
		cli.fileName();
		System.out.println(
				"Quel est le type de graphique que vous voulez avoir ?\n\t1. Diagramme en barres \n\t2. Diagramme à points");
		if (sc.nextInt() == 1) {
			Graph graph = new Graph();
			BarChart barchart = new BarChart(graph);
		}
	}

	public static void main(String[] args) {
		choice();
	}

}
