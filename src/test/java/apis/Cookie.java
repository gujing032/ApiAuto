package apis;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2017/11/18.
 */
public class Cookie {
    @Test
    public void cookieTest(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/cookie";
        Response response = given().
                cookie("JSESSIONID","1wbb6rvvc64g9139b89yn7toe0").
                when().log().all().get();
        int reponseStatusCode = response.getStatusCode();
        response.getBody().print();
        System.out.println("http get request 响应码是：" + String.valueOf(reponseStatusCode));
    }
}
