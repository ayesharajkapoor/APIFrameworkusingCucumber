package stepDefinitions;

import static io.restassured.RestAssured.given;
import pojo.*;
import resources.APIResources;
import resources.TesstDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class placeAPIstepDefintion extends Utils {
	RequestSpecification req;
	ResponseSpecification res;
	Response response1;
	static String placeid;
	TesstDataBuild data = new TesstDataBuild();

	@Given("Addplace API PAyload with {string} {string} {string}")
	public void addplace_api_p_ayload_with(String name, String address, String language) throws IOException {

		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		req = given().spec(requestSpecification()).body(data.addPlacePayload(name, address, language));

	}

	@When("User calls the {string} using http {string} method")
	public void user_calls_the_using_http_method(String Resource, String httpmethod) {

		APIResources resourceAPI = APIResources.valueOf(Resource);

		System.out.println(APIResources.valueOf(Resource));

		System.out.println(resourceAPI.getREsource());

		if (httpmethod.equalsIgnoreCase("POST")) {
			response1 = req.when().post(resourceAPI.getREsource());
		}

		else if (httpmethod.equalsIgnoreCase("GET")) {
			System.out.println(req);
			System.out.println(resourceAPI.getREsource());
			response1 = req.when().get(resourceAPI.getREsource());

		}

	}

	@Then("User gets status code as {int} if success add the place in aPI")
	public void user_gets_status_code_as_if_success_add_the_place_in_a_pi(Integer int1) {
		System.out.println(response1);

		assertEquals(response1.getStatusCode(), 200);

	}

	@Then("{string} in  reponse message could be as {string}")
	public void in_reponse_message_could_be_as(String string, String string2) {
		String responseString = response1.asString();
		JsonPath js = new JsonPath(responseString);

		assertEquals(getJsonPAth(response1, "status"), "OK");
		/*
		 * assertEquals(getJsonPAth(response1, "scope"), "APP");
		 */
	}

	@Then("Verify the place id from created maps  to verify {string} by using {string}")
	public void verify_the_place_id_from_created_maps_and_verify_by_using(String name, String resource)
			throws IOException {

		 placeid = getJsonPAth(response1, "place_id");

		req = given().spec(requestSpecification()).queryParam("place_id", placeid);
		
		user_calls_the_using_http_method(resource, "GET");
		
		assertEquals(getJsonPAth(response1, "name"), name);

	}
	@Given("Delete Place API Payload")
	public void delete_place_api_payload() throws IOException {
		req=	given().spec(requestSpecification()).body(data.deletePlaceAPI(placeid));
	   
	}


}
