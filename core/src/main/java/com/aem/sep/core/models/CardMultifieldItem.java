package com.aem.sep.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardMultifieldItem {
	@Inject
	String itemText;
	
	@Inject
	String itemIcon;
	
	public String getItemIcon() {
		return itemIcon;
	}
	
	public String getItemText() {
		return itemText;
	}

}
