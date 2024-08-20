package createRequest;

import java.util.Map;

import io.restassured.specification.RequestSpecification;

public abstract class BaseRequest {
	
	protected String baseURI;
	protected String endpoints;
	protected Map<String,String> headers;
	protected Map<String,String> queryParam;
	protected String requestBody;
	protected Map<String, String> requestParams;
	protected String method;
	
	public abstract RequestSpecification buildRequest();

	public String getBaseURI() {
		return baseURI;
	}

	public void setBaseURI(String baseURI) {
		this.baseURI = baseURI;
	}

	public String getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(String endpoints) {
		this.endpoints = endpoints;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Map<String, String> getQueryParameter() {
		return queryParam;
	}

	public void setQueryParameter(Map<String, String> queryParam) {
		this.queryParam = queryParam;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public Map<String, String> getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(Map<String, String> requestParams) {
		this.requestParams = requestParams;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	
	
	

}
