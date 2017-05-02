package example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tools.AssertTool;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by weye on 2017/4/22.
 */
@Listeners({tools.report.GenerateReporter.class})
public class PostExample {
    @BeforeClass
    public void init(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/login";
    }
    @Test
    public void postExampleTest(){
        Response response = given().header("", "").when().
                param("userName", "weye").
                param("pwd","123456").
                log().all().post("");
        int resultCode = response.statusCode();
        String result = response.getBody().print();
        AssertTool.assertEqualsInt(resultCode, 200, "返回码错误！");
        AssertTool.assertEqualsStr(result, "验证通过，欢迎您：weye!", "返回内容错误！");
    }
}
