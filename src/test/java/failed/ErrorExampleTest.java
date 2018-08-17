package failed;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tools.AssertTool;

import java.io.UnsupportedEncodingException;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2017/5/2.
 */
@Listeners({tools.report.GenerateReporter.class})
public class ErrorExampleTest {
    @BeforeClass
    public void init(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/getTest/1";
    }
    @Test(groups = { "p0", "failed" })
    public void errorExampleTest() throws UnsupportedEncodingException {
        Response response = given().when().log().all().get("");
        int resultCode = response.statusCode();
        String result = response.getBody().print();
        AssertTool.assertEqualsInt(resultCode, 200, "返回码错误！");
        AssertTool.assertEqualsStr(result, "get request is ok!", "返回内容错误！");
    }
}
