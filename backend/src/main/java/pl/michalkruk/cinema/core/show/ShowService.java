package pl.michalkruk.cinema.core.show;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.michalkruk.cinema.core.movie.Movie;
import pl.michalkruk.cinema.core.show.exception.SeatAlreadyOccupied;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final ModelMapper modelMapper;
    private final ShowRepository showRepository;
    private final ReservedSeatRepository reservedSeatRepository;
    private final ReservationRepository reservationRepository;

    public ShowService(ModelMapper modelMapper, ShowRepository showRepository, ReservedSeatRepository reservedSeatRepository, ReservationRepository reservationRepository) {
        this.modelMapper = modelMapper;
        this.showRepository = showRepository;
        this.reservedSeatRepository = reservedSeatRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Show> findShowsByMovieAndDateAndTimeAfter(Movie movie, LocalDate date, LocalTime time) {
        return showRepository.findAllByMovieAndShowDateAndShowTimeAfterOrderByShowTimeAsc(movie, date, time);
    }

    ShowDTO findShowById(long id) {
        Show show = showRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ShowDTO dto = modelMapper.map(show, ShowDTO.class);
        dto.setReservedSeats(getReservedSeatsForShow(id));
        return dto;
    }

    Set<String> getReservedSeatsForShow(long id) {
        return reservedSeatRepository.findSeatByShowId(id);
    }

    ReservationDTO makeReservation(long showId, ReservationForm form) {
        if (areAllSeatsFree(showId, form.getSeats())) {
            Show show = showRepository.findById(showId).orElseThrow(EntityNotFoundException::new);
            Reservation reservation = Reservation.builder()
                    .show(show)
                    .timestamp(LocalDateTime.now())
                    .clientsInfo(form.getClientsInfo())
                    .build();

            reservationRepository.save(reservation);

            Set<String> reservedSeats = reserveSeats(reservation, form.getSeats());

            return ReservationDTO.builder()
                    .id(reservation.getId())
                    .clientsInfo(reservation.getClientsInfo())
                    .showDate(reservation.getShow().getShowDate().toString())
                    .showTime(reservation.getShow().getShowTime().toString())
                    .movieTitle(reservation.getShow().getMovie().getTitle())
                    .reservedSeats(reservedSeats)
                    .build();
        }

        throw new SeatAlreadyOccupied();
    }

    boolean areAllSeatsFree(long showId, Set<String> seatsToReserve) {
        Set<String> allReservedSeats = reservedSeatRepository.findSeatByShowId(showId);
        return seatsToReserve.stream().noneMatch(allReservedSeats::contains);
    }

    private Set<String> reserveSeats(Reservation reservation, Set<String> seatsToReserve) {
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
}
