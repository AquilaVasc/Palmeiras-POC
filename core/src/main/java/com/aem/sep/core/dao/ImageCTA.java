package com.aem.sep.core.dao;

public class ImageCTA {
	
	private String image;
	private String link;
	private String alt;
	private String target;
	
	/**
	 * CONSTRUCTOR
	 * @param image
	 * @param link
	 * @param alt
	 * @param target
	 */
	public ImageCTA(String image, String alt, String target, String link) {
		super();
		this.image = image;
		this.link = link;
		this.alt = alt;
		this.target = target;
	}
	
	/**
	 * GETTERS AND SETTERS
	 * @return GET and SET of variables
	 */
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
}
