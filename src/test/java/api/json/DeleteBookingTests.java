package api.json;

import controller.AuthorisationController;
import controller.CreateAndFindController;
import controller.DeleteController;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DeleteBookingTests {
    private static Logger logger = LogManager.getLogger(DeleteBookingTests.class);
    @BeforeAll
    public static void setUpOperations() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
    @Test
    public void newBookingShouldBeDeletableById(){
        // Given
        Integer bookingId = CreateAndFindController.createBookingAndReturnIdValue();
        String authorisationToken = AuthorisationController.createAuthorisationToken();

        // When
        DeleteController.deleteBookingByIdValue(bookingId, authorisationToken);

        // Then
        RestAssured.given().contentType(ContentType.JSON).when()
                .get("/booking/{bookingid}", bookingId)
                .then()
                .assertThat().statusCode(404);
    }
}
