package test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.Routepath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import payloads.Pet;

public class Pets {
	
	Faker faker;
	Pet payloads;
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		payloads = new Pet();
		
		payloads.setId(faker.idNumber().hashCode());
		payloads.setName(faker.name().name());
	}
	
	//#1 Test script to search for pet details using petId
	@Test(priority=1)
	
	public void testGetPet()
	{
		RestAssured.baseURI=(Routepath.get_endpoint);
        int petId;
        petId = 10;
        Response response = RestAssured.given()
                .when()
                .get("/pet/" + petId);
        int statusCode = response.getStatusCode();
        System.out.println("Response status code is " + statusCode);
        
        assertEquals("doggie", response.jsonPath().getString("name"));
		        
        ResponseBody body = response.getBody();
        body.prettyPrint();
        System.out.println("Response Body is " + body.asString());  		
	}

	//#2 Test script to Create New Pet
	@Test(priority=2)
	public void testPostPet()
	{
		RestAssured.baseURI=(Routepath.post_endpoint);
		
		String request = "{\n" +
                "  \"id\": 12123,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"GoldenRetriever\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type","application/json");

        Response response = httpRequest.body(request).post("");

        int statusCode = response.getStatusCode();
        System.out.println("Response status code is "+statusCode);
        
        ResponseBody body = response.getBody();
        body.prettyPrint();
        System.out.println("Response Body is " + body.asString());
        
	}
	
	//#3 Test script to update name for an existing pet
	@Test(priority=3)
	public void testPutPet()
	{
		RestAssured.baseURI=(Routepath.update_endpoint);
		
		String request = "{\n" +
                "  \"id\": 12123,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Maltese\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

		RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json").header("accept", "application/json");

        Response response = httpRequest.body(request).put("");

        int statusCode = response.getStatusCode();
        System.out.println("Response status code is "+statusCode);
        
        ResponseBody body = response.getBody();
        body.prettyPrint();
        System.out.println("Response Body is " + body.asString());
        
	}
	
	//#4 Test script to delete a pet
	@Test(priority=4)
	public void deletePetTest() 
	{

		RestAssured.baseURI = (Routepath.delete_endpoint);
		RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json").header("accept", "application/json");

        Response response = httpRequest.delete("/pet/12123");

        System.out.println(response);
        System.out.println("Response status code is " + response.getStatusCode());

    }
}
