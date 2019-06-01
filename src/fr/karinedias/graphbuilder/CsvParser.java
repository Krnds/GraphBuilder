package fr.karinedias.graphbuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CsvParser {

	public static void main(String[] args) throws IOException {
		String fileName = "csv/test.csv";
		File file = new File(fileName);

		// this gives you a 2-dimensional array of strings
		List<List<String>> lines = new ArrayList<>();
		Scanner inputStream;

		try {
			inputStream = new Scanner(file);

			while (inputStream.hasNext()) {
				String line = inputStream.next();
				String[] values = line.split(",");
				// this adds the currently parsed line to the 2-dimensional string array
				lines.add(Arrays.asList(values));
			}

			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// the following code lets you iterate through the 2-dimensional array
		int lineNo = 1;
		for (List<String> line : lines) {
			int columnNo = 1;
			for (String value : line) {
				System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
				columnNo++;
			}
			lineNo++;
		}

		// test for printing only values in the 2nd column :

		inputStream = new Scanner(file);

		while (inputStream.hasNext()) {
			String line = inputStream.next();
			String[] values = line.split(",");
			for (int i = 0; i < values.length; i++) {
				System.out.println(values[i]);
			}
		}
		
		List<String> lines1 = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8); 
		for (String line : lines1) { 
		   String[] array = line.split(","); 
//		   System.out.println(array[1]); 
		}
	}

}