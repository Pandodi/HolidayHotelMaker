package com.company;

public class Hotel {
    private String hotelName;
    private int hotelID;
    private String hotelAddress;
    private String hotelPhoneNumber;
    private String hotelCity;
    private String hotelCountry;
    private String hotelEmail;
    private String hotelZipCode;

    public Hotel(String hotelName, int hotelID, String hotelAddress, String hotelPhoneNumber, String hotelCity, String hotelCountry, String hotelEmail, String hotelZipCode) {
        this.hotelName = hotelName;
        this.hotelID = hotelID;
        this.hotelAddress = hotelAddress;
        this.hotelPhoneNumber = hotelPhoneNumber;
        this.hotelCity = hotelCity;
        this.hotelCountry = hotelCountry;
        this.hotelEmail = hotelEmail;
        this.hotelZipCode = hotelZipCode;
    }

    @Override
    public String toString() {
        return
                " Hotel ID:" + hotelID + ", Hotel Name:'" + hotelName + '\'' +
                ", Hotel Address:'" + hotelAddress + '\'' +
                ", Hotel Phone Number:'" + hotelPhoneNumber + '\'' +
                ", Hotel City:'" + hotelCity + '\'' +
                ", Hotel Country:'" + hotelCountry + '\'' +
                ", Hotel Email:'" + hotelEmail + '\'' +
                ", Hotel ZipCode:'" + hotelZipCode + '\'';
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelPhoneNumber() {
        return hotelPhoneNumber;
    }

    public void setHotelPhoneNumber(String hotelPhoneNumber) {
        this.hotelPhoneNumber = hotelPhoneNumber;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getHotelCountry() {
        return hotelCountry;
    }

    public void setHotelCountry(String hotelCountry) {
        this.hotelCountry = hotelCountry;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public String getHotelZipCode() {
        return hotelZipCode;
    }

    public void setHotelZipCode(String hotelZipCode) {
        this.hotelZipCode = hotelZipCode;
    }
}
