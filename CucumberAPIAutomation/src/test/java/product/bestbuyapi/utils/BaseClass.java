package product.bestbuyapi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;


public class BaseClass {

	protected static RequestSpecification _REQUEST_SPEC;
	protected static Response _RESP;
	protected static Scenario scn;
	
	protected static String unique_cat_name;
	protected static String unique_cat_id;

	//mine
	private Properties raProperties;

	//*************************UTILS**************************
	//To get random Key
	public String GetRandomString(int n) {
		// lower limit for LowerCase Letters 
		int lowerLimit = 97;
		// lower limit for LowerCase Letters 
		int upperLimit = 122;
		Random random = new Random();
		// Create a StringBuffer to store the result 
		StringBuffer r = new StringBuffer(n);
		for (int i = 0; i < n; i++) {
			// take a random value between 97 and 122 
			int nextRandomChar = lowerLimit 
					+ (int)(random.nextFloat() 
							* (upperLimit - lowerLimit + 1));
			// append a character at the end of bs 
			r.append((char)nextRandomChar); 
		}
		// return the resultant string 
		return r.toString(); 
	}

	public static String loadPayloadFile(String filePath, String jsPath, String jsNewValue) throws IOException {
		FileInputStream input = new FileInputStream(filePath);
		String jsonString = IOUtils.toString(input, "UTF-8");
		Configuration configuration = Configuration.builder()
				.jsonProvider(new JacksonJsonNodeJsonProvider())
				.mappingProvider(new JacksonMappingProvider()).build();
		DocumentContext json = JsonPath.using(configuration).parse(jsonString);
		String updateJson = json.set(jsPath, jsNewValue).jsonString();
		return updateJson;
		}

	public String loadPayloadFile(String jsPath, String newValue ) {
		String jsonString = "{\n" +
				"    \"price\": 1000,\n" +
				"    \"name\": \"fvjyj\",\n" +
				"    \"upc\": \"12345676\",\n" +
				"    \"description\": \"Good Product\",\n" +
				"    \"model\": \"fdsff4\",\n" +
				"    \"type\": \"Hard Good\"\n" +
				"}";
		Configuration configuration = Configuration.builder()
				.jsonProvider(new JacksonJsonNodeJsonProvider())
				.mappingProvider(new JacksonMappingProvider()).build();
		DocumentContext json = JsonPath.using(configuration).parse(jsonString);
		String updateJson = json.set(jsPath, newValue).jsonString();
		return updateJson;
	}


	
}
