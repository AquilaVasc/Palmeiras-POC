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

import com.aem.sep.core.constants.FooterEnum;
import com.aem.sep.core.dao.FooterDAO;
import com.aem.sep.core.dao.IconCTA;
import com.aem.sep.core.dao.LinkCTA;
import com.aem.sep.core.interfaces.iFooter;
import com.aem.sep.core.utils.JCRUtils;
import com.aem.sep.core.utils.Utils;

@Component(
		service = iFooter.class,
		immediate = true)
public class FooterService implements iFooter {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public FooterDAO getModel(Resource resource) throws RepositoryException {
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
	 * @param resourceResolver
	 * @return model
	 */
	private FooterDAO create(Node node, Resource resource) {
		FooterDAO model = new FooterDAO();
		
		model.setCopyright(JCRUtils.getPropertyFromNode(node, FooterEnum.COPYRIGHT.getValue()));
		
		model.setFooter(getMenuList(resource));
		model.setSocial(getSocialList(resource));
		
		return model;
	}
	
	/**
	 * @param resource
	 * @return menu list
	 */
	private List<LinkCTA> getMenuList(Resource resource) {
		List<LinkCTA> footerList = Collections.emptyList();
		
		Iterator<Resource> iterator = JCRUtils.getIteratorFromResource(resource, FooterEnum.FOOTER_LIST.getValue());
		
		if(iterator == null || !iterator.hasNext()) {
			return null;
		} else {
			footerList = new ArrayList<>();
			
			while(iterator.hasNext()) {
				Resource itemResource = iterator.next();
				if(itemResource == null) continue;
				
				Node itemNode = itemResource.adaptTo(Node.class);
				if(itemNode == null) continue;
				
				LinkCTA footerItem = new LinkCTA(
						JCRUtils.getPropertyFromNode(itemNode, FooterEnum.FOOTER_TEXT.getValue()),
						JCRUtils.getPropertyFromNode(itemNode, FooterEnum.FOOTER_TARGET.getValue()),
						Utils.getPathFromResolver(resource.getResourceResolver(), JCRUtils.getPropertyFromNode(itemNode, FooterEnum.FOOTER_LINK.getValue())));
				footerList.add(footerItem);
			}
		}
		return footerList;
	}
	
	/**
	 * @param resource
	 * @return social list
	 */
	private List<IconCTA> getSocialList(Resource resource) {
		List<IconCTA> socialList = Collections.emptyList();
		
		Iterator<Resource> iterator = JCRUtils.getIteratorFromResource(resource, FooterEnum.SOCIAL_LIST.getValue());
		
		if(iterator == null || !iterator.hasNext()) {
			return null;
		} else {
			socialList = new ArrayList<>();
			
			while(iterator.hasNext()) {
				Resource itemResource = iterator.next();
				if(itemResource == null) continue;
				
				Node itemNode = itemResource.adaptTo(Node.class);
				if(itemNode == null) continue;
				
				IconCTA socialItem = new IconCTA(
						JCRUtils.getPropertyFromNode(itemNode, FooterEnum.SOCIAL_ICON.getValue()),
						JCRUtils.getPropertyFromNode(itemNode, FooterEnum.SOCIAL_TARGET.getValue()),
						Utils.getPathFromResolver(resource.getResourceResolver(), JCRUtils.getPropertyFromNode(itemNode, FooterEnum.SOCIAL_LINK.getValue())));
				socialList.add(socialItem);
			}
		}
		return socialList;
	}
}
