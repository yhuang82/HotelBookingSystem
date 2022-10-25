package menu;

import api.HotelResource;
import com.sun.tools.javac.Main;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();


        public static void selectMainMenuOptions() {
            while(true) {
                try{
                    int selection = mainMenuOptions();
                    switch (selection) {
                        case 1 ->
                            //finding and reserving a room
                                findAndReserveARoom();
                        case 2 ->
                            //allow customer to see the reservation just created
                                seeCustomerReservation();
                        case 3 ->
                            //assist customer with creating an account
                                createAnAccount();
                        case 4 ->
                            //retrieve/open admin menu
                                adminViewOptions();

                        //exits application
                        case 5 -> System.exit(0);
                    }
                } catch(Exception ex){
                    System.out.println("Invalid input");
                    }
            }
        }

        public static int mainMenuOptions () throws Exception{
            System.out.println("Welcome to the Hotel Reservation Application");
            System.out.println("_______________________________________________");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an Account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("__________________________________________________");
            System.out.println("please select a number for the menu option");
            Scanner scanner = new Scanner(System.in);
            int guestSelection = scanner.nextInt();
            //System.out.println("1234567");
            return guestSelection;
        }

        public static void findAndReserveARoom(){
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
                String checkInString = scanner.nextLine();
                SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
                Date checkInDate = formatter.parse(checkInString);

                System.out.println("Enter checkOut Date mm/dd/yyyy example 02/21/2020");
                String checkOutString = scanner.nextLine();
                Date checkOutDate = formatter.parse(checkOutString);

                Collection<IRoom> availableRoom = hotelResource.findARoom(checkInDate, checkOutDate);
                boolean added = false;
                if (availableRoom.isEmpty()){
                    added = true;
                    System.out.println("Will search the rooms available after 7 days");
                    Calendar checkIn = Calendar.getInstance();
                    checkIn.setTime(checkInDate);
                    checkIn.add(Calendar.DATE, 7);
                    Calendar checkOut= Calendar.getInstance();
                    checkOut.setTime(checkOutDate);
                    checkOut.add(Calendar.DATE, 7);
                    checkInDate = checkIn.getTime();
                    checkOutDate= checkOut.getTime();
                    availableRoom = hotelResource.findARoom(checkInDate, checkOutDate);
                    if(availableRoom.isEmpty()){
                        System.out.println("Sorry, there is not available rooms");
                        return;
                    }
                    System.out.println("7days later available rooms: ");
//                    for(IRoom room : availableRoom1){
//                        System.out.println(room);
//
//                    }
                }
                for(IRoom room : availableRoom){
                    System.out.println(room);

                }
                if(added == true){
//                    Calendar checkIn = Calendar.getInstance();
//                    checkIn.setTime(checkInDate);
//                    checkIn.add(Calendar.DATE, 7);
//                    Calendar checkOut= Calendar.getInstance();
//                    checkOut.setTime(checkOutDate);
//                    checkOut.add(Calendar.DATE, 7);
//                    checkInDate = checkIn.getTime();
//                    checkOutDate = checkOut.getTime();
                    System.out.println("Would you like to book a room after 7 days? y/n");
                }
                else
                    System.out.println("Would you like to book a room? y/n");

                // select y to continue, select n return to the main menu.
                String bookRoom = scanner.nextLine();
                if(!bookRoom.equalsIgnoreCase("y")) {
                    return ;
                }


                System.out.println("Do you have an account with us? y/n");
                String hasAccount = scanner.nextLine();
                //select y to continue, select n create an account
                if(!hasAccount.equalsIgnoreCase("y")) {
                    return;
                }


                System.out.println("Enter Email format: name@domain.com");
                String cusEmail = scanner.nextLine();
                Customer customer = customerService.getCustomer(cusEmail);
                System.out.println("What room number would you like to reserve");
                String roomNumber = scanner.nextLine();
                IRoom roomReser = reservationService.getARoom(roomNumber);
                Reservation reservation = reservationService.reserveARoom(customer, roomReser, checkInDate, checkOutDate);
                System.out.println(reservation);
                reservationService.showDefault();

            } catch (Exception ex){
                System.out.println(ex.getLocalizedMessage());
            }

        }

        public static void seeCustomerReservation(){
            System.out.println("Please enter your email");
            Scanner scanner = new Scanner(System.in);
            String cusEmail = scanner.nextLine();
            Collection<Reservation> customerReserCollect;
            customerReserCollect = hotelResource.getCustomersReservations(cusEmail);
            for (Reservation res: customerReserCollect) {
                System.out.println(res);
            }
        }

        public static void createAnAccount(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter email format: name@domain.com");
            String newGuestEmail = scanner.nextLine();
            System.out.println("First Name ");
            String firstName = scanner.nextLine();
            System.out.println("Last Name ");
            String lastName = scanner.nextLine();
            hotelResource.createACustomer(newGuestEmail, firstName, lastName);


        }

        public static void adminViewOptions(){
            AdminMenu.selectAdminOptions();
        }






    public static void main(String[] args){
        MainMenu mainMenu = new MainMenu();
        mainMenu.selectMainMenuOptions();
    }
}

















