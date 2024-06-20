package gr.aueb.cf.finalproject.repository;

import gr.aueb.cf.finalproject.model.Like;
import gr.aueb.cf.finalproject.model.MovieUser;
import gr.aueb.cf.finalproject.model.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LikeRepository extends CrudRepository<Like, Long> {
    int countLikesByMovieLikedAndMovieUserLiked(Movie movie, MovieUser movieUser);
    Optional<Like> getLikesByMovieUserLiked_Id(Long userId);
    @Transactional
    void deleteLikesByMovieLikedAndMovieUserLiked(Movie movie, MovieUser movieUser);
}
