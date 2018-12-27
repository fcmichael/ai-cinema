package pl.michalkruk.cinema.core.show.exception;

public class SeatAlreadyOccupied extends RuntimeException {

    public SeatAlreadyOccupied() {
        super("At least one of seats has been already occupied");
    }
}
