package testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import createRequest.BaseRequest;
import createRequest.RequestFactory;
import io.restassured.response.Response;

public class TestGetUser {
	
	@Test
	public void testGetUserPagination() {
		int pageNum = 1;
		int perPageNum;
		
		int page;
		int per_page;
		int total;
		int total_pages;
		
		Map<String, String> queryParam = new HashMap<>(); 
		Map<String, String> headers = new HashMap<>();
		
		headers.put("content-type",  String.valueOf("application/json"));
		
		do {
			queryParam.put("page",  String.valueOf(pageNum));
			BaseRequest request = RequestFactory.createRequest("https://reqres.in", 
														   "/api/users", 
														   headers, 
														   queryParam, 
														   null, 
														   null, 
														   "GET");
			
			Response response = request.
									buildRequest().
									get(request.getEndpoints()).
								then().
									log().all().
									statusCode(200).
									extract().
									response();
			
			/*
			 *  "page": 1,
    		 * 	"per_page": 6,
    		 *	"total": 12,
    		 *	"total_pages": 2,
    		 */
			
			//Get User counts from the Body
			page = response.jsonPath().getInt("page");
			per_page = response.jsonPath().getInt("per_page");
			total = response.jsonPath().getInt("total");
			total_pages = response.jsonPath().getInt("total_pages");

			//Verify per page data and per page records are matching
			perPageNum = response.jsonPath().getList("data").size();
			Assert.assertEquals(per_page, perPageNum);
			assert per_page == perPageNum : "per Page Number is correct" + perPageNum;
			
			
			System.out.println("Total users : " + total);
			
			pageNum = pageNum +1;

		}while(page < total_pages);
		
	}

}
