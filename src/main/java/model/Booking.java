package model;

import lombok.Builder;

@Builder
public class Booking {
        private String firstName;
        private String lastName;
        private int totalPrice;
        private int depositPaid;
        private String checkIn;
        private String checkOut;
        private String additionalNeeds;

    public Booking.BookingBuilder createBooking() {
        return new BookingBuilder()
                .firstName("Rakesh").lastName("Narang").totalPrice(27).
                depositPaid(150).checkIn("2018-01-01")
                .checkOut("2018-01-03").additionalNeeds("breakfast").build().createBooking();
    }
}
