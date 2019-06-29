package fr.karinedias.graphbuilder.utils;

import java.util.ArrayList;
import fr.karinedias.graphbuilder.model.BarChart;
import fr.karinedias.graphbuilder.model.LineChart;

public class SVGUtils {

	/*
	 * Classe qui regroupe toutes les méthodes utilitaires pour la construction du
	 * fichier SVG final : - Un compteur pour calculer l'endoit exact des barres du
	 * graphe - SetMax qui permet de calculer la valeur maximale du set de données
	 * et d'ajuster les proportions à partir de celle-ci
	 */

	// méthode qui calcule la position X de chaque barre suivant la largeur et le
	// nombre de barres
	public static ArrayList<Integer> counter(int nbPoints, int widthNumber) {
		ArrayList<Integer> counter = new ArrayList<Integer>(nbPoints);
		counter.add(0, widthNumber);// initialisation du compteur à 0 (première position de x)
		int margin = 5;
		int stepValue = widthNumber + margin;

		for (int i = 1; i < nbPoints; i++) { //TODO: avant était initialisé à 1
			counter.add(i, (counter.get(i - 1) + stepValue)); //TODO: avant il y avait counter.get(i - 1) + stepValue
		}

		return counter;
	}

	// méthode qui prend en argument les données de la 2ème colonne du CSV et
	// renvoie un entier représentant le nombre de données à représenter
	public static int getNumberOfBars(ArrayList<String> data) {

		int nBars = data.size();

		return nBars;
	}

	// méthode calculant la largeur optimale des barres du graphe selon leur nombre,
	// les marges à droite et à gauche des barres ainsi que la largeur totale du
	// graphique.
	public static int getOptimalWidthBarChart(BarChart barchart) {

		// dynamiser la largeur des barres :
		int rightMargin = 30;
		int leftMargin = 50;
		int graphWidth = barchart.getWidth(); // valeur par défaut : 850 px
		int marginBetweenBars = 5;

		int nPoints = barchart.getDataPoints().size();

		int optimalWidth = (graphWidth - (nPoints * marginBetweenBars) - leftMargin - rightMargin) / nPoints;

		return optimalWidth;
	}
	
	//méthode calculant la largeur optimale des lignes du diagramme de points selon leur nombre et la largeur totale du graphe:
	public static int getOptimalWidthLineChart(LineChart linechart) {
		
		int nPoints = linechart.getDataPoints().size();
		int graphWidth = linechart.getWidth(); //valeur par défaut : 850 px
		int optimalWidth = graphWidth / nPoints;
		return optimalWidth;
	}

	// méthode calculant la hauteur optimale des barres du graphe suivant la valeur
	// maximale du set de données du fichier CSV donné en argument

	
	//recalculer la valeur d'une "unité" des données en pixels :
	public static int getOptimalHeightBarChart(BarChart barchart) {
		
		int graphHeight = 350;
		int maxValue = barchart.getMax();
		int minValue = barchart.getMin();
		int intervalValues = maxValue - minValue;
		int optimalHeight = intervalValues / graphHeight;
		
		if (maxValue >= graphHeight) {
			optimalHeight = maxValue / graphHeight;
		}
		return optimalHeight;
	}
	
	
	public static int getOptimalHeightLineChart(LineChart linechart) {
		
		int graphHeight = 350;
		int maxValue = linechart.getMax();
		int minValue = linechart.getMin();
		int intervalValues = maxValue - minValue;
		int optimalHeight = intervalValues / graphHeight;
		
		if (maxValue >= graphHeight) {
			optimalHeight = maxValue / graphHeight;
		}
		return optimalHeight;
	}


}
