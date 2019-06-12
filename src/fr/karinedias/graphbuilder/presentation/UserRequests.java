package fr.karinedias.graphbuilder.presentation;

import java.io.IOException;
import java.util.Scanner;

import fr.karinedias.graphbuilder.BarChart;
import fr.karinedias.graphbuilder.CSVParser;
import fr.karinedias.graphbuilder.LineChart;
import fr.karinedias.graphbuilder.SVGbuilder;
import fr.karinedias.graphbuilder.utils.SVGWriter;

public class UserRequests {

	/*
	 * TODO: largeur des barres ?
	 */
	

	public void SVGWriter(BarChart mysvg) throws IOException {
		
		CSVParser mycsv = new CSVParser();
		mysvg = new BarChart(mycsv);
		mysvg.construction(mysvg);
		
		//tests de la classe LineChart
		LineChart mylinechart = new LineChart(mycsv);
		mylinechart.construction();
	}
	
	//TESTS:
	
	public static void main(String[] args) throws IOException {
		
		CSVParser mycsv = new CSVParser();
		LineChart mylinechart = new LineChart(mycsv);
		mylinechart.construction();
	}
	
	public String svgName() {
		
		String output = "out.svg"; // nom de base
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel est le nom du fichier de sortie ?");
		output = sc.nextLine();
		
		return output;
		
	}
	
	
	private int barWidth() {
		
		int width;
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle est la largeur des barres souhaitée ?");
		width = sc.nextInt();
		
		return width;
	}
	
	
	private String barColor() {
		
		String color = "none";
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle est la couleur des barres souhaitée ? Veuillez indiquer la couleur en anglais ou en format rgb(XXX, YYY, ZZZ)");
		color = sc.nextLine();
		
		return color;
	}
}
