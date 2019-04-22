package com.aem.sep.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sep.core.dao.FooterDAO;
import com.aem.sep.core.interfaces.iFooter;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Footer {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private FooterDAO model;
	
	@Inject
	private Resource resource;
	
	@Inject
	private iFooter service;
	
	@PostConstruct
	public void init() {
		try {
			model = this.service.getModel(resource);
		} catch(Exception e) {
			logger.error("There was an error retrieving the footer model.", e);
		}
	}
	
	public FooterDAO getModel() {
		return this.model;
	}
}