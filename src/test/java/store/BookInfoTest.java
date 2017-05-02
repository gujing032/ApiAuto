package store;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by weye on 2017/5/1.
 */
@Listeners({tools.report.GenerateReporter.class})
public class BookInfoTest {
    @BeforeClass
    public void init(){
        RestAssured.baseURI = "http://localhost:4567";
        RestAssured.basePath = "/store";
    }
    @Test
    public void getBookInfoTest(){
        Response response = given().header("","").when().log().all().get("");
        int resultCode = response.statusCode();
        JsonPath jsonPath = response.jsonPath();
        String store_book_author = jsonPath.get("store.book[0].author");
        Assert.assertEquals(resultCode,200,"返回码错误！");
        Assert.assertEquals(store_book_author,"Nigel Rees","作者信息错误！");
    }
}
