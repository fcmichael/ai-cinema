package pl.michalkruk.cinema.core.programme;

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
import org.springframework.web.util.UriComponentsBuilder;
import pl.michalkruk.cinema.core.programme.ProgrammeMovieDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:integration-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class ProgrammeControllerTest {

    private final String baseUrl = "/programme";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_return_all_movies_when_no_request_parameters_specified() {
        ResponseEntity<List<ProgrammeMovieDTO>> response =
                restTemplate.exchange(baseUrl,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProgrammeMovieDTO>>() {
                        });

        List<ProgrammeMovieDTO> movies = response.getBody();

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(movies);
        Assert.assertEquals(10, movies.size());
    }

    @Test
    public void should_return_all_movies_by_genre() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("genre", "DRAMAT");

        ResponseEntity<List<ProgrammeMovieDTO>> response =
                restTemplate.exchange(builder.toUriString(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProgrammeMovieDTO>>() {
                        });

        List<Long> moviesIds = response.getBody().stream().map(ProgrammeMovieDTO::getId).collect(Collectors.toList());

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(CollectionUtils.isEqualCollection(Arrays.asList(1L, 2L, 5L, 10L), moviesIds));
    }

    @Test
    public void should_return_all_movies_by_country() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("country", "POLAND");

        ResponseEntity<List<ProgrammeMovieDTO>> response =
                restTemplate.exchange(builder.toUriString(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProgrammeMovieDTO>>() {
                        });

        List<Long> moviesIds = response.getBody().stream().map(ProgrammeMovieDTO::getId).collect(Collectors.toList());

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(Collections.singletonList(8L), moviesIds);
    }

    @Test
    public void should_return_all_movies_by_release_year() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("releaseYear", "1999");

        ResponseEntity<List<ProgrammeMovieDTO>> response =
                restTemplate.exchange(builder.toUriString(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProgrammeMovieDTO>>() {
                        });

        List<Long> moviesIds = response.getBody().stream().map(ProgrammeMovieDTO::getId).collect(Collectors.toList());

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(Collections.singletonList(2L), moviesIds);
    }

    @Test
    public void should_return_all_movies_by_genre_country_and_release_year() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("genre", "ANIMOWANY")
                .queryParam("country", "USA")
                .queryParam("releaseYear", "1994");

        ResponseEntity<List<ProgrammeMovieDTO>> response =
                restTemplate.exchange(builder.toUriString(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProgrammeMovieDTO>>() {
                        });

        List<Long> moviesIds = response.getBody().stream().map(ProgrammeMovieDTO::getId).collect(Collectors.toList());

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(Collections.singletonList(9L), moviesIds);
    }
}
