package com.osi.loganalyzer.model;

import java.io.Serializable;

public class InvokeModel implements Serializable {

	/**
	 * 
	 */
	
	
	public InvokeModel(String end_point, String queryParameters, String headers, String resource_location, String uri,
			String request_name, String http_method) {
		super();
		this.end_point = end_point;
		this.queryParameters = queryParameters;
		this.headers = headers;
		this.resource_location = resource_location;
		this.uri = uri;
		this.request_name = request_name;
		this.http_method = http_method;
	}
	public String getHeaders() {
		return headers;
	}
	public void setHeaders(String headers) {
		this.headers = headers;
	}
	public InvokeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
	private String end_point;
	private String queryParameters;
	private String headers;
	
	public String getQueryParameters() {
		return queryParameters;
	}
	public void setQueryParameters(String queryParameters) {
		this.queryParameters = queryParameters;
	}
	private String resource_location;
	private String uri;
	public String getEnd_point() {
		return end_point;
	}
	public void setEnd_point(String end_point) {
		this.end_point = end_point;
	}
	public String getResource_location() {
		return resource_location;
	}
	public void setResource_location(String resource_location) {
		this.resource_location = resource_location;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getRequest_name() {
		return request_name;
	}
	public void setRequest_name(String request_name) {
		this.request_name = request_name;
	}
	public String getHttp_method() {
		return http_method;
	}
	public void setHttp_method(String http_method) {
		this.http_method = http_method;
	}
	private String request_name;
	private String http_method;

	@Override
	public String toString() {
		return "InvokeModel [end_point=" + end_point + ", queryParameters=" + queryParameters + ", headers=" + headers
				+ ", resource_location=" + resource_location + ", uri=" + uri + ", request_name=" + request_name
				+ ", http_method=" + http_method + "]";
	}
	
	
	}
