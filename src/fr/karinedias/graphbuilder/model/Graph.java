package fr.karinedias.graphbuilder.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {

	private String title;
	private List<String> captions;
	private List<DataPoint> dataPoints = new ArrayList<>();


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<DataPoint> getDataPoints() {
		return dataPoints;
	}

	public List<String> getCaptions() {
		return this.captions;
	}

	public void setCaptions(List<String> captions) {
		this.captions = captions;
	}

	public void setDataPoints(List<DataPoint> dataPoints) {
		this.dataPoints = dataPoints;
	}

	public List<Number> getValues() {
		List<Number> values = new ArrayList<>();
		for (DataPoint dataPoint : dataPoints) {
			values.add(dataPoint.getPoint());
		}
		return values;
	}
	
	public List<String> getLabels() {
		
		List<String> labels = new ArrayList<>();
		for (DataPoint labelPoint : dataPoints) {
			labels.add(labelPoint.getCaption());
		}
		
		return labels;
	}

	public List<Integer> getIntValues() {
		List<Integer> intValues = new ArrayList<>();

		for (Number n : getValues()) {
			intValues.add(n.intValue());
		}
		return intValues;
	}

	public int getMax() {
		return Collections.max(getIntValues());
	}
	
	public int getMin() {
		return Collections.min(getIntValues());
	}



}
