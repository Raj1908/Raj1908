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
public class CustomerServiceController {

	@Autowired
	private MSFrameworkCore framework;

	@RequestMapping(value = "cust", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getCustomerService(@PathVariable Map<String, String> pathParams,
			@RequestParam Map<String, String> requestParams, HttpServletRequest request) throws Exception {
		ResponseEntity<String> response = null;

		response = framework.init(requestParams, pathParams, "Customer Service", request, null).invoke();
		return response;
	}
}

