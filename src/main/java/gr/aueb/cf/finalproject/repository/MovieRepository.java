package gr.aueb.cf.finalproject.repository;

import gr.aueb.cf.finalproject.model.Movies;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movies, Long> {

    List<Movies> findByMovieUserId(Long userId);
}
