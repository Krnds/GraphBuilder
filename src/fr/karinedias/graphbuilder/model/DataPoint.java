package fr.karinedias.graphbuilder.model;

public class DataPoint {

	/*
	 * variables d'instance :
	 */

	private String caption;
	private Number point;

	/*
	 * constructeur :
	 */

	public DataPoint() {
	}

	public DataPoint(String caption, Number point) {
		this.caption = caption;
		this.point = point;
	}

	/*
	 * getters et setters :
	 */

	public String getCaption() {
		return caption;
	}

	public void setCaption(String legend) {
		this.caption = legend;
	}

	public Number getPoint() {
		return point;
	}

	public void setPoint(Number point) {
		this.point = point;
	}

}
