package com.teraenergy.root.model.notice.service;

import java.util.List;

import com.teraenergy.root.model.notice.domain.Notice;

public interface NoticeService {

	Notice get(Integer noticeSeq);
	
	List<Notice> gets();
	
	void set(Notice notice);
	
	void sets(List<Notice> notices);
	
}
