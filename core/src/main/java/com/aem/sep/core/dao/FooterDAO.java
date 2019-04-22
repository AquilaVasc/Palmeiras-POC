package com.aem.sep.core.dao;

import java.util.List;

public class FooterDAO {

	private String copyright;
	
	private List<LinkCTA> footer;
	private List<IconCTA> social;
	
	/**
	 * GETTERS AND SETTERS
	 * @return GET and SET of variables
	 */
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public List<LinkCTA> getFooter() {
		return footer;
	}
	public void setFooter(List<LinkCTA> footer) {
		this.footer = footer;
	}
	public List<IconCTA> getSocial() {
		return social;
	}
	public void setSocial(List<IconCTA> social) {
		this.social = social;
	}
}
