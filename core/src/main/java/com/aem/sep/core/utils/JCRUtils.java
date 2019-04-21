package com.aem.sep.core.utils;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;

public class JCRUtils {

	/**
	 * @param node
	 * @param propertyName
	 * @return property value from node
	 */
	public static String getPropertyFromNode(Node node, String propertyName) {
        try {
            return node.hasProperty(propertyName) && node.getProperty(propertyName) != null ? node.getProperty(propertyName).getValue().toString() : StringUtils.EMPTY ;
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }
	
    public static boolean checkIsJcrContentNodeFromResource(final Resource resource) {
        Node node = resource.adaptTo(Node.class);
        try {
            return node.isNodeType("cq:PageContent") ? true : false;
        } catch (RepositoryException e) {
            return false;
        }
    }
    
    public static boolean checkNoIndex(final Resource resource) {
        if (resource.getValueMap().containsKey("noindex")) {
           return resource.getValueMap().get("noindex", Boolean.class) ? true : false;
        } else {
            return false;
        }
    }
    
    public static boolean isCqPage(final Resource resource) {
        String primaryType = resource.getValueMap().get("jcr:primaryType") == null ? null : resource.getValueMap().get("jcr:primaryType").toString();
        if (StringUtils.isBlank(primaryType)) {
            return false;
        } else {
            return primaryType.equals("cq:Page") ? true : false;
        }
    }
}
