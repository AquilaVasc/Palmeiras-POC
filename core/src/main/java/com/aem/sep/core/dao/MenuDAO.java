package com.aem.sep.core.dao;

import java.util.List;

public class MenuDAO {
	
	private imageCTA logo;
	
	private linkCTA login;
	private linkCTA associate;

	private List<linkCTA> menu;
	private List<iconCTA> social;
	
	/**
	 * CONSTRUCTOR
	 */
	public MenuDAO() {
		super();
	}
	
	/**
	 * GETTERS AND SETTERS
	 * @return GET and SET of variables
	 */
	public imageCTA getLogo() {
		return logo;
	}
	public void setLogo(imageCTA logo) {
		this.logo = logo;
	}
	public linkCTA getLogin() {
		return login;
	}
	public void setLogin(linkCTA login) {
		this.login = login;
	}
	public linkCTA getAssociate() {
		return associate;
	}
	public void setAssociate(linkCTA associate) {
		this.associate = associate;
	}
	public List<linkCTA> getMenu() {
		return menu;
	}
	public void setMenu(List<linkCTA> menu) {
		this.menu = menu;
	}
	public List<iconCTA> getSocial() {
		return social;
	}
	public void setSocial(List<iconCTA> social) {
		this.social = social;
	}
}
