package pl.michalkruk.cinema.core.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
}
