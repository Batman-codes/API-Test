package testcases;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import reqpojos.CreateUserRequest;
import respojos.CreateUserResponse;
import respojos.GetUserResponse;
import utils.Endpoints;
import utils.ExcelUtil;

public class TestUsers {

	@BeforeClass
	public void myBeforeClass() {
		System.out.println("MyBeforeClass");
	}

	// @Test(dataProvider = "GetTestData")
	public void testCreateUser(String name, String job) {

		RequestSpecBuilder builder = new RequestSpecBuilder();

		RequestSpecification req = builder.setContentType("Application/json").setBaseUri("https://reqres.in").build();

		ObjectMapper mapper = new ObjectMapper();

		CreateUserRequest createUser = null;

		CreateUserResponse createUserRes = null;

		File path = new File("./src/test/java/payloads/CreateUserPayload.json");
		try {
			createUser = mapper.readValue(path, CreateUserRequest.class);
		} catch (IOException e) {

			e.printStackTrace();
		}
	

		createUser.setName(name);
		createUser.setJob(job);

		String createUserEndpoint = Endpoints.valueOf("createUser").getEndPoint();

		File schema = new File("./src/test/java/jsonschemas/CreateUserSchema.json");

		Response response = given().
								spec(req).
								body(createUser).
								log().all().
							when().
								post(createUserEndpoint).
							then().
								log().all().
								statusCode(201).
								assertThat().
								body(JsonSchemaValidator.matchesJsonSchema(schema)).
								extract().
								response();

		String responseContentType = response.headers().get("Content-Type").toString();

		String responseName = response.jsonPath().get("user");

		try {
			createUserRes = mapper.readValue(response.asString(), CreateUserResponse.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Verify the Name : " + createUserRes.getName());
		System.out.println("user in response: " + responseName + " and response Content Type " + responseContentType);

	}

	@Test
	public void testUser() {

		RequestSpecBuilder builder = new RequestSpecBuilder();

		RequestSpecification req = builder.setContentType("Application/json").setBaseUri("https://reqres.in").build();

		String createUserEndpoint = Endpoints.valueOf("getUser").getEndPoint();

		File schema = new File("./src/test/java/jsonschemas/GetUserSchema.json");

		int size = 0;

		int pageNum = 1;

		int totalUsers = 0;

		Response response;
		
		List<Integer> names = new ArrayList<>();
		
		int index = 0;
		
		do {
			response = given().
						spec(req).
						queryParam("page", pageNum).
						log().all().
					when().
						get(createUserEndpoint).then().
						log().all().
						statusCode(200).assertThat().
						body(JsonSchemaValidator.matchesJsonSchema(schema)).
						time(lessThan(2000L)).
						body("data.size()", equalTo(6)).
						extract().
						response();

			size = size + response.jsonPath().getList("data").size();

			System.out.println("size of data : " + size);
			
			totalUsers = response.jsonPath().getInt("total");
			
			int currentSize = response.jsonPath().getList("data").size();
			int i = 0;
			while( i< currentSize ){
				names.add(response.jsonPath().getInt("data["+i+"].id"));
				i++;
			}

			pageNum++;

		} while (size < totalUsers);
		
		Set<Integer> ids = new HashSet<>(names);
		System.out.println("List Size : " + names.size() + ", Ids size : " + ids.size());
		
		String responseName = response.jsonPath().getString("data[0].email");
		String responseContentType = response.headers().get("Content-Type").toString();

		ObjectMapper mapper = new ObjectMapper();

		GetUserResponse getUserRes = null;

		try {
			getUserRes = mapper.readValue(response.asString(), GetUserResponse.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("Verify the data Size : " + getUserRes.getData().size());

		System.out.println("user in response: " + responseName + " and response Content Type " + responseContentType);

	}

	@DataProvider(name = "GetTestData")
	public String[][] getTestData() {

		ExcelUtil excelUtil = new ExcelUtil();

		return excelUtil.readExcelData("CreateUserTestData", "Sheet1");

	}

}
