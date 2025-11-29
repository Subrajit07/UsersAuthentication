package com.user.enums;

public enum CompanySize {
	Small ("0-20"),
	Medium ("21-50"),
	Large ("51-120");
	
	private String size;
	private CompanySize(String size) {
		this.size=size;
	}
}
