package com.aem.sep.core.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;

import com.aem.sep.core.interfaces.iMultifield;
import com.aem.sep.core.models.CardMultifieldItem;
import com.aem.sep.core.utils.JCRUtils;

@Component(
		service = iMultifield.class,
		immediate = true)
public class MultifieldService implements iMultifield {

	@Override
	public List<CardMultifieldItem> getCardMult(Resource resource, String multifield) throws RepositoryException {
		List<CardMultifieldItem> multiList = Collections.emptyList();
		Iterator<Resource> iterator = JCRUtils.getIteratorFromResource(resource, multifield);
		
		if (iterator == null || !iterator.hasNext()) {
			return null;
		} else {
			multiList = new ArrayList<>();
			
			while(iterator.hasNext()) {
				Resource itemResource = iterator.next();
				if(itemResource == null) continue;
				
				Node itemNode = itemResource.adaptTo(Node.class);
				if(itemNode == null) continue;
				
				CardMultifieldItem Item = getCardMultifieldItem(itemResource);
				if(Item == null) continue;
				
				multiList.add(Item);
			}
		}
		return multiList;
	}
	private CardMultifieldItem getCardMultifieldItem(Resource resource) throws RepositoryException {
		if (resource == null) {
			throw new RepositoryException("Resource can´t be null");
		}
		CardMultifieldItem model = resource.adaptTo(CardMultifieldItem.class);
		return model;
	}
}
