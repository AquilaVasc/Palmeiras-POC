package com.aem.sep.core.constants;

public enum MenuEnum {

	LOGIN_LINK("loginLink"),
	LOGIN_TARGET("loginTarget"),
	ASSOCIATE_LINK("associateLink"),
	ASSOCIATE_TARGET("associateTarget"),
	
	LOGO_ALT("logoAlt"),
	LOGO_LINK("logoLink"),
	LOGO_IMAGE("logoImage"),
	LOGO_TARGET("logoTarget"),
	
	MENU_LIST("menuList"),
	MENU_TEXT("menuText"),
	MENU_LINK("menuLink"),
	MENU_TARGET("menuTarget"),
	
	SOCIAL_LIST("socialList"),
	SOCIAL_ICON("socialIcon"),
	SOCIAL_LINK("socialLink"),
	SOCIAL_TARGET("socialTarget");
	
	private final String value;

	MenuEnum(final String path) {
        this.value = path;
    }

	public String getValue() {
		return this.value;
	}
}
