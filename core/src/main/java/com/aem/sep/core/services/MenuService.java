package com.aem.sep.core.services;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sep.core.constants.MenuEnum;
import com.aem.sep.core.dao.MenuDAO;
import com.aem.sep.core.interfaces.iMenu;
import com.aem.sep.core.utils.JCRUtils;
import com.aem.sep.core.utils.Utils;

public class MenuService implements iMenu{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public MenuDAO getModel(Resource resource) throws RepositoryException {
		try {
			if(resource == null) {
				return null;
			} else {
				Node node = resource.adaptTo(Node.class);
				
				return create(node, resource.getResourceResolver());
			}
		} catch(Exception e) {
			logger.error(e.getMessage());
            return null;
		}
	}

	/**
	 * @param node
	 * @param resourceResolver
	 * @return model
	 */
	private MenuDAO create(Node node, ResourceResolver resourceResolver) {
		MenuDAO model = new MenuDAO();
		
		model.getLogin().setLink(Utils.getPathFromResolver(resourceResolver, JCRUtils.getPropertyFromNode(node, MenuEnum.LOGIN_LINK.getValue())));
		model.getLogin().setTarget(JCRUtils.getPropertyFromNode(node, MenuEnum.LOGIN_TARGET.getValue()));

		model.getAssociate().setLink(Utils.getPathFromResolver(resourceResolver, JCRUtils.getPropertyFromNode(node, MenuEnum.ASSOCIATE_LINK.getValue())));
		model.getAssociate().setTarget(JCRUtils.getPropertyFromNode(node, MenuEnum.ASSOCIATE_TARGET.getValue()));
		
		model.getLogo().setImage(JCRUtils.getPropertyFromNode(node, MenuEnum.LOGO_IMAGE.getValue()));
		model.getLogo().setAlt(JCRUtils.getPropertyFromNode(node, MenuEnum.LOGO_ALT.getValue()));
		model.getLogo().setLink(Utils.getPathFromResolver(resourceResolver, JCRUtils.getPropertyFromNode(node, MenuEnum.LOGO_LINK.getValue())));
		model.getLogo().setTarget(JCRUtils.getPropertyFromNode(node, MenuEnum.LOGO_TARGET.getValue()));
		
		return model;
	}
}
