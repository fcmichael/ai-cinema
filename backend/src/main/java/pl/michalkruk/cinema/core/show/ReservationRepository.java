package pl.michalkruk.cinema.core.show;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
