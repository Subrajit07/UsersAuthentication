package com.user.enums;

public enum WorkingDays {
	MON ("Monday"),
	TUE ("Tuesday"),
	WED ("Wednesday"),
	THU ("Thursday"),
	FRI ("Friday"), 
	SAT ("Saturday"),
	SUN ("Sunday");
	
	private String day;
	private WorkingDays(String day) {
		this.day=day;
	}
}
