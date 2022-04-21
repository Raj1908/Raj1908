package com.application.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msf.MSFrameworkCore;

@RestController
public class TranformXMLController {

	@Autowired
	private MSFrameworkCore framework;
	

	@RequestMapping(value = "/transform", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getTranformXML(@PathVariable Map<String, String> pathParams,
			@RequestParam Map<String, String> requestParams, HttpServletRequest request) throws Exception {
		ResponseEntity<String> response = null;

		String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + 
				"<root>\n" + 
				"	<student>\n" + 
				"		<firstName>Rajat</firstName>\n" + 
				"		<lastName>Kulkarni</lastName>\n" + 
				"		<mobile>8421519609</mobile>\n" + 
				"	</student>\n" + 
				"</root>";
		
		response = framework.init(requestParams, pathParams, "TranformXML", request, xmlStr).invoke();
		return response;
	}
}

