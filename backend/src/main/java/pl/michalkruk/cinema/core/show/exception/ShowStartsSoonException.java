package pl.michalkruk.cinema.core.show.exception;

public class ShowStartsSoonException extends RuntimeException {

    public ShowStartsSoonException() {
        super("Reservation is impossible due to starting show soon");
    }
}
