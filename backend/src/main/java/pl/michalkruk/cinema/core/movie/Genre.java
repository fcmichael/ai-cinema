package pl.michalkruk.cinema.core.movie;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Genre {
    AKCJA("Akcja"),
    ANIMOWANY("Animowany"),
    BIOGRAFICZNY("Biograficzny"),
    DRAMAT("Dramat"),
    FANTASY("Fantasy"),
    GANGSTERSKI("Gangsterski"),
    HORROT("Horror"),
    KATASTROFICZNY("Katastroficzny"),
    KOMEDIA("Komedia"),
    KOMEDIA_ROMANTYCZNA("Komedia romantyczna"),
    KRYMINALNY("Kryminalny"),
    MUSICAL("Musical"),
    OBYCZAJOWY("Obyczajowy"),
    SCI_FI("Science-Fiction"),
    THRILLER("Thriller"),
    WOJENNY("Wojenny");

    private String description;

    @Override
    public String toString() {
        return description;
    }
}
