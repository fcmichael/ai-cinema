package pl.michalkruk.cinema.core.show;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowServiceTest {

    private ShowService showService;

    private ModelMapper modelMapper;
    private ShowRepository showRepository;
    private ReservedSeatRepository reservedSeatRepository;
    private ReservationRepository reservationRepository;

    @Before
    public void init() {
        this.modelMapper = mock(ModelMapper.class);
        this.showRepository = mock(ShowRepository.class);
        this.reservedSeatRepository = mock(ReservedSeatRepository.class);
        this.reservationRepository = mock(ReservationRepository.class);
        this.showService = new ShowService(modelMapper,
                showRepository, reservedSeatRepository, reservationRepository);
    }

    @Test
    public void should_return_that_all_seats_are_free() {
        // given
        Long showId = 1L;
        Set<String> seatsToReserve = new HashSet<>(Arrays.asList("H5", "H6"));
        Set<String> reservedSeats = new HashSet<>(Arrays.asList("H2", "H3", "H4"));
        when(reservedSeatRepository.findSeatByShowId(showId)).thenReturn(reservedSeats);

        // when
        boolean areAllFree = showService.areAllSeatsFree(showId, seatsToReserve);

        // then
        Assert.assertTrue(areAllFree);
    }

    @Test
    public void should_return_that_not_all_seats_are_free() {
        // given
        Long showId = 1L;
        Set<String> seatsToReserve = new HashSet<>(Arrays.asList("H1", "H2"));
        Set<String> reservedSeats = new HashSet<>(Arrays.asList("H2", "H3", "H4"));
        when(reservedSeatRepository.findSeatByShowId(showId)).thenReturn(reservedSeats);

        // when
        boolean areAllFree = showService.areAllSeatsFree(showId, seatsToReserve);

        // then
        Assert.assertFalse(areAllFree);
    }
}
