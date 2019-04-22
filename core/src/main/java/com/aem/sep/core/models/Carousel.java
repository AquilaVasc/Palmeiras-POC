package com.aem.sep.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sep.core.dao.CarouselDAO;
import com.aem.sep.core.interfaces.iCarousel;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Carousel {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private CarouselDAO model;
		
	@Inject
	private Resource resource;
		
	@Inject
	private iCarousel service;
		
	@PostConstruct
	public void init() {
		try {
			model = this.service.getModel(resource);
		} catch(Exception e) {
			logger.error("There was an error retrieving the carousel model.", e);
		}
	}
		
	public CarouselDAO getModel() {
		return this.model;
	}
}