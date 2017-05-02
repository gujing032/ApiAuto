package tools;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by weye on 2017/4/30.
 */
public class ApiTool {
    static LoggerControler log = LoggerControler.getLogger(ApiTool.class);

    /**
     *
     * @return 返回认证令牌token
     */
    public static String getAppToken() {
        String loginPath = "/login";

        //    登陆用户名，密码，租户ID


        String bodystring = "{\n" +
                "    \"username\": [\n" +
                "        \"admin\"\n" +
                "    ],\n" +
                "    \"password\": [\n" +
                "        \"admin\"\n" +
                "    ]\n" +
                "}";
        Response response = given().
                contentType("application/json;charset=UTF-8").
                body(bodystring).
                when().post(loginPath);
        JsonPath loginJson = response.jsonPath();
        String token = loginJson.getString("token");
        return token;
    }
    /**
     * 封装post 请求：每次请求前先获取新token
     *
     * @param json    请求json
     * @param apiPath api地址
     * @return 返回response
     */
    public static Response post(String json, String apiPath) {
        String ticket = ApiTool.getAppToken();
//        开始发起post 请求
        Response response = given().
                contentType("application/json;charset=UTF-8").
                headers("token", ticket).
                body(json).
                when().log().all().post(apiPath);

        try {
            log.info(response.jsonPath().get());
        } catch (Exception e) {
            log.info("请求返回空！");
        }
        return response;
    }
    /**
     * get 请求：每次请求前先获取新token
     * @param apiPath api地址
     * @return 返回response
     */
    public static Response get( String apiPath) {
        String toekn = ApiTool.getAppToken();
//        开始发起GET 请求
        Response response = given().
                contentType("application/json;charset=UTF-8").
                headers("token", toekn).
                when().log().all().get(apiPath);

        try {
            log.info(response.jsonPath().get());
        } catch (Exception e) {
            log.info("请求返回空！");
        }
//        log.info(response.headers());
        return response;
    }
}
