package model;

import java.util.Objects;

public class Room implements IRoom{
    private final String roomNumber;
    private final Double price;
    private final RoomType type;


    public Room (String roomNumber, Double price, RoomType type){
        this.roomNumber = roomNumber;
        this.price = price;
        this.type = type;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return type;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString(){
        return "room: " + roomNumber + " price: " + price + " room type: " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
