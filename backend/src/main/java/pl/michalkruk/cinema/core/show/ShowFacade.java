package pl.michalkruk.cinema.core.show;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.michalkruk.cinema.core.show.exception.SeatAlreadyOccupied;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class ShowFacade {

    private final ModelMapper modelMapper;
    private final ShowService showService;
    private final ReservationService reservationService;
    private final ReservedSeatService reservedSeatService;

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
        dto.setReservedSeats(reservedSeatService.getReservedSeatsForShow(id));
        return dto;
    }

    @Transactional
    public ReservationDTO makeReservation(Long showId, ReservationForm form) {
        if (reservedSeatService.areAllSeatsFree(showId, form.getSeats())) {
            Show show = showService.findById(showId);
            Reservation reservation = mapFromForm(form, show);
            reservationService.save(reservation);
            Set<String> reservedSeats = reservedSeatService.reserveSeats(reservation, form.getSeats());
            return mapToDTO(reservation, show, reservedSeats);
        }

        throw new SeatAlreadyOccupied();
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
