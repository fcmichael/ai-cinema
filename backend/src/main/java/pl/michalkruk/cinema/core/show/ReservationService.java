package pl.michalkruk.cinema.core.show;

import org.springframework.stereotype.Service;

@Service
class ReservationService {

    private final ReservationRepository reservationRepository;

    ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
