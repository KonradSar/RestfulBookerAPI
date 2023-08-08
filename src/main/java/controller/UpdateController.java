package controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.FileNameConstraints;

import java.io.File;
import java.io.IOException;

public class UpdateController {
    private static Logger logger = LogManager.getLogger(CreateAndFindController.class);
    public static String putAPIRequestBody;

    static {
        try {
            putAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstraints.PUT_API_REQUEST_BODY_FILE_PATH), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public UpdateController() throws IOException {
    }

    public static void updateCurrentBookingUsingBodyFileAttached(Integer idValue, String tokenValue) {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(putAPIRequestBody)
                .cookie("token", tokenValue)
                .when()
                .put("/booking/{bookingId}", idValue)
                .then()
                .assertThat()
                .statusCode(200);
    }
}
