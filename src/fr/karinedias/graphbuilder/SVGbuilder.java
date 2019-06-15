package fr.karinedias.graphbuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import com.sun.corba.se.spi.orb.ParserData;

import fr.karinedias.graphbuilder.utils.SVGUtils;

import java.lang.Integer;
import java.lang.String;


public class SVGbuilder {

	private static String header = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"800\" height=\"400\">";
	private String output = "dataSVG.svg";


	
	/*
	 * MAIN METHOD
	 */
	public static void main(String[] args) throws IOException {

		// SVGaxis();
		CSVParser myfile = new CSVParser();
		SVGbuilder mysvg = new SVGbuilder(myfile);
		
		
		
		//tests de méthodess :
		
		System.out.println(myfile.getPoints().toString());
		System.out.println("Nombre de barres :" + numberOfBars(myfile.getPoints()));
		System.out.println("Valeur max : "+ setMax(myfile.getPoints()));
		System.out.println(counter(21, 50));
		System.out.println("Largeur optimale : " + SVGUtils.getOptimalWidth(myfile.getPoints()));
		System.out.println("Hauteur optimale : " + SVGUtils.getOptimalHeight(myfile.getPoints()));
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
				"<line x1=\"30\" y1=\"20\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\" marker-start=\"url(#arrow)\" marker-end=\"url(#arrow)\"/>\"");

		// axe X : abscisses
		pw.println(
				"<line x1=\"850\" y1=\"350\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\"  marker-end=\"url(#markerArrow))\"/>\"");

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
	
	
}

