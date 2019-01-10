package pl.michalkruk.cinema.core.movie;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:integration-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class MovieControllerTest {

    private final String baseUrl = "/movies";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void should_return_movie_by_id() {
        // given
        Long id = 1L;
        String url = baseUrl + "/" + id;

        // when
        ResponseEntity<MovieDTO> response =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, MovieDTO.class);

        MovieDTO movie = response.getBody();

        // then
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(movie);
        Assert.assertEquals("Skazani na Shawshank", movie.getTitle());
        Assert.assertEquals("DRAMA", movie.getGenre());
        Assert.assertEquals("MIN_15", movie.getAgeLimit());
        Assert.assertEquals(new Short("142"), movie.getDuration());
        Assert.assertEquals(new Short("1994"), movie.getReleaseYear());
        Assert.assertEquals("USA", movie.getCountry());
    }

    @Test
    public void should_update_movie_by_id() {
        // given
        Long id = 1L;
        String url = baseUrl + "/" + id;
        int moviesCountBefore = movieRepository.findAll().size();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", obtainAccessToken());

        MovieForm form = new MovieForm("Tytuł", Genre.ACTION, AgeLimit.B_O, (short) 1, (short) 1, Country.POLAND, "Opis");
        HttpEntity<MovieForm> request = new HttpEntity<>(form, headers);

        // when
        ResponseEntity<MovieDTO> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, MovieDTO.class);
        MovieDTO movie = responseEntity.getBody();

        // then
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(moviesCountBefore, movieRepository.findAll().size());
        Assert.assertNotNull(movie);
        Assert.assertEquals(form.getTitle(), movie.getTitle());
        Assert.assertEquals(form.getGenre().toString(), movie.getGenre());
        Assert.assertEquals(form.getAgeLimit().toString(), movie.getAgeLimit());
        Assert.assertEquals((Short) form.getDuration(), movie.getDuration());
        Assert.assertEquals((Short) form.getReleaseYear(), movie.getReleaseYear());
        Assert.assertEquals(form.getDescription(), movie.getDescription());
    }

    private String obtainAccessToken() {
        String username = "test";
        String password = "test";
        String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";

        ResponseEntity<Object> exchange = restTemplate.exchange
                ("/login", HttpMethod.POST, new HttpEntity<>(body), Object.class);

        return exchange.getHeaders().get("Authorization").get(0);
    }

}
