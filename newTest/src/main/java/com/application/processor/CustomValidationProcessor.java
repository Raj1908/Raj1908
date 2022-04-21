package com.application.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.msf.common.exception.MSFException;
import com.msf.core.processor.MSFDefaultProcessor;

@Component
public class CustomValidationProcessor extends MSFDefaultProcessor {
	
	
	@Override
	public boolean doProcess(Map<String, Object> _msf_data_store, Map<String, Object> _step_config)
			throws MSFException {
		System.out.println("We entered the Custom Processor!!");
		
		Map<String, Object> msf_data_store = _msf_data_store;
		//Map<String, Object> addressLinkedHashMap = new HashMap<String, Object>();
		//addressLinkedHashMap = (Map)_msf_data_store.get("Address");
		
		
		//String Values = addressLinkedHashMap.get("Message").toString();
		//String resultJDBC = _msf_data_store.get("PinCode").toString();
		//System.out.println("JDBC Result==> "+resultJDBC);
		//String resultRest = _msf_data_store.get("Address").toString();
		//System.out.println("Rest Result==> "+resultRest);
		//System.out.println("Pincode Column Name==> "+columnName);
		
		StringBuilder columnValue = new StringBuilder(1000);
		StringBuilder fieldValue = new StringBuilder(1000);
		Map<String, Object> childDummyRowData = new LinkedHashMap<String, Object>();
		
		if (((Map) _step_config.get("properties")).containsKey("PreviousStepsOutput")) {
			List<Map<String, String>> previousStepsOutputList = (List<Map<String, String>>) ((Map) _step_config.get("properties"))
					.get("PreviousStepsOutput");
			for (Map<String, String> previousStepsOutput : previousStepsOutputList) {
				columnValue = new StringBuilder(1000);
				if(_msf_data_store.get(previousStepsOutput.get("tableName")) instanceof Map  ) {
					columnValue.append(((Map) _msf_data_store.get(previousStepsOutput.get("tableName"))).get(previousStepsOutput.get("columnName")).toString());
					
					if (previousStepsOutput.containsKey("field")) {
						String ClassName = ((Map) _msf_data_store.get(previousStepsOutput.get("tableName"))).get(previousStepsOutput.get("columnName")).getClass().getName();
						if (ClassName.equalsIgnoreCase("java.util.ArrayList")) {
							ArrayList<Map<String, Object>> al = (ArrayList) ((Map) _msf_data_store.get(previousStepsOutput.get("tableName"))).get(previousStepsOutput.get("columnName"));
							if (al != null && al.size() > 0) {
								childDummyRowData = al.get(0);
							}
							System.out.println("**************** Name is --> "+ (childDummyRowData.get(previousStepsOutput.get("field"))).toString());
						}
					}
					
					
				}
				
				System.out.println("**************** Result is --> "+ columnValue.toString());
			}
		}
		
		
		
		
		return true;
	}
}
