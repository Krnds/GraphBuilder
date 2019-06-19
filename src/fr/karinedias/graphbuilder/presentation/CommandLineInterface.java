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
	static Scanner sc; // TODO: essayer sans initialisation
	static int choice; // le choix fait par l'utilisateur représenté par un int

	public CommandLineInterface() {
		this.filename = fileName(); // source de bugs ?
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
		Scanner scanner = null;

		do {
			try {
				// Ici, un "try with ressources" pour éviter que le Scanner soit toujours ouvert
				// après
				scanner = new Scanner(System.in);
				System.out.println("Quel est le nom du fichier CSV ?");
				f = scanner.next();

				if (correctName(f)) {
					correct = true;
				} else {
					throw new CSVFormatException();
				}
			} catch (CSVFormatException exc) {

				System.out.println("Le fichier doit être de type .csv");

			} finally {
				if (scanner != null)
					scanner.close();
			}
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

	// méthodes du menu :

	public static void csvFileChoice() {

		do {
			try {
				sc = new Scanner(System.in);
				System.out.println(
						"Que souhaitez-vous faire ?" + "\n\t 1. Lire un fichier CSV " + "\n\t 2. Quitter le menu");
				choice = sc.nextInt(); // TODO: utiliser le scanner de la var de classe ??

				if (choice == 1) {
					// lance la lecture du CSV grâce à la méthode readCSV
					graphChoice();
				} else if (choice != 2) {
					// tous les autres cas où l'utilisateur ne souhaite pas lire un fichier ou
					// quitter le menu :
					throw new InputMismatchException();
				}
			} catch (InputMismatchException exc) {
				// TODO: appeler une exception personnalisée
				exc.getMessage();
				exc.printStackTrace();
			} finally {
				sc.close(); 
			}

		} while (choice != 2); // TODO: correct ?

		System.out.println("Au revoir"); // TODO: faire un truc en cas de menu quitté ?
		System.exit(0);
	}

	public static void graphChoice() {

		CommandLineInterface cli = new CommandLineInterface();

		csvFileChoice();
//		try {
			sc = new Scanner(System.in);
		System.out.println("Quel est le type de graphique que vous voulez avoir ?" + "\n\t 1. Diagramme en barres "
				+ "\n\t2. Diagramme à points");
//		} catch (CSVFormatException exc) {
//			
//		}
		if (sc.hasNextInt())
			System.out.println("Le scanner fonctionne avec hasNextInt !");
		if (sc.nextInt() == 1) {
			Graph graph = new Graph();
			BarChart barchart = new BarChart(graph);
		}
		sc.close();
	}
	
	public static void menuChoice() {
		
		String init = "Que voulez-vous faire ?";
		System.out.println(init
				+ "\n\t1. Lire un autre fichier CSV"
				+ "\n\t 2. Afficher un graphique"
				+ "\n\t 3. Modifier les paramètres"
				+ "\n\t 4. Quitter le menu");
	}
	
	public static void parametersBarChart() {
		
		String init = "Voici les paramètres du graphique en barre qui peuvent être modifiés :";
		System.out.println(init
				+ "\n\t 1. Couleur"
				+ "\n\t 2. Contour"
				+ "\n\t 1. Largeur des barres"
				+ "\n\t 3. Hauteur du graphique"
				+ "\n\t 3. Largeur du graphique");
		
		
	}
	
	public static void parametersLineChart() {
		
		String init = "Voici les paramètres du graphique de points qui peuvent être modifiés :";
		System.out.println(init
				+ "\n\t 1. Couleur"
				+ "\n\t 2. Contour"
				+ "\n\t 3. Hauteur du graphique"
				+ "\n\t 3. Largeur du graphique");
		
		
	}


	public static void main(String[] args) {
		csvFileChoice();
	}

}
