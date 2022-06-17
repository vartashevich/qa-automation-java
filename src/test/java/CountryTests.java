import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created on 10.06.2022
 *
 * @author Viktor Artashevich
 */
public class CountryTests {
    @BeforeAll
    public static void setUp() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
    }

    @BeforeAll
    public static void setUpErrorLogging() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void getExistedCountry() {
        when()
                .get("/api/countries/2")
                .then()
                .statusCode(200)
                .body("countryName", equalTo("vi"));
    }

    @Test
    public void putAnotherCountryInExisted() {
        String requestBody = "{\n"
                + "    \"id\": \"1\",\n"
                + "    \"countryName\": \"xx\"\n"
                + "}";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put("/api/countries/1")
                .then()
                .statusCode(200)
                .body("countryName", equalTo("xx"));
    }

    @Test
    public void patchCountryExisted() {
        String requestBody = "{\n"
                + "    \"id\": \"1\",\n"
                + "    \"countryName\": \"xx\"\n"
                + "}";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .patch("/api/countries/1")
                .then()
                .statusCode(200)
                .body("countryName", equalTo("xx"));
    }

    @Test
    public void deleteCountryExisted() {
        String requestBody = "{\n"
                + "    \"countryName\": \"ld\"\n"
                + "}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/countries")
                .then()
                .statusCode(201)
                .extract().response();
        when()
                .delete("/api/countries/" + response.jsonPath().getString("id"))
                .then().statusCode(204);
        when()
                .get("/api/countries/" + response.jsonPath().getString("id"))
                .then()
                .statusCode(404);
    }

    @Test
    public void getCountriesAll() {
        when()
                .get("/api/countries")
                .then()
                .statusCode(200)
                .body("size()", Matchers.not(0));
    }

    @Test
    public void postNewCountry() {
        String requestBody = "{\n"
                + "    \"countryName\": \"kk\"\n"
                + "}";
        String id = null;
        try {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post("/api/countries")
                    .then()
                    .statusCode(201)
                    .body("countryName", equalTo("kk"))
                    .extract().response();
            id = response.jsonPath().getString("id");
        } catch (Throwable e) {
            throw e;
        } finally {
            if (id != null) {
                when()
                        .delete("/api/countries/" + id)
                        .then().statusCode(204);
            }
        }
    }
}

