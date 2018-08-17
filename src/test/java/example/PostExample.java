package example;

import com.jayway.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tools.AssertTool;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2017/4/22.
 */
@Listeners({tools.report.GenerateReporter.class})
public class PostExample {
    @Test
    public void postExampleTest() {
        String reqBody = "{\n" +
                "    \"username\": \"admin\",\n" +
                "    \"password\": \"admin\"\n" +
                "}";
        Response response = given().when().
                body(reqBody).
                log().all().post("http://localhost:4567/web/login");
        int resultCode = response.statusCode();
        System.out.println(response.getCookie("JSESSIONID"));
        String result = response.getBody().print();
        AssertTool.assertEqualsInt(resultCode, 200, "返回码错误！");
        AssertTool.assertEqualsStr(result, "验证通过，欢迎您：Chuckie!", "返回内容错误！");
    }
}
