package parse.response;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Chuckie on 2017/12/4.
 */
public class body {
    @Test
    public void singleTest(){
        RestAssured.baseURI = "http://localhost:4567";
        Response response = given().when().log().all().get("/json/single");
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.get("name");
        System.out.println("json name value:"+name);
    }
    @Test
     public void multiTest(){
        RestAssured.baseURI = "http://localhost:4567";
        Response response = given().when().log().all().get("/json/multi");
        JsonPath jsonPath = response.jsonPath();
        int lottoId = jsonPath.get("lotto.lottoId");
        System.out.println("json lottoId value:"+lottoId);
    }
    @Test
    public void multiArrayTest(){
        RestAssured.baseURI = "http://localhost:4567";
        Response response = given().when().log().all().get("/json/multi");
        JsonPath jsonPath = response.jsonPath();
        int winnerId = jsonPath.get("lotto.winners[0].winnerId");
        System.out.println("json winnerId value:"+winnerId);
    }
}
