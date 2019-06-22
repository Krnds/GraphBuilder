package fr.karinedias.graphbuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import fr.karinedias.graphbuilder.model.Graph;
import fr.karinedias.graphbuilder.utils.SVGUtils;

public class LineChart {

	private static String svgHeader = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"800\" height=\"400\">";
	private String outputFile = "blabla.svg";
	private Graph graph;

	public LineChart(Graph graph) {
		this.graph = graph;
	}

	public void draw() throws IOException {

		// déclaration du PrinterWriter pour la construction du SVG
		PrintWriter pw = new PrintWriter(this.outputFile);
		pw.print(svgHeader);

		/*
		 * PRINTING POINTS OF CSV TO THE GRAPH :
		 */

		// Position des points sur l'axe des abscisses dans l'arrayList
		int nPoints = graph.getDataPoints().size(); //nombre de points à représenter
		ArrayList<Integer> xPositionbar = new ArrayList<Integer>(nPoints);
		int width = 90; // TODO: à demander à l'utilisateur, cf classe CommandLineInterface
		xPositionbar.addAll(SVGUtils.counter(nPoints, width));
		
		//TODO: à supprimer, pour le test
		for (int i = 0; i < xPositionbar.size(); i++) {
			System.out.println("Point n° " + i + ": "  + xPositionbar.get(i));
		}
		

		ArrayList<String> points = new ArrayList<String>(nPoints); // ArrayList pour stocker toutes les valeurs des points du graphe
		points.add(graph.getIntValues().toString());

		//TODO: à supprimer, pour le test
		for (int i = 0; i < points.size(); i++) {
			System.out.println("Point n° " + i + ": "  + points.get(0));
		}
		
		// Proportions correctes en largeur :
		int optWidthPoint = SVGUtils.getOptimalWidthLines(graph);
		//TODO: test à supprimer
		System.out.println("largeur optimale = " + optWidthPoint + " pixels.");

		// TODO: à finir
//		for (int i = 0; i < points.size(); i++) {
//			int newValue = 0;
//			if (Integer.parseInt(points.get(i)) <= 100)
//				newValue = Integer.parseInt(points.get(i)) * optWidthPoint;
//			points.set(i, String.valueOf(newValue));
//			System.out.println(points.get(i));
//		}

		// Affichage des points sur le SVG selon le duo "x,y" avec x représentant la
		// position sur l'axe des abscisses et y la position sur l'axe des ordonnées
		// (valeur du fichier CSV)

		pw.println("<polyline points=\"");
		// boucle pour l'affichage de chaque point
//		for (int i = 0; i < nPoints - 1; i++) {
//			pw.print(xPositionbar.get(i) + "," + graph.getIntValues().get(i) * optWidthPoint + " ");
//		}
		
		//AUTRE TEST POUR LES POINTS :
		
		//TODO il faut inverser les chiffres de la hauteur des points à dessiner : 
		//ex:
		int graphHeight = 350; //à voir
		for (int i = 0; i < nPoints; i++) {
			pw.print(xPositionbar.get(i) + "," + (graphHeight - (graph.getIntValues().get(i) * SVGUtils.getOptimalHeight2(graph))) + " ");
		}
		
		pw.println(
				"\" fill=\"none\" stroke=\"black\" marker-start=\"url(#dot)\" marker-mid=\"url(#dot)\" marker-end=\"url(#dot)\" />");
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
		 * Légendes sur l'axe des abscisses correspondant aux données de la 1ère colonne du CSV :
		 */

		int optWidth = SVGUtils.getOptimalWidthLines(graph);
		int positionOfLabels = (optWidth / nPoints);
		for (int i = 0; i < nPoints - 1; i++) {
			pw.println("<text id=\"légende-abcisse \" x=\"" + (xPositionbar.get(i) + positionOfLabels)
					+ "\" y=\"360\" font-size=\"8\" text-anchor=\"center\">" + graph.getLabels().get(i) + "</text>"); // TODO: changer
		}
		

		/*
		 * LEGENDS :
		 */
		
		
		ArrayList<String> legends = new ArrayList<String>();
		legends = (ArrayList<String>) graph.getLabels(); // TODO: correct ?
		
		pw.println(
				"<rect x=\"655\" y=\"12\" width=\"20\" height=\"10\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\">  </rect>");
		pw.println(
				"<text id=\"légende du graphique\" x=\"680\" y=\"20\" font-size=\"12\">" + graph.getCaptions().get(1) + "</text>");

		pw.println("</svg>");
		pw.close();
	}

}
