import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PostmanEchoTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGetRequest() {
        Response response = given()
                .when()
                .get("/get?foo1=bar1&foo2=bar2")
                .then()
                .extract()
                .response();


        assertThat(response.statusCode()).isEqualTo(200);


        assertThat(response.jsonPath().getString("args.foo1")).isEqualTo("bar1");
        assertThat(response.jsonPath().getString("args.foo2")).isEqualTo("bar2");
        assertThat(response.jsonPath().getString("url")).isEqualTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2");
    }

    @Test
    public void testPostRequest() {
        String requestBody = "{\"key\": \"value\"}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .extract()
                .response();


        assertThat(response.statusCode()).isEqualTo(200);


        assertThat(response.jsonPath().getString("data")).isEqualTo(requestBody);
        assertThat(response.jsonPath().getString("json.key")).isEqualTo("value");
        assertThat(response.jsonPath().getString("url")).isEqualTo("https://postman-echo.com/post");
    }

    @Test
    public void testPutRequest() {
        String requestBody = "{\"key\": \"updatedValue\"}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .extract()
                .response();


        assertThat(response.statusCode()).isEqualTo(200);


        assertThat(response.jsonPath().getString("data")).isEqualTo(requestBody);
        assertThat(response.jsonPath().getString("json.key")).isEqualTo("updatedValue");
        assertThat(response.jsonPath().getString("url")).isEqualTo("https://postman-echo.com/put");
    }

    @Test
    public void testPatchRequest() {
        String requestBody = "{\"key\": \"patchedValue\"}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .extract()
                .response();


        assertThat(response.statusCode()).isEqualTo(200);


        assertThat(response.jsonPath().getString("data")).isEqualTo(requestBody);
        assertThat(response.jsonPath().getString("json.key")).isEqualTo("patchedValue");
        assertThat(response.jsonPath().getString("url")).isEqualTo("https://postman-echo.com/patch");
    }

    @Test
    public void testDeleteRequest() {
        Response response = given()
                .when()
                .delete("/delete")
                .then()
                .extract()
                .response();


        assertThat(response.statusCode()).isEqualTo(200);


        assertThat(response.jsonPath().getString("url")).isEqualTo("https://postman-echo.com/delete");
    }
}