package com.aem.sep.core.interfaces;

import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;

import com.aem.sep.core.dao.MenuDAO;

public interface iMenu {

	public MenuDAO getModel(final Resource resource) throws RepositoryException;
	
}
