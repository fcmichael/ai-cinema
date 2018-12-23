package pl.michalkruk.cinema.core.show;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:integration-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class ShowControllerTest {

    private final String baseUrl = "/shows";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_return_show_by_id() {
        Long id = 1L;
        String url = baseUrl + "/" + id;

        ResponseEntity<ShowDTO> response = restTemplate.exchange(url,
                HttpMethod.GET, null, ShowDTO.class);

        ShowDTO show = response.getBody();

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals((Long) 1L, show.getId());
        Assert.assertEquals("Skazani na Shawshank", show.getMovieTitle());
        Assert.assertEquals("A", show.getAuditoriumName());
        Assert.assertEquals(LocalDate.now().toString(), show.getShowDate());
        Assert.assertEquals("11:30", show.getShowTime());
        Assert.assertEquals(Short.valueOf("9"), show.getAuditoriumRows());
        Assert.assertEquals(Short.valueOf("18"), show.getAuditoriumColumns());
    }

    @Test
    public void should_return_reserved_seats_for_show() {
        Long showId = 1L;
        String url = baseUrl + "/" + showId + "/reservedSeats";

        ResponseEntity<List<String>> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
                });

        List<String> reservedSeats = responseEntity.getBody();

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(6, reservedSeats.size());
        Assert.assertTrue(CollectionUtils.isEqualCollection(
                Arrays.asList("A1", "A7", "A10", "A17", "A13", "A4"), reservedSeats));

    }
}
