package com.teraenergy.root.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teraenergy.root.model.notice.domain.Notice;
import com.teraenergy.root.model.notice.service.NoticeService;
import com.teraenergy.root.util.CHashMap;
import com.teraenergy.root.util.RequestUtil;

@Controller
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource
	NoticeService noticeService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@RequestMapping(value = "/notices", method = RequestMethod.GET)
	public String notice(HttpServletRequest request, Model model)  {
		
		RequestUtil requestUtil = null;
		try {
			requestUtil = new RequestUtil(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Notice> notices = noticeService.gets();
		model.addAttribute("notices", notices);
		return "notice/notice";
	}


	/** *********** POST ********* */ 
	
	@RequestMapping(value="/notice/get", method=RequestMethod.POST)
	@ResponseBody 
	public JsonNode get(@RequestBody String body) {
		JsonNode resultJson = null;
		RequestUtil requestUtil = null;
		try {
			requestUtil = new RequestUtil(body);
			CHashMap parameter =  requestUtil.getParameterMap();
			Integer noticeSeq = parameter.asInt("noticeSeq");
			Notice notice = noticeService.get(noticeSeq);
			resultJson = objectMapper.readTree(objectMapper.writeValueAsString(notice));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultJson;
	}
	

}
