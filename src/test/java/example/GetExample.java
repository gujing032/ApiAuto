package example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tools.AssertTool;

import java.io.UnsupportedEncodingException;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by chengye on 2017/4/22.
 */
@Listeners({tools.report.GenerateReporter.class})
public class GetExample {
    @BeforeClass
    public void init(){
        RestAssured.baseURI = "http://music.163.com";
        RestAssured.basePath = "/weapi/search/suggest/multimatch?csrf_token=";
    }
    @Test(groups = { "p0" })
    public void getExampleTest() throws UnsupportedEncodingException {
        Response response = given().
                header("Content-Type","application/x-www-form-urlencoded").
                param("params","ibTuxhOAhhfcxdfLk7uZO+DWFr9FlKBVVbPg6yjqxMBxo5gBd/AmwMkD4EFIetp0FNmNWogBi9p8j1rpLInL5sOufS6+s53zVOofAxqhN+g=").
                param("encSecKey","3f648a09d4dc46b0544b5ce325b206c7cb7ecf38c13ce7bd9e8ad8eda8d9a109d14254478028f28819bd5109a6e8c14a459c114ed9d81250431c3709e5003f47f01bd1e4dadeba2116739f953de10235cf8e76df5b2a504e91b09b1420d86e25d1ad054acd6e9fb5be0c8e65eefa36888d41bf1d295c4df7c1ad9d095d57ad82").
                when().log().all().
                post("");
        int resultCode = response.statusCode();
        String result = response.getBody().print();
        AssertTool.assertEqualsInt(resultCode, 200, "返回码错误！");
        AssertTool.assertEqualsStr(result, "get request is ok!", "返回内容错误！");
    }
}
