package com.aem.sep.core.dao;

import java.util.List;

public class PartnersDAO {

	private String title;
	
	private List<ImageCTA> partners;

	/**
	 * GETTERS AND SETTERS
	 * @return GET and SET of variables
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<ImageCTA> getPartners() {
		return partners;
	}
	public void setPartners(List<ImageCTA> partners) {
		this.partners = partners;
	}
}
