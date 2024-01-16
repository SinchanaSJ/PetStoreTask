package genericUtils;

import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseClass {

	public RequestSpecification req;
	public ResponseSpecification res;
	
	@BeforeSuite(alwaysRun = true)
	public void bsConfig() {
	 req = new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").setContentType(ContentType.JSON).build();
	 res = new ResponseSpecBuilder().build();
	}
}
