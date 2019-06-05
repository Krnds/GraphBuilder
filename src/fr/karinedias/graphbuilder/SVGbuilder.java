package fr.karinedias.graphbuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.lang.Integer;
import java.lang.*;
import java.lang.String;

import fr.karinedias.graphbuilder.utils.*;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

public class SVGbuilder {

	private static String header = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"800\" height=\"400\">";
	private String output = "dataSVG.svg";

	// TODO: changer en arg CSVParser
	public SVGbuilder(CSVParser csv) {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * MAIN METHOD
	 */
	public static void main(String[] args) throws IOException {

		// SVGaxis();
		CSVParser myfile = new CSVParser();
		SVGbuilder mysvg = new SVGbuilder(myfile);
		mysvg.dataSVG(myfile);
		
		System.out.println(setMax(myfile.getPoints()));
		

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
		double unite = 300/ setMax(file.getPoints()); //350 = hauteur max du graph avec les marges
		

		for (int i = 0; i < a3.size() - 1; i++) {
			pw.println("<rect x=\"" + xPositionbar.get(i) + "\" y=\"350\" width=\"" + width + "\" height=\"" + Double.valueOf(Double.parseDouble(a3.get(i))*unite)
					+ "\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\" transform=\"translate(0,-" + Double.valueOf(Double.parseDouble(a3.get(i))*unite) + ")\" />");
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

		pw.println("<text id=\"légende1 \" x=\"40\" y=\"360\" font-size=\"6\" text-anchor=\"right\">2007</text>");
		pw.println("</svg>");
		pw.close();

	}

	

	public static void SVG() throws FileNotFoundException {

		String outputFile = "testSVG.svg";
		PrintWriter pw = new PrintWriter(outputFile);

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
		
		//parseInt
		Integer j = 0;
		
		ArrayList<Integer> dataInt = new ArrayList<Integer>(data.size());
		for (int i = 0; i < data.size(); i++) {
		dataInt.add(j.valueOf(data.get(i)));
		}
		//find maximum value
		int max = Collections.max(dataInt); //forcément un int ? si données en float ?
		
		return max;
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

//TODO: changer les balises chart par rect