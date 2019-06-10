package fr.karinedias.graphbuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import fr.karinedias.graphbuilder.utils.SVGUtils;

public class BarGraph implements Chart {

	/*
	 * CLASSE POUR LA CONSTRUCTION DES DIAGRAMMES EN BARRE
	 */

	/*
	 * variables d'instance :
	 */

	private static String svgHeader = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"800\" height=\"400\">";
	private String outputFile = "dataSVG.svg";
	private CSVParser csvFile = new CSVParser();

	/*
	 * constructeur :
	 */

	public BarGraph(CSVParser csvFile) {
		this.csvFile = csvFile;
		BarGraph svgFile = new BarGraph(this.csvFile); // TODO: correct ou pas ?
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

	public void construction(BarGraph graph) throws IOException {
		
		// déclaration du PrinterWriter pour la construction du SVG
		PrintWriter pw = new PrintWriter(this.outputFile);
		pw.print(svgHeader);
		

		
		/*
		 * PRINTING POINTS OF CSV TO THE GRAPH :
		 */

		// loop for x position of bars :
		ArrayList<Integer> xPositionbar = new ArrayList<Integer>(graph.points().size());
		int width = 50; // TODO: à demander à l'utilisateur, cf classe UserRequests
		xPositionbar.addAll(SVGUtils.counter(graph.points().size(), width));
		
		//dynamiser la hauteur des barres :
		double optimalHeight = 0.0;
		if (setMax(file.getPoints()) > (400 - width)) {
			optimalHeight = setMax(file.getPoints()) / (400 - width);
		} else {
		 optimalHeight = (400 - width)/ setMax(file.getPoints()); //350 = hauteur max du graph avec les marges
		}
		
		//dynamiser la largeur des barres :
		int rightMargin = 50;
		int optimalWidth = ((600 - (numberOfBars(file.getPoints())) * 5) - rightMargin) / (numberOfBars(file.getPoints())); //à l'arrache

		
		//récupérer la valeur de optimalWidth et optimalHeight de la classe SVGUtils
		int optWidth = SVGUtils.getOptimalWidth(file.getPoints()); 
		int optHeight = SVGUtils.getOptimalHeight(file.getPoints());		
		
		for (int i = 0; i < a3.size() - 1; i++) {
			pw.println("<rect x=\"" + xPositionbar.get(i) + "\" y=\"350\" width=\"" + optWidth + "\" height=\"" + Double.valueOf(Double.parseDouble(a3.get(i))*optHeight)
					+ "\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\" transform=\"translate(0,-" + Double.valueOf(Double.parseDouble(a3.get(i))*optHeight) + ")\" />");
		}
		
		
		

		/*
		 * AXIS OF GRAPH :
		 */

		// axe Y : ordonnées
		pw.println(
				"<line x1=\"30\" y1=\"20\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\" marker-end=\"url(#arrow)\"/>");

		// axe X : abscisses
		pw.println(
				"<line x1=\"850\" y1=\"350\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\"> </line>");

		/*
		 * LABLES OF POINTS :
		 */

		int positionOfLabels = (int) (optimalWidth / numberOfBars(file.getPoints())); //au pif le 5 vient de la margin (cf. counter)
		for (int i = 0; i < a3.size() - 1; i++) {
		pw.println("<text id=\"légende-abcisse \" x=\""+ (xPositionbar.get(i)+positionOfLabels) + "\" y=\"360\" font-size=\"8\" text-anchor=\"center\">"+ a2.get(i) +"</text>");
		}
		
		/*
		 * LEGENDS :
		 */
	ArrayList<String> legends = new ArrayList<String>();
	legends = file.getKeys();
	pw.println("<rect x=\"655\" y=\"12\" width=\"20\" height=\"10\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\">  </rect>");
	pw.println("<text id=\"légende du graphique\" x=\"680\" y=\"20\" font-size=\"12\">" + legends.get(1) + "</text>");
	


		pw.println("</svg>");
		pw.close();
	}

}

// TODO: toutes les méthodes doivent renvoyer quelque chose ?
// TODO: si qu'une seule légende à CHAQUE FOIS, changer la valeur de retour de
// legend en String plutôt que l'AL de String
// TODO: faut-il que je créée une classe fille pour la construction en elle-même
// du SVG de BarGRaph ?