package pl.michalkruk.cinema.core.show.exception;

public class SeatAlreadyOccupiedException extends RuntimeException {

    public SeatAlreadyOccupiedException() {
        super("At least one of seats has been already occupied");
    }
}
