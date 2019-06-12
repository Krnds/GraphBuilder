package fr.karinedias.graphbuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import fr.karinedias.graphbuilder.utils.SVGUtils;

public class BarChart implements Chart {

	/*
	 * CLASSE POUR LA CONSTRUCTION DES DIAGRAMMES EN BARRE
	 */

	/*
	 * variables d'instance :
	 */

	private static String svgHeader = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"800\" height=\"400\">";
	private String outputFile = "dataSVG.svg"; //TODO : demander à l'utilisateur son nom
	private CSVParser csvFile = new CSVParser();
	private BarChart svgFile = new BarChart(this.csvFile); // TODO: nécessaire ? Correct ?
	
	/*
	 * constructeur :
	 */

	public BarChart(CSVParser csvFile) {
		this.csvFile = csvFile;
		BarChart svgFile = new BarChart(this.csvFile); // TODO: correct ou pas ?
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
	
	
	//TODO: Faire des getters dans cette classe là pour accéder à toutes les ArrayList ?
	//TODO: Faire une méthode main ici ou dans une autre classe qui va appeler toutes les méthodes ?
	

	
	
	/* TODO: besoin de getters ??
	 * GETTERS POUR ACCÉDER AUX MÉTHODES EN CRÉANT L'OBJET BARGRAPH DANS UNE AUTRE CLASSE [en constrcution]
	 */

	public void construction(BarChart graph) throws IOException {
		
		//TODO: enlever l'argument graph

		// déclaration du PrinterWriter pour la construction du SVG
		PrintWriter pw = new PrintWriter(this.outputFile);
		pw.print(svgHeader);

		/*
		 * Construction des barres du diagramme :
		 */

		// Position X des barres sur l'axe des abscisses dans l'arrayList xPositionbar
		ArrayList<Integer> xPositionbar = new ArrayList<Integer>(graph.points().size());
		int width = 50; // TODO: à demander à l'utilisateur, cf classe UserRequests
		xPositionbar.addAll(SVGUtils.counter(graph.points().size(), width));


		// récupérer la valeur de optimalWidth et optimalHeight de la classe SVGUtils
		int optWidth = SVGUtils.getOptimalWidth(graph.points());
		int optHeight = SVGUtils.getOptimalHeight(graph.points());

		for (int i = 0; i < points().size() - 1; i++) {
			pw.println("<rect x=\"" + xPositionbar.get(i) + "\" y=\"350\" width=\"" + optWidth + "\" height=\""
					+ Double.valueOf(Double.parseDouble(points().get(i)) * optHeight)
					+ "\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\" transform=\"translate(0,-"
					+ Double.valueOf(Double.parseDouble(points().get(i)) * optHeight) + ")\" />");
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

		int nBars = SVGUtils.getNumberOfBars(graph.points()); // nombre de barres du graphique
		int positionOfLabels = (optWidth / nBars);
		for (int i = 0; i < points().size() - 1; i++) {
			pw.println("<text id=\"légende-abcisse \" x=\"" + (xPositionbar.get(i) + positionOfLabels)
					+ "\" y=\"360\" font-size=\"8\" text-anchor=\"center\">" + graph.labels().get(i) + "</text>");
		}

		/*
		 * Mettre la légende sur le graph (rectangle plein + texte indiquant la couleur) :
		 */
		ArrayList<String> legends = new ArrayList<String>();
		legends = graph.legend();
		pw.println(
				"<rect x=\"655\" y=\"12\" width=\"20\" height=\"10\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\">  </rect>");
		pw.println(
				"<text id=\"légende du graphique\" x=\"680\" y=\"20\" font-size=\"12\">" + legends.get(1) + "</text>");

		pw.println("</svg>");
		pw.close();
	}

}

// TODO: toutes les méthodes doivent renvoyer quelque chose ?
// TODO: si qu'une seule légende à CHAQUE FOIS, changer la valeur de retour de
// legend en String plutôt que l'AL de String
// TODO: faut-il que je créée une classe fille pour la construction en elle-même
// du SVG de BarGRaph ?