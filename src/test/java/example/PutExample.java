package example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tools.AssertTool;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by weye on 2017/4/26.
 */
@Listeners({tools.report.GenerateReporter.class})
public class PutExample {
    @BeforeClass
    public void init(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/update/userInfo";
    }
    @Test
    public void putExampleTest(){
        Response response = given().header("", "").when().
                log().all().put("/weye/heihgt/1800mm");
        int resultCode = response.statusCode();
        String result = response.getBody().print();
        AssertTool.assertEqualsInt(resultCode, 200, "返回码错误！");
        AssertTool.assertEqualsStr(result, "更新成功！", "返回内容错误！");
    }
}
