package login;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.annotations.*;
import tools.AssertTool;
import tools.ExcelUtils;

import java.io.UnsupportedEncodingException;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2017/4/30.
 */
@Listeners({tools.report.GenerateReporter.class})
public class LoginTest {
    @BeforeTest
    public void init(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/login/test";
    }
    @Parameters({"UserName","PassWord"})
    @Test(groups = { "p0"})
    public void passedloginTest(String username,String password) throws UnsupportedEncodingException {
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/login/test";
        String body = "{\n" +
                "    \"username\":" +username+",\n" +
                "    \"password\": "+password+"\n" +
                "}";
        Response response = given().when().
                body(body).
                log().all().post("");
//        log.info(response);
        int resultCode = response.statusCode();
        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.getString("token");
        AssertTool.assertEqualsInt(resultCode, 200, "返回码错误！");
        AssertTool.assertEqualsStr(token, "rSWamyAYwuHCo7IFAgd1oRpSP7nzL7BF5t7ItqpKViM", "返回内容错误！");
    }
    @DataProvider(name = "username")
    public Object[][] createdata() {
        return new Object[][] {
                {"user1", "admin"},
                {"user2", "admin"},
                {"use3", "admin"}
                };
        }
    @Test(groups = { "p0"},dataProvider = "username")
    public void errorUserNameLoginTest(String username , String password) throws UnsupportedEncodingException {
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/login/test";
        String body = "{\n" +
                "    \"username\":"+username+ ",\n" +
                "    \"password\": "+password+"\n" +
                "}";
//        HttpDebugLogMaker.debugGetter("请求路径:" + RestAssured.baseURI + RestAssured.basePath + "\n");
        Response response = given().when().
                body(body).
                log().all().post("");
        int resultCode = response.statusCode();
        AssertTool.assertEqualsInt(resultCode, 401, "返回码错误！");
    }
    @DataProvider
    public Object[][] requestBody() throws Exception{
        String filePath = "E:\\project\\HttpApiTest\\src\\main\\resources\\testData.xlsx";
        Object[][] testObjArray= ExcelUtils.getTableArray(filePath,"Sheet1",6);
        return (testObjArray);
    }
    @Test(groups = { "p0" },dataProvider = "requestBody",description="描述内容")
    public void errorPwdLoginTest(String c1,String c2,String c3,String c4,String c5,String c6) throws UnsupportedEncodingException {
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/login/test";
//        System.out.println("TestCaseName:"+c0);
        RestAssured.basePath = c1;
        String body = c2;
        Response response = given().when().
                header(c3,c4).
                cookie(c5,c6).
                body(body).
                log().all().post("");
//        log.debug(response);
        int resultCode = response.statusCode();
        AssertTool.assertEqualsInt(resultCode, 401, "返回码错误！");
    }

}
