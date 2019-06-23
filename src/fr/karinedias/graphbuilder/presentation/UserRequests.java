package fr.karinedias.graphbuilder.presentation;

import java.util.Scanner;

public class UserRequests {

	/*
	 * variables d'instance avec leurs valeurs par défaut
	 */

	private static Scanner sc = new Scanner(System.in);
	private static int barWidth = 40; 
	private static int strokeWidth = 1;
	private static String barColor = "none";
	private static String lineColor = "none";
	private static int width = 800;
	private static int height = 400;

	public static String svgName() {

		String output = "out.svg"; // nom de base TODO: à changer 
		System.out.println("Quel est le nom du fichier de sortie ?");
		output = sc.nextLine();
		return output;
	}

	static void barWidth() {
		System.out.println("Quelle est la largeur des barres souhaitée (en pixels) ?");
		setBarWidth(sc.nextInt());
		sc.nextLine();
		
	}

	static void graphWidth() {
		System.out.println("Quelle est la largeur du graphe souhaitée ?");
		setWidth(sc.nextInt());
		sc.nextLine();
	}

	
	static void graphHeight() {
		System.out.println("Quelle est la hauteur du graphe souhaitée ?");
		setHeight(sc.nextInt());
		sc.nextLine();
	}

	static void barColor() {
		System.out.println(
				"Quelle est la couleur des barres souhaitée ? Veuillez indiquer la couleur en anglais ou en format rgb(XXX, YYY, ZZZ)");
		setBarColor(sc.nextLine());
	}

	static void lineColor() {
		System.out.println(
				"Quelle est la couleur des lignes ? Veuillez indiquer la couleur en anglais/héxadécimal ou format rgb");
		setLineColor(sc.nextLine());
	}
	
	static void strokeWidth() {

		System.out.println(
				"Quelle est l'épaisseur des contours souhaitée ? (en pixels)");
		setStrokeWidth(sc.nextInt());
		sc.nextLine();
	}

	// getters :
	public static int getBarWidth() {
		return barWidth;
	}
	
	public static void setBarWidth(int barWidth) {
		UserRequests.barWidth = barWidth;
	}

	public static void setStrokeWidth(int strokeWidth) {
		UserRequests.strokeWidth = strokeWidth;
	}

	public static void setBarColor(String barColor) {
		UserRequests.barColor = barColor;
		System.out.println("La couleur choisie est : " + UserRequests.barColor);
		System.out.println(getBarColor());
	}

	public static void setLineColor(String lineColor) {
		UserRequests.lineColor = lineColor;
	}

	public static void setWidth(int width) {
		UserRequests.width = width;
	}

	public static void setHeight(int height) {
		UserRequests.height = height;
	}

	public static int getStrokeWidth() {
		return strokeWidth;
	}


	public static String getBarColor() {
		return barColor;
	}

	public static String getLineColor() {
		return lineColor;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

}
