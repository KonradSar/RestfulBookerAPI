package test.data.model;


public class Booking {
    private String firstName;
    private String lastName;
    private int totalPrice;
    private int depositPaid;
    private String checkIn;
    private String checkOut;
    private String additionalNeeds;

    public Booking(String firstName, String lastName, int totalPrice, int depositPaid, String checkIn, String checkOut, String additionalNeeds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.additionalNeeds = additionalNeeds;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getDepositPaid() {
        return depositPaid;
    }

    public void setDepositPaid(int depositPaid) {
        this.depositPaid = depositPaid;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getAdditionalNeeds() {
        return additionalNeeds;
    }

    public void setAdditionalNeeds(String additionalNeeds) {
        this.additionalNeeds = additionalNeeds;
    }
}
