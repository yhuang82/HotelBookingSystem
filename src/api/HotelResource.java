package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResource;
    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();


    public static HotelResource getInstance(){
        if (hotelResource == null){
            hotelResource = new HotelResource();
        }
        return   hotelResource;
    }

    private HotelResource(){}

    public Customer getCustomer(String email){
        Collection<Customer> cusCollection = customerService.getAllCustomers();
        for (Customer cus : cusCollection){
            if(cus.getEmail().equals(email)){
                return cus;
            }
        }
        return null;
    }

    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }
    // first find the customer by email, and them book a reservation for this customer
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer cus = getCustomer(customerEmail);
        return reservationService.reserveARoom(cus, room, checkInDate, checkOutDate);

    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer cus = getCustomer(customerEmail);
        return reservationService.getCustomersReservation(cus);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkout){
        return reservationService.findRooms(checkIn, checkout);
    }
}
