package pl.michalkruk.cinema.core.movie;

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

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:integration-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class MovieControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_return_all_movies() {
        ResponseEntity<List<MovieDTO>> response =
                restTemplate.exchange("/movie",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<MovieDTO>>() {
                        });

        List<MovieDTO> movies = response.getBody();

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(movies);
        Assert.assertEquals(1, movies.size());
    }
}
