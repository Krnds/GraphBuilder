package fr.karinedias.graphbuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import fr.karinedias.graphbuilder.model.Graph;
import fr.karinedias.graphbuilder.presentation.UserRequests;
import fr.karinedias.graphbuilder.utils.SVGUtils;

public class BarChart {

	/* 
	 * CLASSE POUR LA CONSTRUCTION DES DIAGRAMMES EN BARRE
	 */

	/*
	 * variables d'instance :
	 */

	private static String svgHeader = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\""+ UserRequests.getWidth() + "\" height=\"400\">";
	private String outputFile = "karine.svg"; //TODO : demander à l'utilisateur son nom
	private Graph graph;
	private String color, strokeWidth, barWidth, width, height; //valeurs que l'utilisateur peut choisir
	
	/*
	 * constructeur :
	 */

	public BarChart(Graph graph) {
		this.graph = graph;
	}

	public void draw() throws IOException {
		
		//TODO: enlever l'argument graph

		// déclaration du PrinterWriter pour la construction du SVG
		PrintWriter pw = new PrintWriter(this.outputFile);
		pw.print(svgHeader);

		// Position X des barres sur l'axe des abscisses dans l'arrayList xPositionbar
		int nPoints = graph.getDataPoints().size();
		ArrayList<Integer> xPositionbar = new ArrayList<Integer>(nPoints);
		int width = 50; // TODO: à demander à l'utilisateur, cf classe UserRequests
		xPositionbar.addAll(SVGUtils.counter(nPoints, width));


		// récupérer la valeur de optimalWidth et optimalHeight de la classe SVGUtils
		int optWidth = SVGUtils.getOptimalWidth(this.graph);
		int optHeight = SVGUtils.getOptimalHeight(this.graph);

		for (int i = 0; i < nPoints - 1; i++) {
			pw.println("<rect x=\"" + xPositionbar.get(i) + "\" y=\"350\" width=\"" + optWidth + "\" height=\""
					+ (graph.getIntValues().get(i) * optHeight)
					+ "\" style=\"fill:" + UserRequests.getBarColor() + ";stroke-width:1;stroke:rgb(0,0,0)\" transform=\"translate(0,-"
					+ (graph.getIntValues().get(i) * optHeight) + ")\" />");
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
		 * Légendes sur l'axe des abscisses correspondant aux données de la 1ère colonne du CSV :
		 */

		int positionOfLabels = (optWidth / nPoints);
		for (int i = 0; i < nPoints - 1; i++) {
			pw.println("<text id=\"légende-abcisse \" x=\"" + (xPositionbar.get(i) + positionOfLabels)
					+ "\" y=\"360\" font-size=\"8\" text-anchor=\"center\">" + graph.getLabels().get(i) + "</text>"); // TODO: changer
		}

		/*
		 * Mettre la légende sur le graph (rectangle plein + texte indiquant la couleur) :
		 */
//		ArrayList<String> legends = new ArrayList<String>();
//		legends = graph.legend();
//		pw.println(
//				"<rect x=\"655\" y=\"12\" width=\"20\" height=\"10\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\">  </rect>");
//		pw.println(
//				"<text id=\"légende du graphique\" x=\"680\" y=\"20\" font-size=\"12\">" + legends.get(1) + "</text>");

		pw.println("</svg>");
		pw.close();
	}

}
