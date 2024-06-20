package gr.aueb.cf.finalproject.repository;

import gr.aueb.cf.finalproject.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findByMovieUserId(Long userId);
    Optional<Movie> findByTitle(String title);
    List<Movie> findAllByOrderByLikesDesc();
    List<Movie> findAllByOrderByHatesDesc();
    List<Movie> findAllByOrderByTimestampDesc();
}
