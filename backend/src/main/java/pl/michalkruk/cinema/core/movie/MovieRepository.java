package pl.michalkruk.cinema.core.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m ORDER BY m.id")
    List<Movie> findAllOrderById();
}
