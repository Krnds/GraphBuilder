package fr.karinedias.graphbuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import com.sun.corba.se.spi.orb.ParserData;

import java.lang.Integer;
import java.lang.String;


public class SVGbuilder {

	private static String header = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"800\" height=\"400\">";
	private String output = "dataSVG.svg";

	// TODO: utile ici d'avoir un constructeur ?
	public SVGbuilder(CSVParser csvFile) throws IOException {
		
		//TEST
		ArrayList<String> a1 = new ArrayList<String>();
		a1 = csvFile.getKeys();

		// a2 = tableau pour l'axe des abscisses
		ArrayList<String> a2 = new ArrayList<String>();
		a2 = csvFile.getAxisX();

		// a3 = tableau pour les points du graphe
		ArrayList<String> a3 = new ArrayList<String>();
		a3 = csvFile.getPoints();

		PrintWriter pw = new PrintWriter(this.output);
		pw.print(header);
		
		
		
	}
	
	
	/*
	 * MAIN METHOD
	 */
	public static void main(String[] args) throws IOException {

		// SVGaxis();
		CSVParser myfile = new CSVParser();
		SVGbuilder mysvg = new SVGbuilder(myfile);
		
		
		
		//tests de méthodess :
		
		System.out.println(myfile.getPoints().toString());
		System.out.println(numberOfBars(myfile.getPoints()));
		System.out.println(setMax(myfile.getPoints()));
		System.out.println(counter(21, 50));
		mysvg.dataSVG(myfile);

	}
	
	/*
	 * CONSTRUCTION OF THE SVG FILE
	 */

	public void dataSVG(CSVParser file) throws IOException {

		/*
		 * RAW DATA :
		 */

		// a1 = tableau pour les légendes
		ArrayList<String> a1 = new ArrayList<String>();
		a1 = file.getKeys();

		// a2 = tableau pour l'axe des abscisses
		ArrayList<String> a2 = new ArrayList<String>();
		a2 = file.getAxisX();

		// a3 = tableau pour les points du graphe
		ArrayList<String> a3 = new ArrayList<String>();
		a3 = file.getPoints();

		PrintWriter pw = new PrintWriter(this.output);
		pw.print(header);

		/*
		 * PRINTING POINTS OF CSV TO THE GRAPH :
		 */

		// loop for x position of bars :
		ArrayList<Integer> xPositionbar = new ArrayList<Integer>(a3.size());
		int width = 50; // à demander à l'utilisateur
		xPositionbar.addAll(counter(a3.size(), width));
		
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

		for (int i = 0; i < a3.size() - 1; i++) {
			pw.println("<rect x=\"" + xPositionbar.get(i) + "\" y=\"350\" width=\"" + optimalWidth + "\" height=\"" + Double.valueOf(Double.parseDouble(a3.get(i))*optimalHeight)
					+ "\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\" transform=\"translate(0,-" + Double.valueOf(Double.parseDouble(a3.get(i))*optimalHeight) + ")\" />");
		}
		
		
		

		/*
		 * AXIS OF GRAPH :
		 */

		// axe Y : ordonnées
		pw.println(
				"<line x1=\"30\" y1=\"20\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\" marker-end=\"url(#arrow)\"/>");

		// axe X : abscisses
		pw.println(
				"<line x1=\"750\" y1=\"350\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\"> </line>");

		/*
		 * LEGENDS OF POINTS :
		 */

		int positionOfLabels = (int) (optimalWidth / numberOfBars(file.getPoints())); //au pif le 5 vient de la margin (cf. counter)
		for (int i = 0; i < a3.size() - 1; i++) {
		pw.println("<text id=\"légende-abcisse \" x=\""+ (xPositionbar.get(i)+positionOfLabels) + "\" y=\"360\" font-size=\"6\" text-anchor=\"center\">"+ a2.get(i) +"</text>");
		}
		pw.println("</svg>");
		pw.close();

	}



	// method for specify the increment of each barangle x position
	public static ArrayList<Integer> counter(int nbPoints, int widthNumber) {
		ArrayList<Integer> counter = new ArrayList<Integer>(nbPoints);
		counter.add(0, widthNumber);//to specify the initialization of the counter
		int margin = 5;
		int stepValue = widthNumber + margin;
		
		for (int i = 1; i < nbPoints - 1; i++) {
		counter.add(i, (counter.get(i -1) + stepValue));
		}

		return counter;
	}
	
	//method for setting correct proportions of the chart's bars
	
	public static int setMax(ArrayList<String> data) {
		
		//TODO: setMax doit UNIQUEMENT prendre les données de la 2eme colonne, sinon erreur !!!
				
		ArrayList<Integer> dataInt = new ArrayList<Integer>();//j'ai mis avant dans (); "data.size();
		for (int i = 0; i < data.size(); i++) {
		//dataInt.add(Integer.valueOf(data.get(i))); //fait quoi ?
			dataInt.add(Integer.valueOf(Integer.parseInt(data.get(i))));
		}
		
		
		//find maximum value
		int max = Collections.max(dataInt); //forcément un int ? si données en float ?
		
		return max;
	}
	
	//method for counting how many bars the graph have
	public static int numberOfBars(ArrayList<String> data) {
		
		int nBars = data.size();
		
		return nBars;
	}
	
	
/*
 * TESTS avec le format SVG : 
 */
	
	
	
	public static void svgtest() throws FileNotFoundException {

		String outputFile = "out.svg";
		PrintWriter pw = new PrintWriter(outputFile);

		pw.println("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"800\" height=\"400\">");
		// 1ere barre :
		pw.println(
				"<rect x=\"35\" y=\"250\" width=\"30\" height=\"100\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\" />");

		// légende 1 :
		pw.println("<text id=\"légende1 \" x=\"40\" y=\"360\" font-size=\"6\" text-anchor=\"right\">2007</text>"); // x
																													// =
																													// 1er
																													// bar
																													// +
																													// 5

		// 2eme barre en x = 35 + 30 (width) + 5 d'espace = 70
		pw.println(
				"<rect x=\"70\" y=\"250\" width=\"30\" height=\"75\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\" transform=\"translate(0,25)\"/>");
		// car 100-75 = 25 pour avoir la même base que la 1st barre
		// légende 2 :
		pw.println("<text id=\"légende1 \" x=\"75\" y=\"360\" font-size=\"6\" text-anchor=\"right\">2008</text>");// x =
																													// 2eme
																													// bar
																													// +
																													// 5

		// exemple avec barre n°3 : x = 70 + 30 + 5 = 105
		pw.println(
				"<rect x=\"105\" y=\"250\" width=\"30\" height=\"135\" style=\"fill:rgb(20,80,100);stroke-width:1;stroke:rgb(0,0,0)\" transform=\"translate(0,-35)\"/>");

		// axe Y : ordonnées
		pw.println(
				"<line x1=\"30\" y1=\"20\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\" marker-end=\"url(#arrow)\"/>");

		// axe X : abscisses
		pw.println(
				"<line x1=\"750\" y1=\"350\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\"> </line>");
		pw.println("\n</svg>");
		pw.close();

	}

}

/*
 * TODO: inscrire les données en abcsisse TODO:
 * dessiner les barangles pour chaque donnée TODO: mettre 5 px avant l'axe des
 * ordonnées et entre chaque rectangle pour l'espace
 */

//TODO: changer les balises chart par rect OK
//TODO: réécrire la méthode dataSVG afin de rendre cela plus compréhensible
//TODO: attention de gérer les bugs quand les calculs donnent 0 : si le max des données > hauteur (350px), changer le calcul de l'unité'
//TODO: éviter toutes les répétitions de code, faire du refactoring