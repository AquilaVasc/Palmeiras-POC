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

import com.aem.sep.core.constants.CarouselEnum;
import com.aem.sep.core.constants.ReusableEnum;
import com.aem.sep.core.dao.CarouselDAO;
import com.aem.sep.core.dao.ImageCTA;
import com.aem.sep.core.interfaces.iCarousel;
import com.aem.sep.core.utils.JCRUtils;
import com.aem.sep.core.utils.Utils;

@Component(
		service = iCarousel.class,
		immediate = true)
public class CarouselService implements iCarousel {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public CarouselDAO getModel(Resource resource) throws RepositoryException {
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
	private CarouselDAO create(Node node, Resource resource) {
		CarouselDAO model = new CarouselDAO();
		
		model.setCarousel(getCarouselList(resource));
		
		return model;
	}
	
	/**
	 * @param resource
	 * @return carouselList
	 */
	private List<ImageCTA> getCarouselList(Resource resource) {
		List<ImageCTA> carouselList = Collections.emptyList();
		
		Iterator<Resource> iterator = JCRUtils.getIteratorFromResource(resource, CarouselEnum.CAROUSEL_LIST.getValue());
		
		if(iterator == null || !iterator.hasNext()) {
			return null;
		} else {
			carouselList = new ArrayList<>();
			
			while(iterator.hasNext()) {
				Resource itemResource = iterator.next();
				if(itemResource == null) continue;
				
				Node itemNode = itemResource.adaptTo(Node.class);
				if(itemNode == null) continue;
				
				ImageCTA carouselItem = new ImageCTA(
						JCRUtils.getPropertyFromNode(itemNode, ReusableEnum.IMAGE.getValue()), 
						JCRUtils.getPropertyFromNode(itemNode, ReusableEnum.ALT.getValue()),
						JCRUtils.getPropertyFromNode(itemNode, ReusableEnum.TARGET.getValue()),
						Utils.getPathFromResolver(resource.getResourceResolver(), JCRUtils.getPropertyFromNode(itemNode, ReusableEnum.LINK.getValue())));
						
				carouselList.add(carouselItem);
			}
		}
		return carouselList;
	}
}
