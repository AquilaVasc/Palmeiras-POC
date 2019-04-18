package sep.aem.SEP.core.models;

import sep.aem.SEP.core.utils.Utils;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Menu {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private Resource resource;
	
	public void init() {
		try {
			Page page = Utils.getFirstParentPage(resource);
			Page root = Utils.findSiteRoot(page);
			
		} catch(Exception e) {
			logger.error("There was an error retrieving the menu model.", e);
		}
	}
}
