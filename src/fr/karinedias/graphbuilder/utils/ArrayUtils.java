package fr.karinedias.graphbuilder.utils;

import java.util.ArrayList;

public class ArrayUtils {

	
	public static ArrayList<Object> toArrayList(Object[] array) {
		
		ArrayList<Object> arraylist = new ArrayList<>(array.length);
		
		for (int i = 0; i < array.length; i++) {
			arraylist.add(array[i]);
		}
		return arraylist;
	}
	
	public static Object[] toArray(ArrayList<Object> arraylist) {
		
		Object[] array = new Object[arraylist.size()];
		
		for (int i = 0; i < arraylist.size(); i++) {
			array[i] = arraylist.get(i);
		}
		
		return array;
	}
}





/*
 * TODO: initialiser au départ les tailles des array/arraylist pour ne pas avoir de répétition de code
 */
