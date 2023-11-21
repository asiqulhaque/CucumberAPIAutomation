package product.bestbuyapi.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import product.bestbuyapi.utils.BaseClass;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class StepDefs_Generic_Requests extends BaseClass {

	//Hooks
	@Before
	public void beforeHook(Scenario s) {
		this.scn = s;
	}

	@After
	public void afterHook(Scenario s){
		this.scn = s;
		if (_RESP==null) {
			scn.log("Response: No response received for the Service.");
		}else {
			scn.log("Response: " + _RESP.asString());
		}
	}

	@When("I submit categories post request with unique name and id")
	public void i_submit_categories_post_request_with_unique_name_and_id() {
		//Body
		JSONObject body_json = new JSONObject();
		unique_cat_name = GetRandomString(5); 
		unique_cat_id = GetRandomString(4); 
		body_json.put("id", unique_cat_id);
		body_json.put("name", unique_cat_name);
		scn.log("body as " + body_json.toJSONString());
		//Headers
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("Content-Type", "application/json");
		scn.log("Header as " + headers.toString());
		_RESP = _REQUEST_SPEC.headers(headers).body(body_json).when().post("/categories");
	}

	@Then("new cateogry is created in the system")
	public void new_cateogry_is_created_in_the_system() {
		_RESP.then().assertThat().body("id", equalTo(unique_cat_id));
		_RESP.then().assertThat().body("name", equalTo(unique_cat_name));
		scn.log("Id and name is correctly coming");
	}

	@When("I submit categories post request with existing name and unique id")
	public void i_submit_categories_post_request_with_existing_name_and_unique_id() {

	}

	@When("I submit post request with {string}")
	public void i_submit_post_request_with(String string) {

	}

	@Then("reponse should have bad request error message as {string}")
	public void reponse_should_have_bad_request_error_message_as(String string) {

	}


}
