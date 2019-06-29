package fr.karinedias.graphbuilder.presentation;

import java.io.IOException;
import java.util.Scanner;
import fr.karinedias.graphbuilder.chartmaker.BarChartSVGMaker;
import fr.karinedias.graphbuilder.chartmaker.LineChartSVGMaker;
import fr.karinedias.graphbuilder.exceptions.CSVFormatException;
import fr.karinedias.graphbuilder.exceptions.IncorrectChoiceException;
import fr.karinedias.graphbuilder.model.BarChart;
import fr.karinedias.graphbuilder.model.Graph;
import fr.karinedias.graphbuilder.model.LineChart;
import fr.karinedias.graphbuilder.parser.CSVParser;
import fr.karinedias.graphbuilder.utils.CSVReader;

public class Presentation {

	private static String csvName = "test.csv";
	private static CSVParser csvParser = new CSVParser(csvName);
	private static Scanner sc = new Scanner(System.in);
	
	
	public static void main(String[] args) throws IOException {

		int choice;

		do {
			System.out
					.println("Que souhaitez-vous faire ?" + "\n\t 1. Lire un fichier CSV" + "\n\t 2. Quitter le menu");
			choice = sc.nextInt();
			sc.nextLine();
			if (choice == 1) {
				String filename = readCSV();
				csvName = filename;
				graphChoice();

			} else if (choice == 2) {
				System.exit(0);
			}

		} while (choice != 2 || choice != 1);

		// tous les autres cas où l'utilisateur ne souhaite pas lire un fichier ou
		// quitter le menu :
		throw new IncorrectChoiceException("Choix incorrect");

	}

	// // méthode servant à regarder que le nom du fichier se fini bien en ".csv"
	public static String readCSV() {

		boolean correct = false;
		String f = null;

		while (!correct) {
			try {
				System.out.println("Quel est le nom du fichier CSV ?");
				f = sc.nextLine();

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
	
	

	public static void graphChoice() throws IOException {

		Graph graph = csvParser.buildGraph();

		System.out.println("Quel type de graphique voulez-vous avoir ?" + "\n\t1. Diagramme en barres "
				+ "\n\t2. Diagramme de points " + "\n\t3. Quitter le menu");

		int choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
		case 1:
			BarChart barChart = new BarChart();
			barChart.setCaptions(graph.getCaptions());
			barChart.setDataPoints(graph.getDataPoints());

			customizeChart(barChart);
			BarChartSVGMaker barChartSVGMaker = new BarChartSVGMaker(barChart);
			barChartSVGMaker.make();

			break;

		case 2:
			LineChart lineChart = new LineChart();
			lineChart.setCaptions(graph.getCaptions());
			lineChart.setDataPoints(graph.getDataPoints());

			customizeChart(lineChart);

			LineChartSVGMaker lineChartSVGMaker = new LineChartSVGMaker(lineChart);
			lineChartSVGMaker.make();
			break;

		default:
			System.out.println("Entrée invalide");
			throw new IncorrectChoiceException();
		}

	}

	private static void customizeChart(BarChart barChart) {
		System.out.println("Veuillez indiquer la couleur en anglais/héxadécimal ou format rgb");
		String color = sc.nextLine();

		System.out.println("Quelle est l'épaisseur des contours souhaitée ? (en pixels)");
		int strokeWidth = sc.nextInt();
		sc.nextLine();

		System.out.println("Quelle est la largeur des barres souhaitée (en pixels) ?");
		int barWidth = sc.nextInt();
		sc.nextLine();

		System.out.println("Quelle est la hauteur du graphe souhaitée (en pixels) ?");
		int height = sc.nextInt();
		sc.nextLine();

		System.out.println("Quelle est la largeur du graphe souhaitée (en pixels) ?");
		int width = sc.nextInt();
		sc.nextLine();

		barChart.setColor(color);
		barChart.setStrokeWidth(strokeWidth);
		barChart.setBarWidth(barWidth);
		barChart.setHeight(height);
		barChart.setWidth(width);
	}

	private static void customizeChart(LineChart lineChart) {
		System.out.println("Veuillez indiquer la couleur en anglais/héxadécimal ou format rgb");
		String color = sc.nextLine();

		System.out.println("Quelle est l'épaisseur des contours souhaitée ? (en pixels)");
		int strokeWidth = sc.nextInt();
		sc.nextLine();

		System.out.println("Quelle est la hauteur du graphe souhaitée (en pixels) ?");
		int height = sc.nextInt();
		sc.nextLine();

		System.out.println("Quelle est la largeur du graphe souhaitée (en pixels) ?");
		int width = sc.nextInt();
		sc.nextLine();

		lineChart.setColor(color);
		lineChart.setStrokeWidth(strokeWidth);
		lineChart.setHeight(height);
		lineChart.setWidth(width);
	}

}
