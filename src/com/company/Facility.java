package com.company;

public class Facility {
    private int hotelFacilitiesID;
    private String facilityName;
    private int hotelID;

    public Facility(int hotelFacilitiesID, String facilityName, int hotelID) {
        this.hotelFacilitiesID = hotelFacilitiesID;
        this.facilityName = facilityName;
        this.hotelID = hotelID;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "hotelFacilitiesID=" + hotelFacilitiesID +
                ", facilityName='" + facilityName + '\'' +
                ", hotelID=" + hotelID +
                '}';
    }

    public int getHotelFacilitiesID() {
        return hotelFacilitiesID;
    }

    public void setHotelFacilitiesID(int hotelFacilitiesID) {
        this.hotelFacilitiesID = hotelFacilitiesID;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }
}
