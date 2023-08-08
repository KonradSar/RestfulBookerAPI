package controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import utils.FileNameConstraints;

import java.io.File;
import java.io.IOException;

public class CreateAndFindController {
    private static Logger logger = LogManager.getLogger(CreateAndFindController.class);
    static String postAPIRequestBody;

    static {
        try {
            postAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstraints.POST_API_REQUEST_BODY_FILE_PATH), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer createBookingAndReturnIdValue() {
        // When
        Response response =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(postAPIRequestBody)
                        .when()
                        .post("/booking")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().response();

        // Then
        Assertions.assertEquals(200, response.getStatusCode());
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("bookingid");
    }

    public static Response findBookingById(Integer idValue) {
        logger.log(Level.INFO, "Find booking by id " + idValue);
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/booking/{bookingid}", idValue);
    }
}
