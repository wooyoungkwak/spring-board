package com.teraenergy.root.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;

public class RequestUtil {

    private CHashMap<String, Object> parameters = null;
    private boolean multipart = false;

    private HttpServletRequest request;

    public RequestUtil(HttpServletRequest request) throws Exception{
        this.parameters = new CHashMap<String, Object>();
        parseRequest(request);
        this.request = request;
    }

    public RequestUtil(String jsonString){
        this.parameters = new CHashMap<String, Object>();
        parseRequestJson(jsonString);
    }

    private void parseRequest(HttpServletRequest request) throws Exception {
        // parameter
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String value = request.getParameter(parameterName);

            if (request.getMethod() == "GET")
                value = urlDecode(value);
            this.parameters.put(parameterName, value);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseRequestJson(String jsonString) {
        JsonNode node = null;
        try {
            node = JsonUtil.toJsonNode(jsonString);
            this.parameters = new ObjectMapper().convertValue(node, CHashMap.class);
        } catch (Exception e) {
        }
    }

    public void setParameterToModel(Model model){
        // parameter
        for (Map.Entry<String, Object> entry : this.parameters.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }

    }

    public void setParameter(String key, Object value) {
        request.setAttribute(key, value);
    }

    @SuppressWarnings("rawtypes")
	public CHashMap getParameterMap() {
        return this.parameters;
    }

    public boolean isMultipart(){
        return this.multipart;
    }
    
    public String urlDecode(String value) {
    	String result = null;
    	try {
    		result = URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return result;
    }

}
