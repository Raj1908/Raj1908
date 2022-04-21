package com.application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msf.MSFrameworkCore;

@RestController
public class TransformServiceController {

	@Autowired
	private MSFrameworkCore framework;

	@RequestMapping(value = "/demo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getTransformService(@PathVariable Map<String, String> pathParams,
			@RequestParam Map<String, String> requestParams, HttpServletRequest request) throws Exception {
		ResponseEntity<String> response = null;
		File jsonData = new File("Json-schema-example-data.json");
		JSONTokener jsonDataFile = new JSONTokener(new FileInputStream(jsonData));
		JSONObject jsonObject = new JSONObject(jsonDataFile);
		
		String demo = jsonObject.toString();
		
		System.out.println("JSON stream is "+demo);

		response = framework.init(requestParams, pathParams, "TransformService", request, demo).invoke();
		return response;
	}
}

