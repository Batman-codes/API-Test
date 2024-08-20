package createRequest;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class GetRequest extends BaseRequest{

	@Override
	public RequestSpecification buildRequest() {
		
		return RestAssured.given().
							baseUri(baseURI).
							headers(headers).
							queryParams(queryParam).
							log().all();
	
	}
	
	

}
