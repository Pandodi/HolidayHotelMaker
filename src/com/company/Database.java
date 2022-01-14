package com.company;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class with methods that sends queries to the database
 */
public class Database {
    Connection conn = null;

    public  Database(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:HolidayMaker.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Reservation> getAllReservations(){
        ArrayList<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                 int ReservationID = resultSet.getInt("Reservation_ID");
                 String checkIn = resultSet.getString("Check_In");
                 String checkOut = resultSet.getString("Check_Out");
                 int roomID = resultSet.getInt("Room_ID");
                 int hotelID = resultSet.getInt("Hotel_ID");
                 int numberOfGuests = resultSet.getInt("Number_Of_Guests");
                 int guestID = resultSet.getInt("Guest_ID");
                 int roomNumber = resultSet.getInt("Room_Number");

                 reservations.add(new Reservation(ReservationID, checkIn, checkOut, roomID, hotelID, numberOfGuests, guestID, roomNumber));
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return reservations;
    }

    public ArrayList<Hotel> getAllHotels(){
        ArrayList<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM hotel";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String hotelName = resultSet.getString("Hotel_Name");
                int hotelID = resultSet.getInt("Hotel_ID");
                String hotelAddress = resultSet.getString("Hotel_Address");
                String hotelPhoneNumber = resultSet.getString("Hotel_Phone_Number");
                String hotelCity = resultSet.getString("Hotel_City");
                String hotelCountry = resultSet.getString("Hotel_Country");
                String hotelEmail = resultSet.getString("Hotel_Email");
                String hotelZipCode = resultSet.getString("Hotel_Zip_Code");
                hotels.add(new Hotel(hotelName, hotelID, hotelAddress, hotelPhoneNumber, hotelCity, hotelCountry, hotelEmail, hotelZipCode));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return hotels;
    }

    public ArrayList<Guest> getAllGuests(){
        ArrayList<Guest> guests = new ArrayList<>();
        String query = "SELECT * FROM guest";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String firstName = resultSet.getString("First_Name");
                String lastName = resultSet.getString("Last_Name");
                String phoneNumber = resultSet.getString("Phone_Number");
                String emailAddress = resultSet.getString("Email_Adress");
                String dateOfBirth = resultSet.getString("Date_Of_Birth");
                int guestID = resultSet.getInt("Guest_ID");
                int reservationID = resultSet.getInt("Reservation_ID");

                guests.add(new Guest(firstName, lastName, phoneNumber, emailAddress, dateOfBirth, guestID, reservationID));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return guests;
    }

    public ArrayList<Guest> getGuestsByReservationID(int reservationID){
        ArrayList<Guest> guests = new ArrayList<>();
        String query = "SELECT * FROM guest WHERE guest.Reservation_ID=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, reservationID);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                String firstName = resultSet.getString("First_Name");
                String lastName = resultSet.getString("Last_Name");
                String phoneNumber = resultSet.getString("Phone_Number");
                String emailAddress = resultSet.getString("Email_Adress");
                String dateOfBirth = resultSet.getString("Date_Of_Birth");
                int guestID = resultSet.getInt("Guest_ID");
                reservationID = resultSet.getInt("Reservation_ID");

                guests.add(new Guest(firstName, lastName, phoneNumber, emailAddress, dateOfBirth, guestID, reservationID));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return guests;
    }

    public void deleteReservationByID(int reservationID){
        String query = "DELETE FROM reservation WHERE Reservation_ID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, reservationID);
            stmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void deleteGuestsByReservationID(int reservationID){
        String query = "DELETE FROM guest WHERE Reservation_ID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, reservationID);
            stmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Hotel_facilities> getHotelFacilities(int hotelID){
        ArrayList<Hotel_facilities> facilities = new ArrayList<>();
        String query = "SELECT facility.Facility_Name , hotel_facilities.Hotel_Facitilies_Name, hotel.Hotel_Name FROM facility\n" +
                "       INNER JOIN\n" +
                "       hotel ON facility.Hotel_ID = hotel.Hotel_ID\n" +
                "       INNER JOIN\n" +
                "       hotel_facilities ON facility.Hotel_Facilities_ID = hotel_facilities.Hotel_Facilities_ID\n" +
                " WHERE hotel.Hotel_ID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, hotelID);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                int hotelFacilitiesID = resultSet.getInt("Facility_Name");
                String hotelFacilitiesName = resultSet.getString("Hotel_Facitilies_Name");
                String hotelName = resultSet.getString("Hotel_Name");

                facilities.add(new Hotel_facilities(hotelFacilitiesName, hotelFacilitiesID));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return facilities;
    }

    public int createGuest(Guest newGuest){
        int incrementedID = 0;
        String query = "INSERT INTO guest (First_Name, Last_Name, Phone_Number, Email_Adress, Date_Of_Birth) VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newGuest.getFirstName());
            stmt.setString(2, newGuest.getLastName());
            stmt.setString(3, newGuest.getPhoneNumber());
            stmt.setString(4, newGuest.getEmailAdress());
            stmt.setString(5, newGuest.getDateOfBirth());

            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();

            while(generatedKeys.next()){
                incrementedID = generatedKeys.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return incrementedID;
    }

    public int createReservation(Reservation newReservation){
        int incrementedID = 0;
        String query = "INSERT INTO reservation (Check_In, Check_Out, Room_ID, Hotel_ID, Number_Of_Guests, Guest_ID, Room_Number)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newReservation.getCheckIn());
            stmt.setString(2, newReservation.getCheckOut());
            stmt.setInt(3, newReservation.getRoomID());
            stmt.setInt(4, newReservation.getHotelID());
            stmt.setInt(5, newReservation.getNumberOfGuests());
            stmt.setInt(6, newReservation.getGuestID());
            stmt.setInt(7, newReservation.getRoomNumber());

            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();

            while(generatedKeys.next()){
                incrementedID = generatedKeys.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return incrementedID;
    }

    public ArrayList<Room> getRoomTypes(){
        ArrayList<Room> roomTypes = new ArrayList<>();
        String query = "SELECT * FROM room";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                int roomID = resultSet.getInt("Room_ID");
                String roomType = resultSet.getString("Room_Type");
                int roomCapacity = resultSet.getInt("Room_Capacity");
                int roomAmount = resultSet.getInt("Room_Amount");

                roomTypes.add(new Room(roomID, roomType, roomCapacity, roomAmount));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return roomTypes;
    }

    public ArrayList<Room_location> getAvailableRooms(String checkIn, String checkOut, int hotelID, int roomID){
        ArrayList<Room_location> availableRooms = new ArrayList<>();
        String query = "SELECT room_location.room_Number FROM room_location \n" +
                "LEFT JOIN reservation ON room_location.Room_Number = reservation.Room_Number \n" +
                "and reservation.Check_Out >= ? AND reservation.Check_In <= ?\n" +
                "WHERE room_location.Hotel_ID = ? AND room_location.Room_ID = ?\n" +
                "and reservation.Reservation_ID IS NULL";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, checkIn);
            stmt.setString(2, checkOut);
            stmt.setInt(3, hotelID);
            stmt.setInt(4, roomID);

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                int roomNumber = resultSet.getInt("Room_Number");
                availableRooms.add(new Room_location(roomNumber));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return availableRooms;
    }

    public void assignReservationIDtoGuest(int reservationID, int guestID){
        String query = "UPDATE guest\n" +
                "SET Reservation_ID = ?\n" +
                "WHERE guest_ID = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, reservationID);
            stmt.setInt(2, guestID);

            stmt.executeUpdate();


        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
