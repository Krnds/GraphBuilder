package fr.karinedias.graphbuilder.presentation;

import java.util.Scanner;

public class UserRequests {

	/*
	 * TODO: largeur des barres ?
	 */
	
	public static void main(String[] args) {
		
		
	}
	
	
	private int barWidth() {
		
		int width;
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle est la largeur des barres souhaitée ?");
		width = sc.nextInt();
		
		return width;
	}
}
