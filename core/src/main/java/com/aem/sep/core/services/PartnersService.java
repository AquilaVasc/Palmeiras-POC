package com.aem.sep.core.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sep.core.constants.PartnersEnum;
import com.aem.sep.core.constants.ReusableEnum;
import com.aem.sep.core.dao.ImageCTA;
import com.aem.sep.core.dao.PartnersDAO;
import com.aem.sep.core.interfaces.iPartners;
import com.aem.sep.core.utils.JCRUtils;
import com.aem.sep.core.utils.Utils;

@Component(
		service = iPartners.class,
		immediate = true)
public class PartnersService implements iPartners {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public PartnersDAO getModel(Resource resource) throws RepositoryException {
		try {
			if(resource == null) {
				return null;
			} else {
				Node node = resource.adaptTo(Node.class);
				
				return create(node, resource);
			}
		} catch(Exception e) {
			logger.error(e.getMessage());
            return null;
		}
	}

	/**
	 * @param node
	 * @param resource
	 * @return model
	 */
	private PartnersDAO create(Node node, Resource resource) {
		PartnersDAO model = new PartnersDAO();
		
		model.setTitle(JCRUtils.getPropertyFromNode(node, ReusableEnum.TITLE.getValue()));
		
		model.setPartners(getPartnersList(resource));
		
		return model;
	}
	
	/**
	 * @param resource
	 * @return partnersList
	 */
	private List<ImageCTA> getPartnersList(Resource resource) {
		List<ImageCTA> partnersList = Collections.emptyList();
		
		Iterator<Resource> iterator = JCRUtils.getIteratorFromResource(resource, PartnersEnum.PARTNERS_LIST.getValue());
		
		if(iterator == null || !iterator.hasNext()) {
			return null;
		} else {
			partnersList = new ArrayList<>();
			
			while(iterator.hasNext()) {
				Resource itemResource = iterator.next();
				if(itemResource == null) continue;
				
				Node itemNode = itemResource.adaptTo(Node.class);
				if(itemNode == null) continue;
				
				ImageCTA partnersItem = new ImageCTA(
						JCRUtils.getPropertyFromNode(itemNode, ReusableEnum.IMAGE.getValue()), 
						JCRUtils.getPropertyFromNode(itemNode, ReusableEnum.ALT.getValue()),
						JCRUtils.getPropertyFromNode(itemNode, ReusableEnum.TARGET.getValue()),
						Utils.getPathFromResolver(resource.getResourceResolver(), JCRUtils.getPropertyFromNode(itemNode, ReusableEnum.LINK.getValue())));
						
				partnersList.add(partnersItem);
			}
		}
		return partnersList;
	}
}
