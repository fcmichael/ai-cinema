package pl.michalkruk.cinema.core.show;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class ReservedSeatService {

    private final ReservedSeatRepository reservedSeatRepository;

    ReservedSeatService(ReservedSeatRepository reservedSeatRepository) {
        this.reservedSeatRepository = reservedSeatRepository;
    }

    Set<String> reserveSeats(Reservation reservation, Set<String> seatsToReserve) {
        List<ReservedSeat> reservedSeats = seatsToReserve.stream()
                .map(seat -> ReservedSeat.builder()
                        .reservation(reservation)
                        .show(reservation.getShow())
                        .seat(seat)
                        .build())
                .collect(Collectors.toList());

        return reservedSeatRepository.saveAll(reservedSeats).stream()
                .map(ReservedSeat::getSeat)
                .collect(Collectors.toSet());
    }

    boolean areAllSeatsFree(Long showId, Set<String> seatsToReserve) {
        Set<String> allReservedSeats = reservedSeatRepository.findSeatByShowId(showId);
        return seatsToReserve.stream().noneMatch(allReservedSeats::contains);
    }

    Set<String> getReservedSeatsForShow(Long id) {
        return reservedSeatRepository.findSeatByShowId(id);
    }
}
