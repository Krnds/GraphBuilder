package fr.karinedias.graphbuilder;

public class DataPoint {

	/*
	 * variables d'instance :
	 */
	
	private String legend;
	private Number point;
	
	/*
	 * constructeur :
	 */
	
	public DataPoint(String legend, Number point) {
		
		this.legend = legend;
		this.point = point;
	}
	
	
	/*
	 * getters et setters :
	 */
	


	public String getLegend() {
		return legend;
	}


	public void setLegend(String legend) {
		this.legend = legend;
	}


	public Number getPoint() {
		return point;
	}


	public void setPoint(Number point) {
		this.point = point;
	}
	
	
}
