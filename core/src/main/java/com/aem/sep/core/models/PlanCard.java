package com.aem.sep.core.models;

import java.util.List;

import javax.inject.Inject;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.aem.sep.core.interfaces.iMultifield;
import com.aem.sep.core.utils.Utils;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PlanCard{
	private static final String MULTIFIELD = "benefitsList";
	
	@Self
	Resource resource;
	
	@Inject
	private iMultifield service;
	
	@Inject
	String linkTo;
	
	@Inject
	String price;
	
	@Inject
	String plans;
	
	public String getPlans() {
		return plans;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getLink() {
		return Utils.getPathFromResolver(resource.getResourceResolver(), linkTo);
	}
	public List<CardMultifieldItem> getItems() throws RepositoryException{
		return service.getCardMult(resource, MULTIFIELD);
	}
}
