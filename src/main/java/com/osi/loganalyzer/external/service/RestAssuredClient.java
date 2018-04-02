package com.osi.loganalyzer.external.service;


import java.io.IOException;
import java.util.Map;

import cucumber.api.DataTable;

/**
 * The Interface RestAssuredClient.
 */
public interface RestAssuredClient {
	
	public Map<String, Object> invokeService(DataTable table, Map<String, Map<String, String>> dynamicMap)
			throws IOException;
}
