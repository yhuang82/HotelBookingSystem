package service;

public interface IReservationService {
    default void showDefault() {
        System.out.println("Reserving");
    }



}
