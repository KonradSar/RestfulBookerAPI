package controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteController {
    public static void deleteBookingByIdValue(Integer idValue, String tokenValue) {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .cookie("token", tokenValue)
                .when()
                .delete("/booking/{bookingid}", idValue)
                .then()
                .assertThat()
                .statusCode(201);
    }
}
