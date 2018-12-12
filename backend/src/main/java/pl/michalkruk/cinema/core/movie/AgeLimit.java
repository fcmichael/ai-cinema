package pl.michalkruk.cinema.core.movie;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AgeLimit {
    B_O("b.o."),
    MIN_12("12"),
    MIN_15("15"),
    MIN_18("18");

    private String description;

    @Override
    public String toString() {
        return description;
    }
}
