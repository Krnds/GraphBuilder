package fr.karinedias.graphbuilder.exceptions;

import java.util.NoSuchElementException;

@SuppressWarnings("serial")
public class CSVFormatException extends NoSuchElementException {
	
	public CSVFormatException() {
		super();
		System.out.println("Le fichier CSV n'est pas valide");
	}

	public CSVFormatException(String s) {
		super(s);
		System.out.println("Le fichier CSV n'est pas valide");
		
	}
	
	

}
