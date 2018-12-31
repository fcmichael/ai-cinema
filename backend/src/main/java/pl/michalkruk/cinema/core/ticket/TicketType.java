package pl.michalkruk.cinema.core.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
class TicketType {

    @Id
    @GeneratedValue
    private Long id;

    private String category;

    private int price;
}
