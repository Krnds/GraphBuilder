
package fr.karinedias.graphbuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import fr.karinedias.graphbuilder.model.DataPoint;
import fr.karinedias.graphbuilder.model.Graph;

public class CSVParser {

	private String fileName;

	public CSVParser(String fileName) {
		this.fileName = fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public Graph buildGraph() throws IOException {
		// TODO Input = any file
		String defaultPath = System.getProperty("user.dir") + File.separator + "csv" + File.separator;
		String csv = defaultPath + this.fileName;
		File csvFile = new File(csv);

		List<String> csvLines = Files.readAllLines(csvFile.toPath(), StandardCharsets.UTF_8);
		String[] caption = csvLines.get(0).split(",");
		List<String> captions = Arrays.asList(caption);

		csvLines.remove(0); // TODO Use first line (removed here since it is not data)

		Graph graph = new Graph();

		for (String csvLine : csvLines) {
			DataPoint dataPoint = this.buildDataPoint(csvLine);
			graph.getDataPoints().add(dataPoint);
		}

		graph.setCaptions(captions);

		return graph;
	}

	private DataPoint buildDataPoint(String line) {
		String[] parsedLine = line.split(",");
		DataPoint dataPoint = new DataPoint(parsedLine[0], Integer.parseInt(parsedLine[1]));
		return dataPoint;
	}

}
