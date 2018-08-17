package example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tools.AssertTool;

import java.io.UnsupportedEncodingException;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2017/4/26.
 */
@Listeners({tools.report.GenerateReporter.class})
public class DeleteExapmle {
    @BeforeClass
    public void init(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/user";
    }
    @Test(groups = { "p1", "delete_type" })
    public void deleteExampleTest() throws UnsupportedEncodingException {
        Response response = given().when().log().all().delete("/Chuckie");
        int resultCode = response.statusCode();
        Assert.assertEquals(resultCode,200,"123");
        String result = response.getBody().print();
        AssertTool.assertEqualsInt(resultCode, 200, "返回码错误！");
        AssertTool.assertEqualsStr(result, "删除用户：Chuckie信息成功！", "返回内容错误！");
    }
}
