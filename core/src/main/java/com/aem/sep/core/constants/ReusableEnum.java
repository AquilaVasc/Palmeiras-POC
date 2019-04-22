package com.aem.sep.core.constants;

public enum ReusableEnum {

	TITLE("title"),
	SUBTITLE("subtitle"),
	DESCRIPTION("description"),
	
	ALT("alt"),
	LINK("link"),
	IMAGE("image"),
	TARGET("target");
	
	private final String value;

	ReusableEnum(final String path) {
        this.value = path;
    }

	public String getValue() {
		return this.value;
	}
}
