package com.aem.sep.core.interfaces;

import java.util.List;

import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;

import com.aem.sep.core.models.CardMultifieldItem;

public interface iMultifield {
	public List<CardMultifieldItem> getCardMult(Resource resource, String multifield) throws RepositoryException;
}
