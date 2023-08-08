package api.json;

import controller.AuthorisationController;
import controller.CreateAndFindController;
import controller.UpdateController;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UpdateBookingTests {
    private static Logger logger = LogManager.getLogger(UpdateBookingTests.class);
    @BeforeAll
    public static void setUpOperations() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
    @Test
    public void newBookingShouldBeEditableById(){
        // Given
        Integer bookingId = CreateAndFindController.createBookingAndReturnIdValue();
        String authorisationToken = AuthorisationController.createAuthorisationToken();

        // When
        UpdateController.updateCurrentBookingUsingBodyFileAttached(bookingId, authorisationToken);

        // Then
        RestAssured.given().then().body("firstname", Matchers.equalTo("KondzioDzik")).
                body("lastname", Matchers.equalTo("PoprawionyPutem"));
    }
}
