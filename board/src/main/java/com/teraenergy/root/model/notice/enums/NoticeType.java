package com.teraenergy.root.model.notice.enums;

import lombok.Getter;

@Getter
public enum NoticeType {

	ALARM("공지"),
	
	NEWS("소식")
	;
	
	private String value;
	
	NoticeType(String value) {
		this.value = value;
	}
	
}
