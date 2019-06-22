package fr.karinedias.graphbuilder.presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.karinedias.graphbuilder.BarChart;
import fr.karinedias.graphbuilder.CSVParser;
import fr.karinedias.graphbuilder.LineChart;
import fr.karinedias.graphbuilder.exceptions.CSVFormatException;
import fr.karinedias.graphbuilder.exceptions.IncorrectChoiceException;
import fr.karinedias.graphbuilder.model.Graph;
import fr.karinedias.graphbuilder.utils.CSVReader;
import fr.karinedias.graphbuilder.utils.SVGWriter;

public class CommandLineInterface {

	/*
	 * variable d'instance :
	 */

	private static String filename;
	static Scanner sc = new Scanner(System.in);
	static CSVParser csvParser = new CSVParser(getFilename()); //????
	static int choice; // le choix fait par l'utilisateur représenté par un int
	static int graphType = 0;

	public CommandLineInterface() {
		this.filename = fileName(); // source de bugs ?
	}

	public static String getFilename() {
		return filename; // TODO: this.filename ?
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	// méthode servant à regarder que le nom du fichier se fini bien en ".csv"
	public String fileName() {

		boolean correct = false;
		String f = null;

		while (!correct) {
			try {
				System.out.println("Quel est le nom du fichier CSV ?");
				f = sc.next();

				if (CSVReader.correctName(f) && CSVReader.fileExists(f)) {
					correct = true;
				} else {
					throw new CSVFormatException();
				}

			} catch (CSVFormatException exc) {

				System.out.println("Votre fichier doit être de type .csv et/ou être présent dans le dossier /csv");
				correct = false;

			}

		}

		return f;

	}

	// méthode qui vérifie que le nom du fichier se termine bien en ".csv"
	public static boolean correctName(String str) throws FileNotFoundException {

		Pattern p = Pattern.compile("(\\.(csv)){1}$");
		Matcher m = p.matcher(str);
		boolean correct = m.find();

		return correct;
	}

	// méthodes du menu :
	public static void readFileChoice() throws IOException {


		do {
				System.out.println(
						"Que souhaitez-vous faire ?" + "\n\t 1. Lire un fichier CSV" + "\n\t 2. Quitter le menu");
				choice = sc.nextInt();
				sc.nextLine();
				if (choice == 1) {

					CommandLineInterface cli = new CommandLineInterface(); // demande le nom du fichier et vérifie que
					// c'est un fichier csv et qu'il existe
					graphChoice();

				} else if (choice == 2) {
					quit();
				}
			

		} while (choice != 2 || choice != 1);
		
		// tous les autres cas où l'utilisateur ne souhaite pas lire un fichier ou
		// quitter le menu :
		throw new IncorrectChoiceException("Choix incorrect");

	}

	public static void graphChoice() throws IOException {

		System.out.println("Quel type de graphique voulez-vous avoir ?" + "\n\t1. Diagramme en barres "
				+ "\n\t2. Diagramme à points " + "\n\t3. Quitter le menu");

		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			graphType = 1;
			
			Graph graph = csvParser.buildGraph();
			 BarChart barChart = new BarChart(graph);
			 barChart.draw();
			
			menuChoice();
			break;

		case 2:
			graphType = 2;
			Graph linegraph = new Graph();
			LineChart linechart = new LineChart(linegraph);
			menuChoice();
			break;

		case 3:
			quit();
			break;
			
		default:
			System.out.println("Entrée invalide");

		}
	}

	public static void main(String[] args) throws IOException {
		readFileChoice();

	}

	// TODO: duplication de la méthode de CSVReader
	public boolean fileExists(String f) {

		String absolutePath = new File(f).getAbsoluteFile().getParentFile() + File.separator + "csv" + File.separator
				+ f;

		File absoluteFile = new File(absolutePath);
		return absoluteFile.exists();

	}

	public static void quit() {

		System.out.println("Au revoir !");
		System.exit(0);
	}

	public static void menuChoice() throws IOException {

		String init = "Que voulez-vous faire ?";
		System.out.println(init + "\n\t1. Lire un autre fichier CSV" + "\n\t2. Afficher l'emplacement du graphique"
				+ "\n\t3. Modifier les paramètres" + "\n\t4. Quitter le menu");
		
		choice = sc.nextInt();
		sc.nextLine();
		
		switch (choice) {
		case 1:
			//répétition de CSVFileChoice :
			CommandLineInterface cli = new CommandLineInterface(); // demande le nom du fichier et vérifie que
			// c'est un fichier csv et qu'il existe
			graphChoice();
			break;
			
		case 2:
			//créer une méthode pour afficher l'emplacement
			System.out.println("Emplacement du fichier SVG de sortie : " + SVGWriter.getPath());
			break;
			
		case 3:
			if (graphType == 1) {
				parametersBarChart();
			} else if (graphType == 2) {
				parametersLineChart();
			} else {
				throw new IncorrectChoiceException("Le graphique doit être de type BarChart ou LineChart.");
			}
			
		case 4:
			quit();

		default:
			 System.out.println("Entrée invalide.");
		}
	}

	public static void parametersBarChart() {

		String init = "Voici les paramètres du graphique en barre qui peuvent être modifiés :";
		System.out.println(init + "\n\t1. Couleur" + "\n\t2. Contour" + "\n\t3. Largeur des barres"
				+ "\n\t4. Hauteur du graphique" + "\n\t5. Largeur du graphique" + "\n\t6.Quitter le menu");
		choice = sc.nextInt();
		sc.nextLine();
		
		switch (choice) {
		case 1:
			UserRequests.barColor();
			break;
		case 2:
			UserRequests.strokeWidth();
			break;
		case 3:
			UserRequests.barWidth();
			break;
		case 4:
			UserRequests.graphHeight();
			break;
		case 5:
			UserRequests.graphWidth();
			break;
		case 6:
			quit();
			break;

		default:
			System.out.println("Entrée invalide.");
		}

	}

	public static void parametersLineChart() {

		//TODO à écrire et à finir
		String init = "Voici les paramètres du graphique de points qui peuvent être modifiés :";
		System.out.println(init + "\n\t 1. Couleur" + "\n\t 2. Contour" + "\n\t 3. Hauteur du graphique"
				+ "\n\t 3. Largeur du graphique");

	}

}
