package apis;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2017/11/18.
 */
public class FormDataBody {
    @Test
    public void formDataTest(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/form-data";
        Response response = given().
                formParam("fdk1", "fdv1").
                when().log().all().post();
        int reponseStatusCode = response.getStatusCode();
        response.getBody().print();
        System.out.println("http get request 响应码是：" + String.valueOf(reponseStatusCode));
    }
}
