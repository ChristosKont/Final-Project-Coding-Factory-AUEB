package gr.aueb.cf.finalproject.repository;

import gr.aueb.cf.finalproject.model.Likes;
import gr.aueb.cf.finalproject.model.MovieUsers;
import gr.aueb.cf.finalproject.model.Movies;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LikeRepository extends CrudRepository<Likes, Long> {
    int countLikesByMovieLikedAndMovieUserLiked(Movies movie, MovieUsers movieUser);
    @Transactional
    void deleteLikesByMovieLikedAndMovieUserLiked(Movies movie, MovieUsers movieUser);
}
