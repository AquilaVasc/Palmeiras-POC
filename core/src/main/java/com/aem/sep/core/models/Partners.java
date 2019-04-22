package com.aem.sep.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sep.core.dao.PartnersDAO;
import com.aem.sep.core.interfaces.iPartners;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Partners {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private PartnersDAO model;
		
	@Inject
	private Resource resource;
		
	@Inject
	private iPartners service;
		
	@PostConstruct
	public void init() {
		try {
			model = this.service.getModel(resource);
		} catch(Exception e) {
			logger.error("There was an error retrieving the partners model.", e);
		}
	}
		
	public PartnersDAO getModel() {
		return this.model;
	}
}
