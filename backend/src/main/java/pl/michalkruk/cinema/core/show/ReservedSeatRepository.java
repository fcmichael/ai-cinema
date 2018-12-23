package pl.michalkruk.cinema.core.show;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, Long> {

    @Query("select rs.seat from ReservedSeat rs join rs.show s where s.id = :id")
    List<String> findSeatByShowId(Long id);
}
