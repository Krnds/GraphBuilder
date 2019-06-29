package fr.karinedias.graphbuilder.exceptions;

@SuppressWarnings("serial")
public class IncorrectChoiceException extends IllegalArgumentException {

	public IncorrectChoiceException() {
		super();	
		System.out.println("Ce choix n'est pas disponible");

	}
	

	public IncorrectChoiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IncorrectChoiceException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public IncorrectChoiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
