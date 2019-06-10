package fr.karinedias.graphbuilder.utils;

import java.util.ArrayList;
import java.util.Collections;

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
		counter.add(0, widthNumber);// to specify the initialization of the counter
		int margin = 5;
		int stepValue = widthNumber + margin;

		for (int i = 1; i < nbPoints - 1; i++) {
			counter.add(i, (counter.get(i - 1) + stepValue));
		}

		return counter;
	}

	// méthode qui calcule la valeur maximale de la 2ème colonne du CSV pour ainsi
	// adapter la hauteur du graphe
	public static int getMax(ArrayList<String> data) {

		ArrayList<Integer> dataInt = new ArrayList<Integer>();

		for (int i = 0; i < data.size(); i++) {
			dataInt.add(Integer.valueOf(Integer.parseInt(data.get(i))));
		}

		int max = Collections.max(dataInt); // TODO:forcément un int ? si données en float ?

		return max;
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
	public static int getOptimalWidth(ArrayList<String> data) {

		// dynamiser la largeur des barres :
		int rightMargin = 30;
		int leftMargin = 50;
		int graphWidth = 850; // à voir
		int marginBetweenBars = 5;

		int optimalWidth = (graphWidth - ((getNumberOfBars(data)) * marginBetweenBars) - leftMargin - rightMargin)
				/ (getNumberOfBars(data));

		return optimalWidth;
	}

	// méthode calculant la hauteur optimale des barres du graphe suivant la valeur
	// maximale du set de données du fichier CSV donné en argument
	public static int getOptimalHeight(ArrayList<String> data) {

		int maxValue = getMax(data); //utilise la méthode getMax pour chercher la valeur maximale du CSV
		int graphHeight = 350; //à voir
		int optimalHeight;
		
		if (maxValue >= graphHeight) {
		optimalHeight = maxValue / graphHeight;
		} else {
			optimalHeight = graphHeight / maxValue;
		}
		
		return optimalHeight;
	}
	
	//TODO: recalculer la hauteur max car quand grande données, la barre la plus haute dépasse...

}
