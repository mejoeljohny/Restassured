package RestAPI;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest_withParams {
	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given(); // request is the request object
		Response response = request.param("id", "1").get(); // response object stores the response for id=1

		System.out.println("************");

		int responsestatus = response.getStatusCode();
		System.out.println("The Status code is " + responsestatus);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);

		Assert.assertEquals(responsestatus, 200);

		Assert.assertTrue(responsebody.contains("Rohit"));

		JsonPath jpath = response.jsonPath();
		List<String> names = jpath.get("name");
//		for(String nam : names) {
//			System.out.println(nam);// to print all the names in the response
//		}
		System.out.println(names.get(0));
		Assert.assertEquals(names.get(0), "Rohit");

		String Header = response.getHeader("Content-Type");
		System.out.println(Header);
	}
}
