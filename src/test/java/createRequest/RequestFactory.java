package createRequest;

import java.util.Map;

public class RequestFactory {
	
	public static BaseRequest createRequest(String baseURI,
											String endPoints,
											Map<String, String> headers,
											Map<String,String> queryParam,
											String requestBody,
											Map<String,String>  requestParam,
											String method) {
		
		BaseRequest request = null;
		
		if(method != null) {
			switch (method.toUpperCase()) {
			
			case "GET" : 
				request = new GetRequest();
				break;
			case "Post" :
				request = new PostRequest();
				break;
			case "PUT" :
				request = new PutRequest();
				break;
			case "Patch" :
				request = new PatchRequest();
				break;
			case "Delete" :
				request = new DeleteRequest();
				break;
			case "Head" :
				request = new HeadRequest();
				break;
			case "OPTIONS" :
				request = new OptionsRequest();
				break;
			default:
				throw new IllegalArgumentException("Illeagal Method -> " + request);
			}
		}
		if(request != null) {
	            request.setBaseURI(baseURI);
	            request.setEndpoints(endPoints);
	            request.setHeaders(headers);
	            request.setQueryParameter(queryParam);
	            request.setRequestBody(requestBody);
	            request.setRequestParams(requestParam);
	            
	        
		}
		return request;
	}

}
