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
	 * variables d'instance
	 */
	
	private int barWidth;
	private String barColor;
	
	
	
	public String svgName() {
		
		String output = "out.svg"; // nom de base
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel est le nom du fichier de sortie ?");
		output = sc.nextLine();
		
		return output;
		
	}
	
	
	private int barWidth() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle est la largeur des barres souhaitée ?");
		this.barWidth = sc.nextInt();
		
		return barWidth;
	}
	
	
	private String barColor() {
		
		String color = "none";
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle est la couleur des barres souhaitée ? Veuillez indiquer la couleur en anglais ou en format rgb(XXX, YYY, ZZZ)");
		color = sc.nextLine();
		
		return color;
	}
	
	
}
