package apis;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2017/11/18.
 */
public class JsonBody {
    @Test
    public void josnBodyTest(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/body";
        String body = "{\n" +
                "\tusername:admin,\n" +
                "\tpassword:admin\n" +
                "}";
        Response response = given().
                body(body).
                when().log().all().post();
        int reponseStatusCode = response.getStatusCode();
        response.getBody().print();
        System.out.println("http get request 响应码是：" + String.valueOf(reponseStatusCode));
    }
}
