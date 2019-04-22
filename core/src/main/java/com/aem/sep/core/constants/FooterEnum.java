package com.aem.sep.core.constants;

public enum FooterEnum {

	COPYRIGHT("copyright"),
	
	FOOTER_LIST("footerList"),
	FOOTER_TEXT("footerText"),
	FOOTER_LINK("footerLink"),
	FOOTER_TARGET("footerTarget"),
	
	SOCIAL_LIST("socialList"),
	SOCIAL_ICON("socialIcon"),
	SOCIAL_LINK("socialLink"),
	SOCIAL_TARGET("socialTarget");
	
	private final String value;

	FooterEnum(final String path) {
        this.value = path;
    }

	public String getValue() {
		return this.value;
	}
}
