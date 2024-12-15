package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {
		if(req==null) {

		PrintStream log = new PrintStream(new FileOutputStream("logFile.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))

				.addFilter(RequestLoggingFilter.logRequestTo(log))

				.addFilter(ResponseLoggingFilter.logResponseTo(log))

				.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
		
		return req;
		}
		return req;

	}

	public static String getGlobalValue(String key) throws IOException {

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Rafeek\\git\\APIFrameworkusingCucumber\\APIFrameworkusingCucumber\\src\\test\\java\\resources\\gloabal.properties");

		prop.load(fis);

		return prop.getProperty(key);

	}
	
	public static String getJsonPAth(Response response,String key) {
	
		
		JsonPath js= new JsonPath(response.asString());
		return js.getString(key);
	}

}
