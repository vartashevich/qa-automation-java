import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
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
    String requestBody = "{\n"
            + "    \"id\": \"1\",\n"
            + "    \"countryName\": \"xx\"\n"
            + "}";

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
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .patch("/api/countries/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteCountryExisted() {
        when()
                .delete("/api/countries/9")
                .then()
                .statusCode(204);
    }

    @Test
    public void getCountriesAll() {
        when()
                .get("/api/countries")
                .then()
                .statusCode(200);
    }

    @Test
    public void postNewCountry() {
        String requestBody = "{\n"
                + "    \"countryName\": \"kk\"\n"
                + "}";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/countries")
                .then()
                .statusCode(201)
                .body("countryName", equalTo("kk"));
    }
}
