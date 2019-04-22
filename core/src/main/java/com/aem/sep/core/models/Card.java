package com.aem.sep.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.aem.sep.core.utils.Utils;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Card {
	@Self
	Resource resource;
	
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
}