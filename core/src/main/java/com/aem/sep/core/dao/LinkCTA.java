package com.aem.sep.core.dao;

public class LinkCTA {
	
	private String text;
	private String link;
	private String target;
	
	/**
	 * CONSTRUCTOR
	 * @param text
	 * @param link
	 * @param target
	 */
	public LinkCTA(String text, String target, String link) {
		super();
		this.text = text;
		this.link = link;
		this.target = target;
	}
	
	/**
	 * GETTERS AND SETTERS
	 * @return GET and SET of variables
	 */
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
}
