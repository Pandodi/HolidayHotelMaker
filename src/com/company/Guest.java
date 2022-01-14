package com.company;

public class Guest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String dateOfBirth;
    private int guestID;
    private int reservationID;

    public Guest(String firstName, String lastName, String phoneNumber, String emailAdress, String dateOfBirth, int guestID, int reservationID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAdress;
        this.dateOfBirth = dateOfBirth;
        this.guestID = guestID;
        this.reservationID = reservationID;
    }

    public Guest(String firstName, String lastName, String phoneNumber, String emailAddress, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return guestID +
                ". First Name: " + firstName +
                ", Last Name: " + lastName +
                ", Phone Number: " + phoneNumber +
                ", Email Address: " + emailAddress +
                ", Date Of Birth: " + dateOfBirth +
                ", Guest ID: " + guestID +
                ", Reservation ID: " + reservationID;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAdress() {
        return emailAddress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAddress = emailAdress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

}
