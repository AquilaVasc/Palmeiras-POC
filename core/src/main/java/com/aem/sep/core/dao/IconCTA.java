package com.aem.sep.core.dao;

public class IconCTA {

	private String icon;
	private String link;
	private String target;
	
	/**
	 * CONSTRUCTOR
	 * @param icon
	 * @param link
	 * @param target
	 */
	public IconCTA(String icon, String target, String link) {
		super();
		this.icon = icon;
		this.link = link;
		this.target = target;
	}
	
	/**
	 * GETTERS AND SETTERS
	 * @return GET and SET of variables
	 */
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
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
