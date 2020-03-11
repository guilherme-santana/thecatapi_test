package runTests;
import static io.restassured.RestAssured.proxy;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import bodyFactory.BodyFavourites;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import suport.ReadProperties;

public class FavouritesTest {

	public static String PROXY = "http://192.168.50.178:80";
	String endPoint = "/favourites/";
	int id;

	@Test
    void teste01postSucesso() throws IOException, Exception {
		proxy(PROXY);
        RestAssured.baseURI = new ReadProperties().read().get("url_server")+endPoint;
        RequestSpecification httpRequest = RestAssured.given()
        .header("Content-Type", "application/json")
        .header("x-api-key", new ReadProperties().read().get("x-api-key_valid"))
        .body(BodyFavourites.bodyFavourites().toJSONString());
        
        Response response = httpRequest.request(Method.POST);
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonPath js = response.jsonPath();
        id = js.get("id");
        Assert.assertEquals(js.get("message"), "SUCCESS");

    }
	
	@Test
	 void teste02getSucesso() throws IOException, Exception {
		proxy(PROXY);
       RestAssured.baseURI = new ReadProperties().read().get("url_server")+endPoint+id;
       RequestSpecification httpRequest = RestAssured.given()
       		.header("x-api-key", new ReadProperties().read().get("x-api-key_valid"));
       Response response = httpRequest.request(Method.GET);

       int statusCode = response.getStatusCode();
       Assert.assertEquals(statusCode, 200);
       JsonPath js = response.jsonPath();
       Assert.assertEquals(js.get("id"), id);
       Assert.assertEquals(js.get("user_id"), "09kvzz");
       Assert.assertEquals(js.get("image_id"), "img003");
       Assert.assertEquals(js.get("sub_id"), "user01");
       
	}
	
	@Test
	 void teste03getAutenticacaoInvalida() throws IOException, Exception {
		proxy(PROXY);
     RestAssured.baseURI = new ReadProperties().read().get("url_server")+endPoint+id;
     RequestSpecification httpRequest = RestAssured.given()
     		.header("x-api-key", new ReadProperties().read().get("x-api-key_invalid"));
     Response response = httpRequest.request(Method.GET);

     int statusCode = response.getStatusCode();
     Assert.assertEquals(statusCode, 401);
     JsonPath js = response.jsonPath();
     Assert.assertEquals(js.get("message"), "AUTHENTICATION_ERROR - no valid x-api-key in header");
     
	}
	
	@Test
    void teste04postCampoRequerido() throws IOException, Exception {
		proxy(PROXY);
        RestAssured.baseURI = new ReadProperties().read().get("url_server")+endPoint;
        RequestSpecification httpRequest = RestAssured.given()
        .header("Content-Type", "application/json")
        .header("x-api-key", new ReadProperties().read().get("x-api-key_valid"))
        .body(BodyFavourites.bodyFavouritesSemImagem().toJSONString());
        
        Response response = httpRequest.request(Method.POST);
        Assert.assertEquals(response.getStatusCode(), 400);
        JsonPath js = response.jsonPath();
        Assert.assertEquals(js.get("message"), "\"image_id\" is required");

    }
	
	@Test
	 void teste05deleteSucesso() throws IOException, Exception {
		proxy(PROXY);
      RestAssured.baseURI = new ReadProperties().read().get("url_server")+endPoint+id;
      RequestSpecification httpRequest = RestAssured.given()
      		.header("x-api-key", new ReadProperties().read().get("x-api-key_valid"));
      Response response = httpRequest.request(Method.DELETE);

      int statusCode = response.getStatusCode();
      Assert.assertEquals(statusCode, 200);
      JsonPath js = response.jsonPath();
      Assert.assertEquals(js.get("message"), "SUCCESS");
      
	}
	
	@Test
	 void teste06deleteSemID() throws IOException, Exception {
		proxy(PROXY);
     RestAssured.baseURI = new ReadProperties().read().get("url_server")+endPoint;
     RequestSpecification httpRequest = RestAssured.given()
     		.header("x-api-key", new ReadProperties().read().get("x-api-key_valid"));
     Response response = httpRequest.request(Method.DELETE);

     int statusCode = response.getStatusCode();
     Assert.assertEquals(statusCode, 405);
     
	}
	
	@Test
    void teste07postSemBody() throws IOException, Exception {
		proxy(PROXY);
        RestAssured.baseURI = new ReadProperties().read().get("url_server")+endPoint;
        RequestSpecification httpRequest = RestAssured.given()
        .header("Content-Type", "application/json")
        .header("x-api-key", new ReadProperties().read().get("x-api-key_valid"));
        
        Response response = httpRequest.request(Method.POST);
        Assert.assertEquals(response.getStatusCode(), 400);

    }
	
}
