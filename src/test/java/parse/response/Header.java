package parse.response;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2017/12/4.
 */
public class Header {
    @Test
    public void headerTest(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/app/login";
        String body = "{\n" +
                "    \tusername:\"admin\",\n" +
                "    \tpassword:\"admin\"\n" +
                "}";
        Response response = given().when().
                body(body).
                log().all().post();
        int resultCode = response.statusCode();
        System.out.println("响应状态码："+String.valueOf(resultCode));
        String responseHeader = response.getHeader("token");
        System.out.println("响应头域token："+responseHeader);
    }
}
