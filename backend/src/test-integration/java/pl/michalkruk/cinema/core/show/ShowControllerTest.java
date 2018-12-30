package pl.michalkruk.cinema.core.show;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:integration-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class ShowControllerTest {

    private final String baseUrl = "/shows";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservedSeatRepository reservedSeatRepository;

    @Test
    public void should_return_show_with_reserved_seats_by_id() {
        // given
        Long id = 1L;
        String url = baseUrl + "/" + id;

        // when
        ResponseEntity<ShowDTO> response = restTemplate.exchange(url,
                HttpMethod.GET, null, ShowDTO.class);
        ShowDTO show = response.getBody();

        // then
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals((Long) 1L, show.getId());
        Assert.assertEquals("Skazani na Shawshank", show.getMovieTitle());
        Assert.assertEquals("A", show.getAuditoriumName());
        Assert.assertEquals(LocalDate.now().toString(), show.getShowDate());
        Assert.assertEquals("11:30", show.getShowTime());
        Assert.assertEquals(Short.valueOf("9"), show.getAuditoriumRows());
        Assert.assertEquals(Short.valueOf("18"), show.getAuditoriumColumns());
        Assert.assertEquals(6, show.getReservedSeats().size());
        Assert.assertTrue(CollectionUtils.isEqualCollection(
                Arrays.asList("A1", "A7", "A10", "A17", "A13", "A4"), show.getReservedSeats()));
    }

    @Test
    public void should_make_seats_reservation_for_show_if_seats_are_free() {
        // given
        int reservationsCountBefore = reservationRepository.findAll().size();
        int reservedSeatsCountBefore = reservedSeatRepository.findAll().size();

        Long showId = 2L;
        Show show = showRepository.findById(showId).get();
        String url = baseUrl + "/" + showId + "/reservations";
        Set<String> seatsToReserve = new HashSet<>(Arrays.asList("H2", "H3", "H4"));
        ReservationForm form = new ReservationForm(seatsToReserve, "Jan", "Nowak", "111111111");
        HttpEntity<ReservationForm> request = new HttpEntity<>(form);

        // when
        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange(url,
                HttpMethod.POST, request, ReservationDTO.class);
        ReservationDTO reservation = responseEntity.getBody();

        // then
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assert.assertEquals("Jan", reservation.getFirstName());
        Assert.assertEquals("Nowak", reservation.getLastName());
        Assert.assertEquals("111111111", reservation.getPhoneNumber());
        Assert.assertEquals(reservationsCountBefore + 1, reservationRepository.findAll().size());
        Assert.assertEquals(reservedSeatsCountBefore + seatsToReserve.size(), reservedSeatRepository.findAll().size());
        Assert.assertEquals(show.getShowDate().toString(), reservation.getShowDate());
        Assert.assertEquals(show.getShowTime().toString(), reservation.getShowTime());
        Assert.assertEquals(show.getMovie().getTitle(), reservation.getMovieTitle());
        Assert.assertTrue(CollectionUtils.isEqualCollection(reservation.getReservedSeats(), seatsToReserve));
    }

    @Test
    public void should_not_make_seats_reservation_for_show_if_any_of_seats_is_occupied() {
        // given
        int reservationsCountBefore = reservationRepository.findAll().size();
        int reservedSeatsCountBefore = reservedSeatRepository.findAll().size();

        Long showId = 1L;
        String url = baseUrl + "/" + showId + "/reservations";
        Set<String> seatsToReserve = new HashSet<>(Arrays.asList("A1", "A2"));
        ReservationForm form = new ReservationForm(seatsToReserve, "Jan", "Nowak", "111111111");
        HttpEntity<ReservationForm> request = new HttpEntity<>(form);

        // when
        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange(url,
                HttpMethod.POST, request, ReservationDTO.class);

        // then
        Assert.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        Assert.assertEquals(reservationsCountBefore, reservationRepository.findAll().size());
        Assert.assertEquals(reservedSeatsCountBefore, reservedSeatRepository.findAll().size());
    }
}
