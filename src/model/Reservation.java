package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(customer, that.customer) && Objects.equals(room, that.room) && Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate, that.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, room, checkInDate, checkOutDate);
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        return "Reservation\n"
                + customer.getFirstName() + " " + customer.getLastName() + "\n"
                + "Room: " + room.getRoomNumber() + " - " + room.getRoomType() + " bed" + "\n"
                + "Price: $" + room.getRoomPrice() + " per night" + "\n"
                + "Checkin Date: " + formatter.format(checkInDate) + "\n"
                + "Checkout Date: " + formatter.format(checkOutDate)
                ;
    }


}
