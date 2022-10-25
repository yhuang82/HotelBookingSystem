package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarOutputStream;

public class ReservationService implements IReservationService{
    private static ReservationService reservationService = null;
    private Collection<Reservation> setOfReservations = new HashSet<Reservation>();
    private Set<IRoom> setOfRooms = new HashSet<IRoom>();


    //facilitate the singleton pattern
    private ReservationService(){
    }

    //static reference for Reservation class
    public static ReservationService getInstance(){
        if (reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }


    // add rooms to Collection<IRoom>
    public void addRoom(IRoom room){
        setOfRooms.add(room);
    }

    // return room if roomId is in the set
    public IRoom getARoom(String roomId){
        for (IRoom room : setOfRooms) {
            if (roomId.equals(room.getRoomNumber())){
                return room;
            }
        }
        return null;
    }
    //return all rooms
    public Collection<IRoom> getAllRooms() {return setOfRooms;}


    // reserve a room, create a new reservation
    public Reservation reserveARoom(Customer customer, IRoom room,
                                    Date checkInDate, Date checkOutDate){
        Reservation reservedRoom = new Reservation(customer, room, checkInDate, checkOutDate);
        System.out.println("This room has been reserved.");
        setOfReservations.add(reservedRoom);
        return reservedRoom;
    }

    //find rooms according to their checkin date and checkout date
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Set<IRoom> availableRooms = new HashSet<>();
        if (setOfReservations.isEmpty()) {
              availableRooms = setOfRooms;
//            System.out.println(availableRooms.size());
            return availableRooms;
        } else{
            for (IRoom room : setOfRooms){
                boolean reserved = false;
                for (Reservation reservation : setOfReservations){
//                    if ((room.getRoomNumber().equals(reservation.room.getRoomNumber()))
//                        && ((checkInDate.before(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckOutDate()))
//                        || (checkInDate.after(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckOutDate())))) {
//                        availableRooms.add(room);
////                        System.out.println(availableRooms.size());
//                    }
                    if (room.getRoomNumber().equals(reservation.getRoom().getRoomNumber())){

                        if(((checkInDate.before(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckInDate()))
                            || (checkInDate.after(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckOutDate())))) {

                        } else{
                            reserved = true;
                        }
                    }

                }
                if(!reserved){
                    availableRooms.add(room);
                }
            }
        }
        return availableRooms;

    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customersRes = new HashSet<>();
        for(Reservation reservation : setOfReservations) {
            if(reservation.getCustomer().equals(customer)){
                customersRes.add(reservation);
            }
        }
        return customersRes;


    }

    public void printAllReservation(){
        for (Reservation reservation : setOfReservations){
            System.out.println(reservation);
        }

    }





}
