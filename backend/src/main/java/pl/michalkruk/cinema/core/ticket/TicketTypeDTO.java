package pl.michalkruk.cinema.core.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class TicketTypeDTO {
    private String category;
    private int price;
}
