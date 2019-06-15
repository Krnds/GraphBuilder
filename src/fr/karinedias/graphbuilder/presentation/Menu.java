package fr.karinedias.graphbuilder.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;
import fr.karinedias.graphbuilder.BarChart;
import fr.karinedias.graphbuilder.model.Graph;

public class Menu {

	static final Scanner sc = new Scanner(System.in);

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
