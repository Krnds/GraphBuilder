package fr.karinedias.graphbuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import fr.karinedias.graphbuilder.utils.SVGUtils;

public class LineChart implements Chart {

	/*
	 * CLASSE POUR LA CONSTRUCTION DES GRAPHIQUES LINÉAIRES
	 */

	/*
	 * variables d'instance :
	 */

	private static String svgHeader = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"800\" height=\"400\">";
	private String outputFile = "linechart.svg"; // TODO : demander à l'utilisateur son nom
	private CSVParser csvFile = new CSVParser();
	private LineChart svgFile = new LineChart(this.csvFile); // TODO: nécessaire ? Correct ?

	/*
	 * constructeur :
	 */

	public LineChart(CSVParser csvFile) {
		this.csvFile = csvFile;
		LineChart svgFile = new LineChart(this.csvFile); // TODO: nécessaire ? correct ou pas ?
	}

	@Override
	public void axis() throws IOException {

	}

	@Override
	public ArrayList<String> points() throws IOException {

		// ArrayList comprenant tous les points du graphique sous forme de chaîne de
		// caractères
		ArrayList<String> points = new ArrayList<String>();
		points = this.csvFile.getPoints();

		return points;
	}

	@Override
	public ArrayList<String> legend() throws IOException {

		// ArrayList comprenant la ou les légendes du graphique
		ArrayList<String> legend = new ArrayList<String>();
		legend = this.csvFile.getKeys();

		return legend;
	}

	@Override
	public ArrayList<String> labels() throws IOException {

		// ArrayList comprenant toutes les données affichées sur l'axe des abscisses
		ArrayList<String> labels = new ArrayList<String>();
		labels = this.csvFile.getAxisX();

		return labels;
	}

	public void construction() throws IOException {

		// TODO: enlever l'argument graph

		// déclaration du PrinterWriter pour la construction du SVG
		PrintWriter pw = new PrintWriter(this.outputFile);
		pw.print(svgHeader);

		/*
		 * PRINTING POINTS OF CSV TO THE GRAPH :
		 */

		// Position des points sur l'axe des abscisses dans l'arrayList
		ArrayList<Integer> xPositionbar = new ArrayList<Integer>(svgFile.points().size());
		int width = 50; // TODO: à demander à l'utilisateur, cf classe UserRequests
		xPositionbar.addAll(SVGUtils.counter(svgFile.points().size(), width));

pw.println("<polyline points=\"15,80 29,50 43,60 57,30 71,40 85,15\" fill=\"none\" stroke=\"grey\" marker-start=\"url(#dot) \" marker-mid=\"url(#dot)\"  marker-end=\"url(#dot)\" />");

		/*
		 * AXIS :
		 */

		// axe X : abscisses
		pw.println(
				"<line x1=\"850\" y1=\"350\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\"> </line>");

		// axe Y : ordonnées
		pw.println(
				"<line x1=\"30\" y1=\"20\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\" marker-start=\"url(#arrow)\" marker-end=\"url(#arrow)\"/>");


		/*
		 * LABELS OF POINTS :
		 */

		int nPoints = SVGUtils.getNumberOfBars(svgFile.points()); // nombre de points du graphique

		

		/*
		 * LEGENDS :
		 */
		ArrayList<String> legends = new ArrayList<String>();
		legends = svgFile.legend();
		pw.println(
				"<rect x=\"655\" y=\"12\" width=\"20\" height=\"10\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\">  </rect>");
		pw.println(
				"<text id=\"légende du graphique\" x=\"680\" y=\"20\" font-size=\"12\">" + legends.get(1) + "</text>");

		pw.println("</svg>");
		pw.close();
	}

}
