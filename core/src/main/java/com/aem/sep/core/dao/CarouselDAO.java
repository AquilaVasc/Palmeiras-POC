package com.aem.sep.core.dao;

import java.util.List;

public class CarouselDAO {

	private List<ImageCTA> carousel;
	
	/**
	 * GETTERS AND SETTERS
	 * @return GET and SET of variables
	 */
	public List<ImageCTA> getCarousel() {
		return carousel;
	}

	public void setCarousel(List<ImageCTA> carousel) {
		this.carousel = carousel;
	}
}
