package mock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Rule;
import org.junit.Test;
import org.testng.Assert;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2018/8/17.
 */
public class MockTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(60000);
    @Test
    public void testMockForCode(){
        wireMockRule.stubFor(
                post(urlEqualTo("/mock/test")).withHeader("type",containing("mock"))
                        .willReturn(aResponse().withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("mock test success!")));
        RestAssured.baseURI = "http://localhost:60000";
        RestAssured.basePath = "/mock/test";
        Response response = given().
                header("type","mock323232323").
                when().log().all().post();
        int reponseStatusCode = response.getStatusCode();
        response.getBody().print();
        Assert.assertEquals(reponseStatusCode,200,"返回状态码错误！");
        System.out.println("http get request 响应码是：" + String.valueOf(reponseStatusCode));
    }
    @Test
    public void testMockForStandalone(){
        RestAssured.baseURI = "http://localhost:8089";
        RestAssured.basePath = "/common/test11";
        Response response = given().
                when().log().all().get();
        int reponseStatusCode = response.getStatusCode();
        response.getBody().print();
        Assert.assertEquals(reponseStatusCode,200,"返回状态码错误！");
    }
}
