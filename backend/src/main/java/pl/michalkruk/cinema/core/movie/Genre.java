package pl.michalkruk.cinema.core.movie;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Genre {
    KOMEDIA("Komedia"),
    DRAMAT("Dramat"),
    ANIMOWANY("Animowany"),
    GANGSTERSKI("Gangsterski");

    private String description;

    @Override
    public String toString() {
        return description;
    }
}
