package fr.karinedias.graphbuilder.chartmaker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import fr.karinedias.graphbuilder.model.LineChart;
import fr.karinedias.graphbuilder.utils.SVGUtils;

public class LineChartSVGMaker {

	private String outputFile = "linechart.svg";
	private LineChart lineChart;

	public LineChartSVGMaker(LineChart lineChart) {
		this.lineChart = lineChart;
	}

	public void make() throws IOException {

		// déclaration du PrinterWriter pour la construction du SVG
		PrintWriter pw = new PrintWriter(this.outputFile);
		String svgHeader = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"" + lineChart.getWidth()
				+ "\" height=\"" + lineChart.getHeight() + "\">";
		pw.print(svgHeader);

		/*
		 * Affichage des points du graphe
		 */

		// Position des points sur l'axe des abscisses dans l'arrayList
		int nPoints = lineChart.getDataPoints().size(); // nombre de points à représenter
		ArrayList<Integer> xPositionbar = new ArrayList<Integer>(nPoints);
		int width = 40;
		xPositionbar.addAll(SVGUtils.counter(nPoints, width));


		ArrayList<String> points = new ArrayList<String>(nPoints); // ArrayList pour stocker toutes les valeurs des
																	// points du graphe
		points.add(lineChart.getIntValues().toString());

		pw.println("<polyline points=\"");

		// AUTRE TEST POUR LES POINTS :

		int graphHeight = lineChart.getHeight(); //par défaut : 350px
		// boucle pour l'affichage de chaque point
		for (int i = 0; i < nPoints; i++) {
			pw.print(xPositionbar.get(i) + ","
					+ (graphHeight - (lineChart.getIntValues().get(i) * SVGUtils.getOptimalHeightLineChart(lineChart))) + " ");
		}

		pw.println(
				"\" fill=\"none\" stroke=\""+ lineChart.getColor() + "\" stroke-width=\"" + lineChart.getStrokeWidth() + "\" marker-start=\"url(#dot)\" marker-mid=\"url(#dot)\" marker-end=\"url(#dot)\" />");
		/*
		 * Dessin des axes du graphique :
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

		int optWidth = SVGUtils.getOptimalWidthLineChart(lineChart);
		int positionOfLabels = (optWidth / nPoints);
		for (int i = 0; i < nPoints - 1; i++) {
			pw.println("<text id=\"légende-abcisse \" x=\"" + (xPositionbar.get(i) + positionOfLabels)
					+ "\" y=\"360\" font-size=\"8\" text-anchor=\"center\">" + lineChart.getLabels().get(i)
					+ "</text>");
		}

		/*
		 * LEGENDS :
		 */


		pw.println(
				"<rect x=\"655\" y=\"12\" width=\"20\" height=\"10\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\">  </rect>");
		pw.println("<text id=\"légende du graphique\" x=\"680\" y=\"20\" font-size=\"12\">"
				+ lineChart.getCaptions().get(1) + "</text>");

		pw.println("</svg>");
		pw.close();
	}

}
