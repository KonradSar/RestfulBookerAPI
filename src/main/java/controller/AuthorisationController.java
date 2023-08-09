package controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import utils.FileNameConstraints;

import java.io.File;
import java.io.IOException;

public class AuthorisationController {

    private static Logger logger = LogManager.getLogger(CreateAndFindController.class);
    static String tokenAPIRequestBody;

    static {
        try {
            tokenAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstraints.TOKEN_API_REQUEST_BODY_FILE_PATH), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AuthorisationController() throws IOException {
    }

    public static String createAuthorisationToken() {
        Response tokenAPIResponse =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(tokenAPIRequestBody)
                        .when()
                        .post("/auth")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();

        Assertions.assertTrue(tokenAPIResponse.statusCode() == 200);
        ResponseBody body = tokenAPIResponse.getBody();
        String authorisationToken = body.asPrettyString().substring(16, 31);
        return authorisationToken;
    }
}
