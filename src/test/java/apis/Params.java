package apis;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2017/11/18.
 */
public class Params {
    @Test
    public void paramsRestTest(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/users/admin";
        Response response = given().when().log().all().get();
        int reponseStatusCode = response.getStatusCode();
        response.getBody().print();
        System.out.println("http get request 响应码是："+String.valueOf(reponseStatusCode));
    }
    @Test
    public void paramsKVTest(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/users";
        Response response = given().
                param("name","admin").
                when().log().all().get();
        int reponseStatusCode = response.getStatusCode();
        System.out.println("http get request 响应码是："+String.valueOf(reponseStatusCode));
    }
}
