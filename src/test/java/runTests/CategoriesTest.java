package runTests;
import static io.restassured.RestAssured.proxy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import suport.ReadProperties;

public class CategoriesTest {

	public static String PROXY = "http://192.168.50.178:80";
	String endPoint = "/categories/";

	@Test
	void teste01getSucesso() throws IOException, Exception {
		proxy(PROXY);
		RestAssured.baseURI = new ReadProperties().read().get("url_server")+endPoint;
		RequestSpecification httpRequest = RestAssured.given()
				.header("x-api-key", new ReadProperties().read().get("x-api-key_valid"));
		Response response = httpRequest.request(Method.GET);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		JsonPath js = response.jsonPath();
		List<Integer> listaId = js.get("id");
		List<Integer> listaIdEsperado = Arrays.asList(5, 15, 1, 14, 2, 4, 7);
		List<String> listaNameEsperado = Arrays.asList("boxes", "clothes", "hats", "sinks", "space", "sunglasses", "ties");
		List<Integer> listaName = js.get("name");
		
		Assert.assertEquals(listaId, listaIdEsperado);
		Assert.assertEquals(listaName, listaNameEsperado);

	}

	@Test
	void teste02getAutenticacaoInvalida() throws IOException, Exception {
		proxy(PROXY);
		RestAssured.baseURI = new ReadProperties().read().get("url_server")+endPoint;
		RequestSpecification httpRequest = RestAssured.given()
				.header("x-api-key", new ReadProperties().read().get("x-api-key_invalid"));
		Response response = httpRequest.request(Method.GET);
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 401);
		JsonPath js = response.jsonPath();
		Assert.assertEquals(js.get("message"), "AUTHENTICATION_ERROR - no valid x-api-key in header");

	}

}
