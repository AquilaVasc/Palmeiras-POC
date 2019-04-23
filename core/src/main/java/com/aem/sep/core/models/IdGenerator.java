package com.aem.sep.core.models;

import java.util.UUID;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class IdGenerator {
	String id;
	
	public String getRamdomId() {
		return UUID.randomUUID().toString();
	}
}
