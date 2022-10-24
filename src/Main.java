import model.Room;

import static model.RoomType.DOUBLE;
import static model.RoomType.SINGLE;

public class Main {
    public static void main(String[] args) {
        Room room1 = new Room("123", 10.8, SINGLE);
        //System.out.println("Hello world!");
        System.out.println(room1);
    }
}