package petStore;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import genericUtils.BaseClass;
import genericUtils.EndPointsLibrary;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoClasses.CreateStorePojo;
import pojoClasses.createPetPojo;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class PostStore extends BaseClass{

	@Test
	public void createStore() {
		int id=0;
		int petId=0;
		int quantity=0;
		String shipDate="2024-01-16T08:51:35.731Z";
		String status="placed";
		String complete="true";
		
		CreateStorePojo pobj = new CreateStorePojo(id,petId,quantity,shipDate,status,complete);
		given().spec(req).body(pobj)
		.when().post("/store/order")
		.then().spec(res).statusCode(200).contentType(ContentType.JSON)
		.log().all();
		
			
	}
	@Test
	public void postPet() {
	
		int id=0;
		String name="doggie";
		String [] photoUrls= {"sdc","hsvd"};
		String status="available";
		createPetPojo pobj = new createPetPojo(id, name, photoUrls, status);
		Response resp = given().spec(req).body(pobj).
		when().post("/pet");
		resp.then().spec(res).assertThat().statusCode(200).time(Matchers.lessThan(5000l),TimeUnit.MILLISECONDS).
		log().all();
	}
	
	@Test
	public void getPet() {
		Response resp = when().get("/pet/9223372036854756703");
		resp.then().spec(res).assertThat().statusCode(403)
		.time(Matchers.lessThan(5000l),TimeUnit.MILLISECONDS)
		.log().all();
	}
	
	@Test
	public void reqChain() {
		int id=0;
		String name="doggie";
		String [] photoUrls= {"sdc","hsvd"};
		String status="available";
		createPetPojo pobj = new createPetPojo(id, name, photoUrls, status);
		RequestSpecification reqst = given().spec(req).body(pobj);
		
		Response resp = reqst.when().post("/pet");
		
		Long pid = resp.jsonPath().get("id");
		System.out.println(pid);
		
		resp.then().log().all();
		reqst.when().get("/pet/"+pid);
		resp.then().assertThat().statusCode(200)
		.time(Matchers.lessThan(5000l),TimeUnit.MILLISECONDS)
		.log().all();
	}
	
	@Test
	public void putAndDeletePet() {
		int id=0;
		String name="doggie";
		String [] photoUrls= {"sdc","hsvd"};
		String status="not available";
		createPetPojo pobj = new createPetPojo(id, name, photoUrls, status);
		RequestSpecification rqt = given().spec(req).body(pobj);
		Response resp = rqt.when().put("/pet");
		resp.then().assertThat().contentType(ContentType.JSON)
		.statusCode(200).log().all();
		Long pid = resp.jsonPath().get("id");
		rqt.delete("/pet/"+pid);
		resp.then().statusCode(200).log().all();
	}
}
