package pl.michalkruk.cinema.core.show;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.michalkruk.cinema.core.show.exception.SeatAlreadyOccupiedException;
import pl.michalkruk.cinema.core.show.exception.ShowStartsSoonException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ShowFacade {

    private final ModelMapper modelMapper;
    private final ShowService showService;
    private final ReservationService reservationService;
    private final ReservedSeatService reservedSeatService;
    private final ConcurrentHashMap<Long, Set<String>> temporaryReservedSeats = new ConcurrentHashMap<>();

    public ShowFacade(ModelMapper modelMapper, ShowService showService,
                      ReservationService reservationService, ReservedSeatService reservedSeatService) {
        this.modelMapper = modelMapper;
        this.showService = showService;
        this.reservationService = reservationService;
        this.reservedSeatService = reservedSeatService;
    }

    @Transactional(readOnly = true)
    public ShowDTO findById(Long id) {
        Show show = showService.findById(id);
        ShowDTO dto = modelMapper.map(show, ShowDTO.class);
        Set<String> reserved = reservedSeatService.getReservedSeatsForShow(id);

        if (temporaryReservedSeats.containsKey(id)) {
            reserved.addAll(temporaryReservedSeats.get(id));
        }

        dto.setReservedSeats(reserved);
        return dto;
    }

    @Transactional
    public ReservationDTO makeReservation(Long showId, ReservationForm form) {
        Show show = showService.findById(showId);

        if (isReservationPossible(show, form)) {
            Reservation reservation = mapFromForm(form, show);
            reservationService.save(reservation);
            Set<String> reservedSeats = reservedSeatService.reserveSeats(reservation, form.getSeats());
            return mapToDTO(reservation, show, reservedSeats);
        } else {
            return null;
        }
    }

    void addTemporaryReservation(Long showId, TemporarySeatReservationForm seat) {
        if (seat.isReserved()) {
            if (!temporaryReservedSeats.containsKey(showId)) {
                temporaryReservedSeats.put(showId, new HashSet<>());
            }
            temporaryReservedSeats.get(showId).add(seat.getName());
        } else {
            temporaryReservedSeats.get(showId).remove(seat.getName());
        }
    }

    void removeTemporaryReservations(Long showId, Set<String> seats) {
        if (temporaryReservedSeats.containsKey(showId)) {
            temporaryReservedSeats.get(showId).removeAll(seats);
        }
    }

    private boolean isReservationPossible(Show show, ReservationForm form) {
        if (isReservationRequestTooLate(show)) {
            throw new ShowStartsSoonException();
        }

        if (!areAllSeatsFree(show, form.getSeats())) {
            throw new SeatAlreadyOccupiedException();
        }

        return true;
    }

    private boolean isReservationRequestTooLate(Show show) {
        LocalDateTime movieTimeStamp = LocalDateTime.of(show.getShowDate(), show.getShowTime());

        return LocalDateTime.now()
                .isAfter(movieTimeStamp.minusMinutes(ReservationLimit.MOVIE_RESERVATION_DEADLINE_MINUTES));
    }

    private boolean areAllSeatsFree(Show show, Set<String> seats) {
        return reservedSeatService.areAllSeatsFree(show.getId(), seats);
    }

    private Reservation mapFromForm(ReservationForm form, Show show) {
        return Reservation.builder()
                .show(show)
                .timestamp(LocalDateTime.now())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .phoneNumber(form.getPhoneNumber())
                .build();
    }

    private ReservationDTO mapToDTO(Reservation reservation, Show show, Set<String> reservedSeats) {
        ReservationDTO reservationDTO = mapToDTO(reservation);
        reservationDTO.setMovieTitle(show.getMovie().getTitle());
        reservationDTO.setReservedSeats(reservedSeats);
        return reservationDTO;
    }

    private ReservationDTO mapToDTO(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDTO.class);
    }
}
