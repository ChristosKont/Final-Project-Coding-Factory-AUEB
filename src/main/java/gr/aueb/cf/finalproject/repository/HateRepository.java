package gr.aueb.cf.finalproject.repository;

import gr.aueb.cf.finalproject.model.Hates;
import gr.aueb.cf.finalproject.model.MovieUsers;
import gr.aueb.cf.finalproject.model.Movies;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface HateRepository extends CrudRepository<Hates, Long> {
    int countHatesByMovieHatedAndMovieUserHated(Movies movie, MovieUsers movieUser);
    @Transactional
    void deleteHatesByMovieHatedAndMovieUserHated(Movies movie, MovieUsers movieUser);
}
