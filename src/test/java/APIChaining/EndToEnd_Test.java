package APIChaining;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEnd_Test {

	Response response;
	String BaseURI = "http://localhost:3000/employees";

	@Test
	public void test1() {
		response = GetMethodAll();
		Assert.assertEquals(response.getStatusCode(), 200);

		response = PostMethod("Lucy", "10000");
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath jpath = response.jsonPath();
		int Emp_ID = jpath.get("id");
		System.out.println("ID is  " + Emp_ID);

		response = PutMethod(Emp_ID, "Samuel", "15000");
		Assert.assertEquals(response.getStatusCode(), 200);
		jpath = response.jsonPath();
		Assert.assertEquals(jpath.get("name"), "Samuel");

		response = DeleteMethod(Emp_ID);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		response = GetMethod(Emp_ID);
		Assert.assertEquals(response.getStatusCode(), 404);
	}

	public Response GetMethodAll() {
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given(); // request is the request object
		Response response = request.get();
		return response;
	}

	public Response PostMethod(String Name, String Salary) {
		RestAssured.baseURI = BaseURI;

		JSONObject jobj = new JSONObject();
		jobj.put("name", Name);
		jobj.put("salary", Salary);

		RequestSpecification request = RestAssured.given();

		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jobj.toString())
				.post("/create");
		return response;
	}

	public Response PutMethod(int Emp_ID, String Name, String Salary) {
		RestAssured.baseURI = BaseURI;

		JSONObject jobj = new JSONObject();
		jobj.put("name", Name);
		jobj.put("salary", Salary);
		RequestSpecification request = RestAssured.given();

		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jobj.toString())
				.put("/" + Emp_ID);
		return response;
	}

	public Response DeleteMethod(int Emp_ID) {
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.delete("/" + Emp_ID);
		return response;
	}
	
	public Response GetMethod(int Emp_ID) {
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/" + Emp_ID);
		return response;
	}

}
