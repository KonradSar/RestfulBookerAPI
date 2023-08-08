package api.json;

import controller.CreateAndFindController;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTests {
    private static Logger logger = LogManager.getLogger(CreateBookingTests.class);
    @BeforeAll
    public static void setUpOperations() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
    @Test
    public void newBookingShouldBeAvailableById() {
        // Given
        Integer bookingId = CreateAndFindController.createBookingAndReturnIdValue();

        // When
        Response response = CreateAndFindController.findBookingById(bookingId);

        // Then
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void createBookingWithoutBodyFileAttached() {
        String value = "";
        // When
        Response response = given().contentType("application/json").accept("application/json")
                .body("{\n" +
                        "    \"firstname\" : \"Konrad\",\n" +
                        "    \"lastname\" : \"Saw\",\n" +
                        "    \"totalprice\" : 130,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2011-01-01\",\n" +
                        "        \"checkout\" : \"2023-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .when()
                .post("https://restful-booker.herokuapp.com/booking")

                .then()
                .body("booking.firstname", Matchers.equalTo("Konrad"))
                .body("booking.lastname", Matchers.equalTo("Saw"))
                .body("booking.totalprice", Matchers.equalTo(130))
                .body("booking.depositpaid", Matchers.equalTo(true))
                .body("booking.bookingdates.checkin", Matchers.equalTo("2011-01-01"))
                .body("booking.bookingdates.checkout", Matchers.equalTo("2023-01-01"))
                .body("booking.additionalneeds", Matchers.equalTo("Breakfast"))

                .extract().response();

        // Then
        Assertions.assertEquals(200, response.getStatusCode());

        if (response.getStatusCode() == 200) {
            logger.log(Level.INFO, "Main page is successfully displayed");
        }
    }
}
