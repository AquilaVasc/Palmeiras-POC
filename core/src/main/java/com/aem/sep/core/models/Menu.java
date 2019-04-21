package com.aem.sep.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sep.core.dao.MenuDAO;
import com.aem.sep.core.interfaces.iMenu;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Menu {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private MenuDAO model;
	
	@Inject
	private Resource resource;
	
	@Inject
	private iMenu service;
	
	@PostConstruct
	public void init() {
		try {
			model = this.service.getModel(resource);
		} catch(Exception e) {
			logger.error("There was an error retrieving the menu model.", e);
		}
	}
	
	public MenuDAO getModel() {
		return this.model;
	}
}
