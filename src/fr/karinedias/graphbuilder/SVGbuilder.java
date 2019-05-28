package fr.karinedias.graphbuilder;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import fr.karinedias.graphbuilder.utils.*;

public class SVGbuilder {

	public static void SVGaxis() throws FileNotFoundException {

		String outputFile = "out.svg";
		int height = 400;
		int width = 800;
		PrintWriter pw = new PrintWriter(outputFile);
		pw.println("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"800\" height=\"400\">");
		// 1ere barre :
		pw.println(
				"<rect x=\"35\" y=\"250\" width=\"30\" height=\"100\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\" />");

		// légende 1 :
		pw.println("<text id=\"légende1 \" x=\"40\" y=\"360\" font-size=\"6\" text-anchor=\"right\">2007</text>"); //x = 1er rect + 5

		// 2eme barre en x = 35 + 30 (width) + 5 d'espace = 70
		pw.println(
				"<rect x=\"70\" y=\"250\" width=\"30\" height=\"75\" style=\"fill:rgb(200,50,100);stroke-width:1;stroke:rgb(0,0,0)\" transform=\"translate(0,25)\"/>"); 
		// car 100-75 = 25 pour avoir la même base que la 1st barre
		// légende 2 :
				pw.println("<text id=\"légende1 \" x=\"75\" y=\"360\" font-size=\"6\" text-anchor=\"right\">2008</text>");//x = 2eme rect + 5

		// exemple avec barre n°3 : x = 70 + 30 + 5 = 105
		pw.println(
				"<rect x=\"105\" y=\"250\" width=\"30\" height=\"135\" style=\"fill:rgb(20,80,100);stroke-width:1;stroke:rgb(0,0,0)\" transform=\"translate(0,-35)\"/>");

		pw.println(
				"<line x1=\"30\" y1=\"20\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\" marker-end=\"url(#arrow)\"/>");
		pw.println(
				"<line x1=\"750\" y1=\"350\" x2=\"30\" y2=\"350\" fill=\"none\" shape-rendering=\"crispEdges\" stroke=\"#ccc\" stroke-dasharray=\"5,2\" stroke-width=\"1\"> </line>");
		pw.println("\n</svg>");
		pw.close();

	}

	public static void main(String[] args) throws FileNotFoundException {
		SVGaxis();
		fr.karinedias.graphbuilder.utils.CSVReader.
	}
}

/*
 * TODO: dessiner les deux axes TODO: inscrire les données en abcsisse TODO:
 * dessiner les rectangles pour chaque donnée TODO: mettre 5 px avant l'axe des
 * ordonnées et entre chaque rectangle pour l'espace
 */
