package endpoints;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.Pet;


public class PetEndpoints {

	
	
	//GET Endpoint to retrieve Pet details
	public static Response retrievePet()
	{
		RequestSpecification httprequest = RestAssured.given();
		Response response = httprequest.request(Method.GET, "v2/");
	            
	        when()
	                .get(Routepath.get_endpoint);

	        return (Response) response;
		
	}
	
	//POST Endpoint to Create New Pet
		public static Response createPet(Pet payloads)
		{
			Response response=(Response) given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payloads);
			
			when()
				.post(Routepath.post_endpoint);
			
			return response;
		}
		
	//PUT Endpoint to Update Pet Name
	public static Response updatePet(Pet payloads)
	{
		Response response=(Response)given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payloads);
		
		when()
			.put(Routepath.update_endpoint);
		
		return response;
	}
	
	//DELETE Endpoint to Delete a Pet
	public static Response deletePet(String petId)
	{
		RequestSpecification httprequest = RestAssured.given();
		Response response = httprequest.request(Method.DELETE, "v2/");
	    
		when()
			.delete(Routepath.delete_endpoint);
		
		return response;
	}
}
