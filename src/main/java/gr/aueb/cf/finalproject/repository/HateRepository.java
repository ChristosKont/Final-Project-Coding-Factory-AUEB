package gr.aueb.cf.finalproject.repository;

import gr.aueb.cf.finalproject.model.Hate;
import gr.aueb.cf.finalproject.model.MovieUser;
import gr.aueb.cf.finalproject.model.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface HateRepository extends CrudRepository<Hate, Long> {
    int countHatesByMovieHatedAndMovieUserHated(Movie movie, MovieUser movieUser);
    @Transactional
    void deleteHatesByMovieHatedAndMovieUserHated(Movie movie, MovieUser movieUser);
}
