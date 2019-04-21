package com.aem.sep.core.dao;

import java.util.List;

public class MenuDAO {
	
	private ImageCTA logo;
	
	private LinkCTA login;
	private LinkCTA associate;

	private List<LinkCTA> menu;
	private List<IconCTA> social;
	
	/**
	 * GETTERS AND SETTERS
	 * @return GET and SET of variables
	 */
	public ImageCTA getLogo() {
		return logo;
	}
	public void setLogo(ImageCTA logo) {
		this.logo = logo;
	}
	public LinkCTA getLogin() {
		return login;
	}
	public void setLogin(LinkCTA login) {
		this.login = login;
	}
	public LinkCTA getAssociate() {
		return associate;
	}
	public void setAssociate(LinkCTA associate) {
		this.associate = associate;
	}
	public List<LinkCTA> getMenu() {
		return menu;
	}
	public void setMenu(List<LinkCTA> menu) {
		this.menu = menu;
	}
	public List<IconCTA> getSocial() {
		return social;
	}
	public void setSocial(List<IconCTA> social) {
		this.social = social;
	}
}
