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
public class DeleteExapmle {
    @BeforeClass
    public void init(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/user";
    }
    @Test
    public void deleteExampleTest(){
        Response response = given().header("","").when().log().all().delete("/weye");
        int resultCode = response.statusCode();
        String result = response.getBody().print();
        AssertTool.assertEqualsInt(resultCode, 200, "返回码错误！");
        AssertTool.assertEqualsStr(result, "删除用户：weye信息成功！", "返回内容错误！");
    }
}
