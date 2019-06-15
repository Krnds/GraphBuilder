package fr.karinedias.graphbuilder;

import java.io.IOException;
import java.util.List;

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

	private static CSVParser csvParser = new CSVParser(csvName4);


	public static void main(String[] args) throws IOException {
		Graph graph = csvParser.buildGraph();
//		BarChart barChart = new BarChart(graph);
//		barChart.draw();
		
		LineChart lineChart = new LineChart(graph);
		lineChart.draw();
	}

}

