package com.aem.sep.core.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sep.core.constants.MenuEnum;
import com.aem.sep.core.dao.MenuDAO;
import com.aem.sep.core.dao.IconCTA;
import com.aem.sep.core.dao.ImageCTA;
import com.aem.sep.core.dao.LinkCTA;
import com.aem.sep.core.interfaces.iMenu;
import com.aem.sep.core.utils.JCRUtils;
import com.aem.sep.core.utils.Utils;

@Component(
		service = iMenu.class,
		immediate = true)
public class MenuService implements iMenu{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public MenuDAO getModel(Resource resource) throws RepositoryException {
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
	private MenuDAO create(Node node, Resource resource) {
		MenuDAO model = new MenuDAO();
		
		model.setLogo(
				new ImageCTA(
						JCRUtils.getPropertyFromNode(node, MenuEnum.LOGO_IMAGE.getValue()),
						JCRUtils.getPropertyFromNode(node, MenuEnum.LOGO_ALT.getValue()),
						JCRUtils.getPropertyFromNode(node, MenuEnum.LOGO_TARGET.getValue()),
						Utils.getPathFromResolver(resource.getResourceResolver(), JCRUtils.getPropertyFromNode(node, MenuEnum.LOGO_LINK.getValue()))));
		
		model.setLogin(
				new LinkCTA(
						StringUtils.EMPTY,
						JCRUtils.getPropertyFromNode(node, MenuEnum.LOGIN_TARGET.getValue()),
						Utils.getPathFromResolver(resource.getResourceResolver(), JCRUtils.getPropertyFromNode(node, MenuEnum.LOGIN_LINK.getValue()))));

		model.setAssociate(
				new LinkCTA(
						StringUtils.EMPTY,
						JCRUtils.getPropertyFromNode(node, MenuEnum.ASSOCIATE_TARGET.getValue()),
						Utils.getPathFromResolver(resource.getResourceResolver(), JCRUtils.getPropertyFromNode(node, MenuEnum.ASSOCIATE_LINK.getValue()))));
		
		model.setMenu(getMenuList(resource));
		model.setSocial(getSocialList(resource));
		
		return model;
	}
	
	/**
	 * @param resource
	 * @return menuList
	 */
	private List<LinkCTA> getMenuList(Resource resource) {
		List<LinkCTA> menuList = Collections.emptyList();
		
		Iterator<Resource> iterator = JCRUtils.getIteratorFromResource(resource, MenuEnum.MENU_LIST.getValue());
		
		if(iterator == null || !iterator.hasNext()) {
			return null;
		} else {
			menuList = new ArrayList<>();
			
			while(iterator.hasNext()) {
				Resource itemResource = iterator.next();
				if(itemResource == null) continue;
				
				Node itemNode = itemResource.adaptTo(Node.class);
				if(itemNode == null) continue;
				
				LinkCTA menuItem = new LinkCTA(
						JCRUtils.getPropertyFromNode(itemNode, MenuEnum.MENU_TEXT.getValue()),
						JCRUtils.getPropertyFromNode(itemNode, MenuEnum.MENU_TARGET.getValue()),
						Utils.getPathFromResolver(resource.getResourceResolver(), JCRUtils.getPropertyFromNode(itemNode, MenuEnum.MENU_LINK.getValue())));
				menuList.add(menuItem);
			}
		}
		return menuList;
	}
	
	/**
	 * @param resource
	 * @return socialList
	 */
	private List<IconCTA> getSocialList(Resource resource) {
		List<IconCTA> socialList = Collections.emptyList();
		
		Iterator<Resource> iterator = JCRUtils.getIteratorFromResource(resource, MenuEnum.SOCIAL_LIST.getValue());
		
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
						JCRUtils.getPropertyFromNode(itemNode, MenuEnum.SOCIAL_ICON.getValue()),
						JCRUtils.getPropertyFromNode(itemNode, MenuEnum.SOCIAL_TARGET.getValue()),
						Utils.getPathFromResolver(resource.getResourceResolver(), JCRUtils.getPropertyFromNode(itemNode, MenuEnum.SOCIAL_LINK.getValue())));
				socialList.add(socialItem);
			}
		}
		return socialList;
	}
}
