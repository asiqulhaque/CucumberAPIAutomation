package product.bestbuyapi.stepdefs;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import product.bestbuyapi.utils.BaseClass;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class StepDefs_Products extends BaseClass {

	Map<String,String> headers = new HashMap<String,String>();
	JSONObject body_json = new JSONObject();
	String product_name;


	private static String setProperty(String payload, String jsonProperty, String value){
		Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
				.mappingProvider(new JacksonMappingProvider()).build();
		DocumentContext json = JsonPath.using(configuration).parse(payload);
		return json.set(jsonProperty, value).jsonString();
	}

	@When("I setup payload for product post request with unique name")
	public  void  i_submit_product_post_request_with_unique_name() throws ParseException, IOException {
		//Body
		product_name = GetRandomString(5);
	/*
		body_json.put("name", product_name);
		body_json.put("type", "Hard Good");
		body_json.put("upc", "12345676");
		body_json.put("price", 1000);
		body_json.put("description", "Good Product");
		body_json.put("model", "fdsff4");
		scn.write("Product body as " + body_json.toJSONString()); */

		String fPath = "src/test/testdata/payloadFile.txt";
		String inputStream = loadPayloadFile(fPath, "name", product_name);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(inputStream);
		body_json.putAll(json);
		scn.log("setting Product body as " + json);
	}

	@When("I set up Headers for the service")
	public void setUpApiHeaders(){
		//Headers
		headers.put("Content-Type", "application/json");
		scn.log("Header as " + headers.toString());
	}

	@When("I submit postRequest to Service Endpoint")
	public void postReqServiceEndpoint(){
		_RESP = _REQUEST_SPEC.headers(headers)
				.body(body_json)
				.when()
				.post("/products");
		scn.log("Response: " + _RESP.asString());
	}

	@Then("new product is created in the system")
	public void new_product_is_created_in_the_system() {
		_REQUEST_SPEC.get("/products?name=" + product_name)
				.then()
				.assertThat()
				.statusCode(200);
		_REQUEST_SPEC.get("/products?name=" + product_name)
				.then().assertThat().body("data[0].name", equalTo(product_name));
	}

	@Then("new product is correctly saved in database")
	public void new_product_is_correctly_saved_in_database() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("I submit get request with product {string} returns {int} status code")
	public void i_submit_get_request_with_product_returns_status_code(String product, Integer int1) {
		 _RESP = _REQUEST_SPEC.headers(headers)
				.get("/products?name=" + product);
		_RESP.then().assertThat().statusCode(int1);
	}
	@Then("I verify product details in response")
	public void i_verify_product_details_in_response() {
		System.out.println(_RESP.jsonPath().getString("data[0].name"));
	   Assert.assertEquals("wtcju", _RESP.jsonPath().getString("data[0].name"));

	}


}
