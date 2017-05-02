package login;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tools.AssertTool;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by weye on 2017/4/30.
 */
@Listeners({tools.report.GenerateReporter.class})
public class LoginTest {
    @BeforeClass
    public void init(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/login/test";
    }
    @Test
    public void loginTest(){
        String body = "{\n" +
                "    \"username\": \"admin\",\n" +
                "    \"password\": \"admin\"\n" +
                "}";
        Response response = given().header("", "").when().
                body(body).
                log().all().post("");
        int resultCode = response.statusCode();
        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.getString("token");
        AssertTool.assertEqualsInt(resultCode, 200, "返回码错误！");
        AssertTool.assertEqualsStr(token, "rSWamyAYwuHCo7IFAgd1oRpSP7nzL7BF5t7ItqpKViM", "返回内容错误！");
    }
}
