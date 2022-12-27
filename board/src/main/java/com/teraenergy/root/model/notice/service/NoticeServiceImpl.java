package com.teraenergy.root.model.notice.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.teraenergy.root.model.notice.domain.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Resource
	EntityManager entityManager;

	@Override
	public Notice get(Integer noticeSeq) {
		String queryStr = " select * from notice where noticeSeq = :noticeSeq";
		Query query = entityManager.createNativeQuery(queryStr, Notice.class)
				.setParameter("noticeSeq", noticeSeq);
		return (Notice) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> gets() {
		String queryStr = " select * from Notice";
		Query query = entityManager.createNativeQuery(queryStr, Notice.class);
		return query.getResultList();
	}

	@Override
	public void set(Notice notice) {
		String queryStr = " insert into Notice values (:subject, :content, :type,  :userSeq, :regDt)";
		Query query = entityManager.createNativeQuery(queryStr, Notice.class)
				.setParameter("subject", notice.getSubject())
				.setParameter("content", notice.getContent())
				.setParameter("type", notice.getNoticeType().name())
				.setParameter("userSeq", notice.getUserSeq())
				.setParameter("regDt", new Date());
				;
		query.executeUpdate();		
	}

	@Override
	public void sets(List<Notice> notices) {
		// TODO Auto-generated method stub

	}

}
