package sep.aem.SEP.core.utils;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;

public class JCRUtils {

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
