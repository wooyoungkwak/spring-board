package com.teraenergy.root.model.notice.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.teraenergy.root.model.notice.enums.NoticeType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity (name = "notice")
public class Notice implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	Integer noticeSeq;
	
	@Column
	String subject;
	
	@Column
	String content;
	
	@Column (name = "type")
	@Enumerated(EnumType.STRING)
	NoticeType noticeType;
	
	@Column  (nullable = false)
	Integer userSeq;
	
	@Column (nullable = false)
	Date regDt = new Date();
	
	@Column (nullable = false)
	Boolean isDel = false;
	
	@Column
	Date delDt;
	
}
