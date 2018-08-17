package apis;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import java.io.File;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2017/11/18.
 */
public class MultiPartBody {
    @Test
    public void multiPartTest(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/upload";
        Response response = given().
                multiPart("uploaded_file",new File("D:\\upload_files\\bgt.png")).
                when().log().all().post();
        int reponseStatusCode = response.getStatusCode();
        response.getBody().print();
        System.out.println("http get request 响应码是：" + String.valueOf(reponseStatusCode));
    }
}
