package example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by quanh on 2017/6/10.
 */
public class GetRequest {
    @Test
    public void getTest(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/app/homepage";
        Response response = given().when().log().all().get();
        int reponseStatusCode = response.getStatusCode();
        String result = response.getBody().print();
        System.out.println("http get request 响应码是："+String.valueOf(reponseStatusCode));
    }
}
