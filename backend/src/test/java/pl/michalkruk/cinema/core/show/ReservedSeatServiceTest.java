package pl.michalkruk.cinema.core.show;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservedSeatServiceTest {

    private ReservedSeatService reservedSeatService;

    private ReservedSeatRepository reservedSeatRepository;

    @Before
    public void init() {
        this.reservedSeatRepository = mock(ReservedSeatRepository.class);
        this.reservedSeatService = new ReservedSeatService(reservedSeatRepository);
    }

    @Test
    public void should_return_that_all_seats_are_free() {
        // given
        Long showId = 1L;
        Set<String> seatsToReserve = new HashSet<>(Arrays.asList("H5", "H6"));
        Set<String> reservedSeats = new HashSet<>(Arrays.asList("H2", "H3", "H4"));
        when(reservedSeatRepository.findSeatByShowId(showId)).thenReturn(reservedSeats);

        // when
        boolean areAllFree = reservedSeatService.areAllSeatsFree(showId, seatsToReserve);

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
        boolean areAllFree = reservedSeatService.areAllSeatsFree(showId, seatsToReserve);

        // then
        Assert.assertFalse(areAllFree);
    }
}
