package fr.karinedias.graphbuilder.utils;

import java.util.ArrayList;
import java.util.Collections;

import fr.karinedias.graphbuilder.model.Graph;

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
	public static int getOptimalWidth(Graph graph) {

		// dynamiser la largeur des barres :
		int rightMargin = 30;
		int leftMargin = 50;
		int graphWidth = 850; // à voir
		int marginBetweenBars = 5;

		int nPoints = graph.getDataPoints().size();

		int optimalWidth = (graphWidth - (nPoints * marginBetweenBars) - leftMargin - rightMargin) / nPoints;

		return optimalWidth;
	}
	
	//méthode calculant la largeur optimale des lignes du diagramme de points selon leur nombre et la largeur totale du graphe:
	public static int getOptimalWidthLines(Graph graph) {
		
		int nPoints = graph.getDataPoints().size();
		int graphWidth = 850; //à voir ?
		int optimalWidth = graphWidth / nPoints;
		return optimalWidth;
	}

	// méthode calculant la hauteur optimale des barres du graphe suivant la valeur
	// maximale du set de données du fichier CSV donné en argument
	public static int getOptimalHeight(Graph graph) {

		int maxValue = graph.getMax();
		int graphHeight = 350; // à voir
		int optimalHeight;

		if (maxValue >= graphHeight) {
			optimalHeight = maxValue / graphHeight;
		} else {
			optimalHeight = graphHeight / maxValue;
		}

		return optimalHeight;
	}
	
	//TEST pour recalculer autrement la valeur d'une "unité" des données en pixels :
	public static int getOptimalHeight2(Graph graph) {
		
		int graphHeight = 350;
		int maxValue = graph.getMax();
		int minValue = graph.getMin();
		int intervalValues = maxValue - minValue;
		int optimalHeight = intervalValues / graphHeight;
		
		return optimalHeight;
	}

	// TODO: recalculer la hauteur max car quand grande données, la barre la plus
	// haute dépasse...

}
