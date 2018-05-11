package com.osi.loganalyzer.external.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.osi.loganalyzer.model.InvokeModel;

import static com.osi.loganalyzer.external.service.WrapperConstants.*;

import cucumber.api.DataTable;
import io.restassured.response.Response;

@Service
public class WrapperService extends BasicWrapper{

	InvokeModel invokeModel1;
	
	public StringBuilder hitExternalService() {
		StringBuilder all=new StringBuilder("");
		if(invokeModel1.getRequest_name()==null){
			File folder = new File(invokeModel1.getResource_location());
			File[] listOfFiles = folder.listFiles();
			for(int i = 0; i < listOfFiles.length; i++){
			String filename = listOfFiles[i].getName();
			if(filename.endsWith(".xml"))
			{
				invokeModel1.setRequest_name(filename);
				DataTable actualTable = getDynamicTableFromList();
				RestAssuredClientImpl client = new RestAssuredClientImpl();
				Map<String, Object> response = null;
				try {
					response = client.invokeService(actualTable, dynamicMap);
				}catch (FileNotFoundException e) {
					e.printStackTrace();
					throw new RuntimeException(e.toString());
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e.toString());
				}
				//System.out.println(filename);
				//return response;
				Response actualResponse = (Response) response.get("ACTUAL_RESPONSE");
				String output = actualResponse.asString();
				if(!all.toString().contains(output))
				all.append(output);
			
			}
			   }
		}else{
			StringBuilder individualTestOutPut=new StringBuilder("");
			DataTable actualTable = getDynamicTableFromList();
			RestAssuredClientImpl client = new RestAssuredClientImpl();
			Map<String, Object> response = null;
			try {
				response = client.invokeService(actualTable, dynamicMap);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e.toString());
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.toString());
			}
			Response actualResponse = (Response) response.get("ACTUAL_RESPONSE");
			String output = actualResponse.asString();
			individualTestOutPut.append(output);
			all=individualTestOutPut;
			
		}
		/*DataTable actualTable = getDynamicTableFromList();
		RestAssuredClientImpl client = new RestAssuredClientImpl();
		Map<String, Object> response = null;
		try {
			response = client.invokeService(actualTable, dynamicMap);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.toString());
		}
			*/
		return all;
	}
	
	public DataTable getDynamicTableFromList() {
		List<List<String>> tableData = new ArrayList<List<String>>();
		List<String> column = new ArrayList<String>();
		List<String> columnValue = new ArrayList<String>();
		String requestFile = invokeModel1.getRequest_name();
		String responseFile = "";
		String requestMethod = invokeModel1.getHttp_method();
		//ToDo to add pathparameters
		String pathParameters = "";
		String queryParameters = invokeModel1.getQueryParameters();
		column.add(URI);
		if (requestMethod.equals("GET") || requestMethod.equals("DELETE")) {
			columnValue.add(uri);
		} else {
			columnValue.add(uri);
		}

		column.add(HEADER_PARAMS);
		columnValue.add(headerParameters);

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
	}
	
public void buildUri(InvokeModel invokeModel) {
	      invokeModel1=invokeModel;
		uri =invokeModel1.getUri();
		dynamicMap = new HashMap<String, Map<String, String>>();
		Map<String, String> endPoint = new HashMap<String, String>();
		endPoint.put(END_PONT, invokeModel1.getEnd_point());
		endPoint.put(RESOURCE_PATH_URL, "file:"+invokeModel1.getResource_location());
		dynamicMap.put(HOST_DETAILS, endPoint);
		//ToDo to add headers
		if(invokeModel1.getHeaders() != null)
		headerParameters = invokeModel1.getHeaders();
		else{
			headerParameters=HEADER_PARAMETERS_REST;
		}
	}

}
