package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that helps run the console app
 */
public class RunApp {
    private Scanner scan = new Scanner(System.in);
    private Database db = new Database();
    int hotelChoice;
    int roomTypeChoice;
    String checkIn;
    String checkOut;
    int roomNumber;
    int amountOfGuests;
    int reservationID;
    ArrayList<Integer> guestIDs = new ArrayList<>();

    public RunApp(){
        mainMenu();
    }

    /**
     * Prints out app main menu and uses all required methods
     */
    public void mainMenu(){

        int choice;
        boolean continueRun = true;
        while (continueRun){
            System.out.println("This app is a hotel booking management system. Please make a choice");
            System.out.println("1. Book a reservation \n2. Cancel a reservation \n3. See all guests \n4. See all reservations \n5. Quit");
            choice = Integer.parseInt(scan.nextLine());
            switch (choice){
                case 1:
                    System.out.println("You've chosen to book a reservation. Choose your desired hotel by hotelID:");
                    showAllHotels();
                    hotelChoice = Integer.parseInt(scan.nextLine());
                    getFacilitiesByHotelID();
                    checkHotelFacilities();
                    addAmountOfGuests();
                    chooseRoomType();
                    checkRoomAvailabilityByDate();
                    reservationID = bookReservation(checkIn, checkOut, roomTypeChoice, hotelChoice,amountOfGuests, guestIDs.get(0), roomNumber);
                    for (Integer guestID : guestIDs){
                        db.assignReservationIDtoGuest(reservationID, guestID);
                    }
                    break;
                case 2:
                    cancelReservationMenu();
                    break;
                case 3:
                    showAllGuests();
                    break;
                case 4:
                    getAllReservations();
                    break;
                case 5:
                    System.out.println("Quitting program...");
                    continueRun = false;
            }
        }
    }

    /**
     * method to get all reservations existing in database
     */
    private void getAllReservations(){
        System.out.println("Here is a list of all reservations:");
        ArrayList<Reservation> reservations = db.getAllReservations();
        for (Reservation reservation : reservations){
            System.out.println(reservation);
        }
    }

    /**
     * Method to cancel a reservation by entering reservation ID
     */
    private void cancelReservationMenu(){
        int choice;
        System.out.println("Here is a list of all reservations:");
        ArrayList<Reservation> reservations = db.getAllReservations();
        for (Reservation reservation : reservations){
            System.out.println(reservation);
        }
        System.out.println("Please enter the reservation number you'd like to remove:");
        reservationID = Integer.parseInt(scan.nextLine());
        ArrayList<Guest> guests = db.getGuestsByReservationID(reservationID);
        System.out.println("Here are the guests for this reservation:");
        for (Guest guest : guests){
            System.out.println(guest);
        }
        System.out.println("Would you like to remove the whole reservation? 1. Yes  2. No, go back to menu");
        choice = Integer.parseInt(scan.nextLine());
        switch (choice){
            case 1:
                db.deleteGuestsByReservationID(reservationID);
                db.deleteReservationByID(reservationID);
                mainMenu();
            case 2:
                mainMenu();
                break;
        }
    }

    /**
     * method to create a new reservation
     */
    private int bookReservation(String checkIn, String checkOut, int roomID, int hotelID, int amountOfGuests, int guestID, int roomNumber){
        return db.createReservation(new Reservation(checkIn, checkOut, roomID, hotelID, amountOfGuests, guestID, roomNumber));
    }

    /**
     * method to show all guests in database
     */
    private void showAllGuests(){
        ArrayList<Guest> guests = db.getAllGuests();
        for (Guest guest : guests){
            System.out.println(guest);
        }
    }

    /**
     * method to select a room type for hotel
     */
    private void chooseRoomType(){
        System.out.println("Please select suitable room type");
        ArrayList<Room> roomTypes = db.getRoomTypes();
        for (Room rooms : roomTypes){
            System.out.println(rooms);
        }
        roomTypeChoice = Integer.parseInt(scan.nextLine());
    }

    /**
     * method to create new guest
     * @return add new guest to database
     */
    private int addGuest(int turn){
        String firstName;
        System.out.println("Enter the first name of guest " + turn + ":");
        firstName = scan.nextLine();
        String lastName;
        System.out.println("Enter the last name of guest " + turn + ":");
        lastName = scan.nextLine();
        String phoneNumber;
        System.out.println("Enter the phone number of guest " + turn + ":");
        phoneNumber = scan.nextLine();
        String emailAdress;
        System.out.println("Enter the email address of guest " + turn + ":");
        emailAdress = scan.nextLine();
        String dateOfBirth;
        System.out.println("Enter the date of birth of guest in format YYYY-MM-DD" + turn + ":");
        dateOfBirth = scan.nextLine();

        return db.createGuest(new Guest(firstName, lastName, phoneNumber, emailAdress, dateOfBirth));
    }

    /**
     * Method to select amount of guests in a booking
     */
    public ArrayList<Integer> addAmountOfGuests(){
        System.out.println("Please enter the amount of guests that would like to stay");
        amountOfGuests = Integer.parseInt(scan.nextLine());
        int counter = amountOfGuests;
        int turn = 1;

        while (counter != 0){
            guestIDs.add(addGuest(turn));
            counter--;
            turn++;
        }
        return guestIDs;
    }

    /**
     * Method to search for available rooms
     */
    private void checkRoomAvailabilityByDate(){
        System.out.println("Enter check in date in format YYYY-MM-DD");
        checkIn = scan.nextLine();
        System.out.println("Enter check out date in format YYYY-MM-DD");
        checkOut = scan.nextLine();

        System.out.println("Here are rooms available after your search criteria:");
        ArrayList<Room_location> availableRooms = db.getAvailableRooms(checkIn, checkOut, hotelChoice, roomTypeChoice);
        for (Room_location room : availableRooms){
            System.out.println(room);
        }
        System.out.println("Please enter the room number that you'd like to book");
        roomNumber = Integer.parseInt(scan.nextLine());

    }

    /**
     * Method to view all hotels available
     */
    public void showAllHotels(){
        ArrayList<Hotel> hotels = db.getAllHotels();
        for(Hotel hotel : hotels){
            System.out.println(hotel);
        }
    }

    /**
     * Method to check if customer is happy with hotel choice
     */
    private void checkHotelFacilities(){
        System.out.println("Is the customer satisfied with the facilities provided? 1. Continue  2. Go Back");
        int hChoice = Integer.parseInt(scan.nextLine());
        if (hChoice == 1){
        }
        else if (hChoice == 2){
            mainMenu();
        }
        else {
            System.out.println("Please press either 1 or 2");
        }
    }

    /**
     * method to get what facilities a hotel offers
     */
    public void getFacilitiesByHotelID(){
        System.out.println("Here are the facilities this hotel provides:");
        ArrayList<Hotel_facilities> facilities = db.getHotelFacilities(hotelChoice);
        for(Hotel_facilities facility : facilities){
            System.out.println(facility);
        }
    }
}
