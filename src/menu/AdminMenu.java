package menu;

import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();
    public static void selectAdminOptions(){
        while(true){
            try{
                int selection = adminViewOptions();
                switch (selection) {
                    case 1:
                        //method to view all customers created in system
                        seeAllCustomers();
                        break;

                    case 2:
                        //method to view all rooms created in system
                        seeAllRooms();
                        break;
                    case 3:
                        //method to view all reservations created in system
                        seeAllReservations();
                        break;

                    case 4:
                        //method to retrieve/open admin menu
                        addARoom();
                        break;

                        //exits application
                    case 5:
                        //method to return back to main menu
                        returnToMainMenu();
                        break;
            }
        } catch(Exception ex){
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }

    public static int adminViewOptions() {
        System.out.println("Admin menu");
        System.out.println("---------------------------");
        System.out.println("1: See all customers");
        System.out.println("2: See all rooms");
        System.out.println("3: See all reservations");
        System.out.println("4: Add a room");
        System.out.println("5: Back to Main Menu");
        System.out.println("--------------------------------------------------");
        System.out.println("Please enter a number for the menu option");
        Scanner scanner = new Scanner(System.in);
        int guestSelection = scanner.nextInt();
        return guestSelection;
    }

    public static void seeAllCustomers(){
        customerService.getAllCustomers();
    }

    public static void seeAllRooms(){
        Collection<IRoom> allRoom;
        allRoom = reservationService.getAllRooms();
        for (IRoom room: allRoom) {
            System.out.println(room);
        }
    }

    public static void seeAllReservations(){
        reservationService.printAllReservation();
    }

    public static void addARoom(){


        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter room number: ");
            String roomNumber = scanner.nextLine();

            System.out.println("Enter room price: ");
            Double price = scanner.nextDouble();
            List<IRoom> rooms = new ArrayList<>();
            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            try{
                int type = scanner.nextInt();
                switch (type){
                    case 1:
                        RoomType roomType1 = RoomType.SINGLE;
                        IRoom room1 = new Room(roomNumber, price, roomType1);
                        rooms.add(room1);
                        adminResource.addRoom(rooms);
                        break;

                    case 2:
                        RoomType roomType2 = RoomType.DOUBLE;
                        IRoom room2 = new Room(roomNumber, price, roomType2);
                        rooms.add(room2);
                        adminResource.addRoom(rooms);
                        break;
                }
                System.out.println("Would you like to add another room: Y/N?");
                String option;
                do {
                    option = scanner.next();
                    if (!option.equalsIgnoreCase("y") && !option.equalsIgnoreCase("n")) {
                        System.out.println("Please enter Y (Yes) or N (No)");
                    }
                    else{
                        break;
                    }
                } while(true);


                if (option.equalsIgnoreCase("y")){
                    continue;
                } else {
                    break;
                }

            } catch(Exception ex){
                System.out.println(ex.getLocalizedMessage());
            }



        }



    }

    public static void returnToMainMenu(){
        MainMenu.selectMainMenuOptions();
    }



}
