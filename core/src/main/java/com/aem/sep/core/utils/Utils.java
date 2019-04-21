package com.aem.sep.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.Page;

public class Utils {

	private static final String WWW = "www.";
	private static final String HTTP = "http:";
    private static final String HTTPS = "https:";
    private static final String HTML_EXTENSION = ".html";
    private static final String PDF_EXTENSION = ".pdf";
	
	/**
     * Returns the page path according to the mapping of the resolver.
     *
     * @param  resolver  ResourceResolver Instance
     * @param  path  Path of the page to be mapped
     * @return returns mapped path
     */
    public static String getPathFromResolver(ResourceResolver resolver, String path) {
        if (StringUtils.isBlank(path)) {
        	return path;
        }
        
        path = path.replaceAll(" ", "");
        
        if (isExternal(path)) {
            path = addHttps(path);
        	return path;
        }
        
        if (resolver == null) {
            return path;
        }
        
        try {
            String resolvedPath = resolver.map(path);
            if (StringUtils.isBlank(resolvedPath)) {
                return path;
            }
            return addHtmlExtension(resolvedPath);
        } catch (Exception e) {
            return path;
        }
    }
    
    /**
     * Verify if url is external
     * @param url Path url the page
     * @return Return if url is external or not
     */
    private static boolean isExternal(final String url) {
        return (StringUtils.isNotBlank(url) && (url.startsWith(HTTP) || url.startsWith(HTTPS) || url.startsWith(WWW)));
    }
    
    /**
     * Return String url formated with https://
     * @param url Path url the page
     * @return Return String https:// + url
     */
    private static String addHttps(final String url) {
    	if(url.startsWith(WWW)) 
    		return "https://" + url;
    	return url;
    }

    /**
     * Add html extension
     * @param path Add html extension 
     * @return Return url with html extension added
     */
    private static String addHtmlExtension(String path) {
        if (StringUtils.isBlank(path)) return path;
        
        String anchor = getAnchor(path);
        String tempPath = removeAnchor(path);
        
        if ( tempPath.endsWith(HTML_EXTENSION) || tempPath.endsWith(PDF_EXTENSION) ) {
            return tempPath + anchor;
        } else {
            return tempPath + HTML_EXTENSION  + anchor;
        }
    }

    /**
     * Get anchor 
     * @param url Url used to get anchor
     * @return Return anchor
     */
    private static String getAnchor(String url) {
        try {
            if (StringUtils.contains(url, "#")) {
                String anchor = url.substring(url.indexOf("#"), url.length());
                if (StringUtils.endsWith(anchor, HTML_EXTENSION) || StringUtils.endsWith(anchor, PDF_EXTENSION)) {
                    anchor = anchor.substring(0, anchor.indexOf("."));
                }
                return anchor;
            } else {
                return "";
            }
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }
    
    /**
     * 
     * @param url
     * @return
     */
    private static String removeAnchor(String url) {
        try {
            if (StringUtils.contains(url, "#")) {
                return url.substring(0, url.indexOf("#"));
            } else {
                return url;
            }
        } catch (Exception e) {
            return url;
      }
    }
    
    
    
    /**
	 * Returns the page that represents the first parent
	 * @param resource
	 * @return First Parent Page
	 */
	public static Page getFirstParentPage(Resource resource) {
        Resource r = getFirstParent(resource);
        
        if (r!=null)
        	return r.adaptTo(Page.class);
        else
     	   return null;
	}
	
    public static Resource getFirstParent(Resource resource) {
        try {
            if (JCRUtils.checkIsJcrContentNodeFromResource(resource)) {
                if (JCRUtils.checkNoIndex(resource)) {
                    return null;
                }
            }
            if ( JCRUtils.isCqPage(resource) ) {
                return resource;
            }
            Resource parent = resource.getParent();
            do {
                if (parent == null) return null;
                
                if (JCRUtils.checkIsJcrContentNodeFromResource(parent)) {
                    if (JCRUtils.checkNoIndex(parent)) {
                        return null;
                    }
                }
                if (JCRUtils.isCqPage(parent)) {
                    return parent;
                }
                parent = parent.getParent();
            } while (parent != null);
            return (parent == null) ? null : parent;
        } catch (Exception e) {
            return  null;
        }
    }
    
    /**
	 * Returns the page that represents the project root
	 * @param resourcePage Page that will be used as origin to find root
	 * @return Root page
	 */
	public static Page findSiteRoot(Page resourcePage) {
		Page rootPage = resourcePage;
		while ((rootPage != null) && (!isSiteRoot(rootPage))) {
			rootPage = rootPage.getParent();
		}
		return rootPage;
	}
	
	/**
	 * Verifies that the page is the root of the site
	 * @param page Page to be verified
	 * @return Returns true or false depending on whether the page is site root or not
	 */
	public static boolean isSiteRoot(Page page) {
		Resource res = page.getContentResource();
		if (res == null) {
			return false;
		}
		ValueMap vm = (ValueMap) res.adaptTo(ValueMap.class);
		return ((Boolean)vm.get("siteRoot", Boolean.valueOf(false))).booleanValue();
	}
}
