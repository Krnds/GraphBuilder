package fr.karinedias.graphbuilder;

import java.io.IOException;
import java.util.ArrayList;

/*
 * INTERFACE DES GRAPHIQUES :
 */

public interface Chart {

	public void  axis() throws IOException;

	public ArrayList<String>  points() throws IOException;

	public ArrayList<String>  legend() throws IOException;

	public ArrayList<String>  labels() throws IOException;
	
	public void construction() throws IOException;

}
