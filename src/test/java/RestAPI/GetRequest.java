package RestAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {

	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given(); //request is the request object
		Response response = request.get();//response is the response object
		
		System.out.println("************");
		
		int responsestatus = response.getStatusCode();
		System.out.println("The Status code is "+responsestatus);
		String Responsebody = response.getBody().asString();
		System.out.println(Responsebody);
		
		Assert.assertEquals(responsestatus, 200);
	}

}
