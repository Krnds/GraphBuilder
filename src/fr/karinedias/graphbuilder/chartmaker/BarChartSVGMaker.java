package fr.karinedias.graphbuilder.chartmaker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import fr.karinedias.graphbuilder.model.BarChart;
import fr.karinedias.graphbuilder.utils.SVGUtils;

public class BarChartSVGMaker {

	/*
	 * CLASSE POUR LA CONSTRUCTION DES DIAGRAMMES EN BARRE
	 */

	/*
	 * variables d'instance :
	 */

	private String outputFile = "barchart.svg";
	private BarChart barChart;

	/*
	 * constructeur :
	 */

	public BarChartSVGMaker(BarChart barChart) {
		this.barChart = barChart;
	}

	public void make() throws IOException {

		// déclaration du PrinterWriter pour la construction du SVG
		PrintWriter pw = new PrintWriter(this.outputFile);
		String svgHeader = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"" + barChart.getWidth()
				+ "\" height=\"" + barChart.getHeight() + "\">";
		pw.print(svgHeader);

		// Position X des barres sur l'axe des abscisses dans l'arrayList xPositionbar
		int nPoints = barChart.getDataPoints().size();
		ArrayList<Integer> xPositionbar = new ArrayList<Integer>(nPoints);
		int barWidth = barChart.getBarWidth();
		xPositionbar.addAll(SVGUtils.counter(nPoints, barWidth));

		// récupérer la valeur de optimalWidth et optimalHeight de la classe SVGUtils
		int optWidth = SVGUtils.getOptimalWidthBarChart(this.barChart);
		int optHeight = SVGUtils.getOptimalHeightBarChart(this.barChart);

		for (int i = 0; i < nPoints - 1; i++) {
			pw.println("<rect x=\"" + xPositionbar.get(i) + "\" y=\"350\" width=\"" + optWidth + "\" height=\""
					+ (barChart.getIntValues().get(i) * optHeight) + "\" style=\"fill:" + barChart.getColor()
					+ ";stroke-width:" + barChart.getStrokeWidth() + ";stroke:rgb(0,0,0)\" transform=\"translate(0,-"
					+ (barChart.getIntValues().get(i) * optHeight) + ")\" />");
		}

		/*
		 * Axes du graphique :
		 */

		// axe X : abscisses
		pw.println(
				"<line x1=\"850\" y1=\"350\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\"> </line>");

		// axe Y : ordonnées
		pw.println(
				"<line x1=\"30\" y1=\"20\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\" marker-start=\"url(#arrow)\" marker-end=\"url(#arrow)\"/>");

		/*
		 * Légendes sur l'axe des abscisses correspondant aux données de la 1ère colonne
		 * du CSV :
		 */

		int positionOfLabels = (optWidth / nPoints);
		for (int i = 0; i < nPoints - 1; i++) {
			pw.println("<text id=\"légende-abcisse \" x=\"" + (xPositionbar.get(i) + positionOfLabels)
					+ "\" y=\"360\" font-size=\"8\" text-anchor=\"center\">" + barChart.getLabels().get(i) + "</text>");
		}

		/*
		 * Mettre la légende sur le graph (rectangle plein + texte indiquant la couleur)
		 * :
		 */
//		
//		ArrayList<String> legends = new ArrayList<String>();
//		legends = (ArrayList<String>) barChart.getLabels();

		pw.println(
				"<rect x=\"655\" y=\"12\" width=\"20\" height=\"10\" fill=\""+ barChart.getColor() + "\" stroke-width=\"1\" stroke=\"rgb(0,0,0)\">  </rect>");
		pw.println("<text id=\"légende du graphique\" x=\"680\" y=\"20\" font-size=\"12\">"
				+ barChart.getCaptions().get(1) + "</text>");

		pw.println("</svg>");
		pw.close();
	
	}
}
