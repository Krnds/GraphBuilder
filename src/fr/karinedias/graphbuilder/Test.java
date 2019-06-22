package fr.karinedias.graphbuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import fr.karinedias.graphbuilder.model.DataPoint;
import fr.karinedias.graphbuilder.model.Graph;

public class Test {

	/*
	 * Variables d'instance:
	 */

	private static String csvName = "test.csv";
	private static String csvName2 = "test2.csv";
	private static String csvName3 = "";
	private static String csvName4 = "test3.csv";

	private static CSVParser csvParser = new CSVParser(csvName);

	public static void main(String[] args) throws IOException {
		 Graph graph = csvParser.buildGraph();
		 BarChart barChart = new BarChart(graph);
		 barChart.draw();
		
		 LineChart lineChart = new LineChart(graph);
		 lineChart.draw();



	}

	public boolean fileExists() throws IOException {

		File f = new File(getCSVPath());

		return f.isFile();

	}
//
//	public String getCSVPath() {
//
//		String directory = System.getProperty("user.dir");
//		String absolutePath = directory + File.separator + "csv" + File.separator + ask();
//
//		return absolutePath;
//	}

	private static String getCSVPath() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Fichier ?");

		File csvFile = new File(sc.next());
		String absolutePath = csvFile.getAbsoluteFile().getParentFile() + File.separator + "csv" + File.separator + csvFile;
		System.out.println(absolutePath);
		return absolutePath;

	}

}
