package sep.aem.SEP.core.utils;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.Page;

public class Utils {

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
