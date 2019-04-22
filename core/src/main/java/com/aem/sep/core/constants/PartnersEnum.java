package com.aem.sep.core.constants;

public enum PartnersEnum {
	
	PARTNERS_LIST("partnersList");
	
	private final String value;

	PartnersEnum(final String path) {
        this.value = path;
    }

	public String getValue() {
		return this.value;
	}
	
}
