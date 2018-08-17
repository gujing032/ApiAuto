package apis;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2017/11/18.
            */
    public class Headers {
        @Test
        public void headersTest(){
            RestAssured.baseURI = "http://localhost:4567";
            RestAssured.basePath = "/header";
            Response response = given().
                    header("access_token","rSWamyAYwuHCo7IFAgd1oRpSP7nzL7BF5t7ItqpKViM").
                    when().log().all().get();
            int reponseStatusCode = response.getStatusCode();
            response.getBody().print();
            System.out.println("http get request 响应码是：" + String.valueOf(reponseStatusCode));
        }
}
