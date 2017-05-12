package com.zinnaworks.kbell;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zinnaworks.kbell.service.DailyService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Resource(name = "OracleService")
	DailyService service;

	@RequestMapping(value = "/daily", method = RequestMethod.GET)
	public @ResponseBody Object getDaily(HttpServletRequest request, HttpServletResponse response) {

		String crud = StringUtils.defaultIfEmpty(request.getParameter("crud"), "none");
		
		logger.info("GET [" + "CRUD : " + crud + "]");
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		if(checkNone("","","","")){
			resultMap.put("result", "false");
			resultMap.put("reason", "No Parameter");
			return resultMap;
		}
		
		return service.crudService(crud);
	}

	@RequestMapping(value = "/daily", method = RequestMethod.POST)
	public @ResponseBody Object postDaily(HttpServletRequest request, HttpServletResponse response) {

		String crud = StringUtils.defaultIfEmpty(request.getParameter("crud"), "none");
		String name = StringUtils.defaultIfEmpty(request.getParameter("name"), "none");
		String email = StringUtils.defaultIfEmpty(request.getParameter("email"), "none");
		String detail = StringUtils.defaultIfEmpty(request.getParameter("detail"), "none");
		String age = StringUtils.defaultIfEmpty(request.getParameter("age"), "none");
		
		logger.info("POST [" + "CRUD : " + crud + ", 이름 : " + name + ", 이메일 : " + email + ", 내용 : " + detail + ", 나이 : " + age + "]");
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		if(checkNone(name,email,detail,age)){
			resultMap.put("result", "false");
			resultMap.put("reason", "No Parameter");
			return resultMap;
		}
		
		return service.crudService(crud, name, email, detail, age);
	}
	
	public boolean checkNone(String name, String email, String detail, String age) {
		if (name.equals("none") && email.equals("none") && detail.equals("none")
				&& age.equals("none")) {
			return true;
		}
		return false;
	}

}
