package com.aem.sep.core.constants;

public enum CarouselEnum {

	CAROUSEL_LIST("carouselList");
	
	private final String value;

	CarouselEnum(final String path) {
        this.value = path;
    }

	public String getValue() {
		return this.value;
	}
}
