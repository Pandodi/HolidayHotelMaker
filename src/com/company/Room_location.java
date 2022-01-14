package com.company;

public class Room_location {
    private int roomID;
    private int hotelID;
    private int roomNumber;

    public Room_location(int roomID, int hotelID, int roomNumber) {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.roomNumber = roomNumber;
    }

    public Room_location(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Room number: " + roomNumber;

    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
