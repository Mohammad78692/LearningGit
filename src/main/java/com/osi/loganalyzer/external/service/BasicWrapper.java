package com.osi.loganalyzer.external.service;


import java.util.Map;

public class BasicWrapper {

	public RestAssuredClientImpl client;

	public Map<String, Map<String, String>> dynamicMap;

	public String uri;
	public String soapAction="";
	public String headerParameters;
	
	
	public BasicWrapper(){
		client = new RestAssuredClientImpl();
	}
	
	/*public DataTable getDynamicTable(DataTable table) {
		List<List<String>> tableData = new ArrayList<List<String>>();
		List<String> column = new ArrayList<String>();
		List<String> columnValue = new ArrayList<String>();
		String requestFile = CommonUtility.getDataTableMap(table).get(REQUEST_FILE);
		String responseFile = CommonUtility.getDataTableMap(table).get(RESPONSE_FILE);
		String requestMethod = CommonUtility.getDataTableMap(table).get(REQUEST_METHOD);
		String pathParameters = CommonUtility.getDataTableMap(table).get(PATH_PARAMETERS);
		String queryParameters = CommonUtility.getDataTableMap(table).get(QUERY_PARAMETERS);
		if (uri != null) {
		column.add(URI);
		columnValue.add(uri);
		}
		column.add(HEADER_PARAMS);
		columnValue.add(headerParameters+soapAction);

		column.add(REQUEST_METHOD);
		columnValue.add(requestMethod);
		if (requestFile != null) {
			column.add(REQUEST_FILE);
			columnValue.add(requestFile);
		}
		if (responseFile != null) {
			column.add(RESPONSE_FILE);
			columnValue.add(responseFile);
		}
		if (pathParameters != null) {
			column.add(PATH_PARAMETERS);
			columnValue.add(pathParameters);
		}
		if (queryParameters != null) {
			column.add(QUERY_PARAMETERS);
			columnValue.add(queryParameters);
		}

		tableData.add(column);
		tableData.add(columnValue);
		
		DataTable actualTable = DataTable.create(tableData);

		return actualTable;
	}*/
}
