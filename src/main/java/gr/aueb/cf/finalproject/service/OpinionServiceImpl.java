package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.Hate;
import gr.aueb.cf.finalproject.model.Like;
import gr.aueb.cf.finalproject.model.MovieUser;
import gr.aueb.cf.finalproject.model.Movie;
import gr.aueb.cf.finalproject.repository.HateRepository;
import gr.aueb.cf.finalproject.repository.LikeRepository;
import gr.aueb.cf.finalproject.repository.MovieRepository;
import gr.aueb.cf.finalproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OpinionServiceImpl implements IOpinionService {

    private LikeRepository likeRepository;
    private HateRepository hateRepository;
    private UserRepository userRepository;
    private MovieRepository movieRepository;

    public int getMovieLikes(Long movieId) {
        Movie movie = MovieServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);
        return movie.getLikes();
    }

    public void addLike(Like like, Long userId, Long movieId) {
        MovieUser movieUser = UserServiceImpl.unwrapMovieUser(userRepository.findById(userId), userId);
        Movie movie = MovieServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);

        like.setMovieUserLiked(movieUser);
        like.setMovieLiked(movie);
        movie.setLikes(movie.getLikes() + 1);

        likeRepository.save(like);
    }

    public int getLikesByMovieAndMovieUser(Long movieId, Long userId) {
        MovieUser movieUser = UserServiceImpl.unwrapMovieUser(userRepository.findById(userId), userId);
        Movie movie = MovieServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);

        return likeRepository.countLikesByMovieLikedAndMovieUserLiked(movie, movieUser);
    }

    public void deleteLike(Long movieId, Long userId) {
        MovieUser movieUser = UserServiceImpl.unwrapMovieUser(userRepository.findById(userId), userId);
        Movie movie = MovieServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);

        movie.setLikes(movie.getLikes() - 1);
        likeRepository.deleteLikesByMovieLikedAndMovieUserLiked(movie, movieUser);
    }

    public int getHatesByMovieAndMovieUser(Long movieId, Long userId) {
        MovieUser movieUser = UserServiceImpl.unwrapMovieUser(userRepository.findById(userId), userId);
        Movie movie = MovieServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);

        return hateRepository.countHatesByMovieHatedAndMovieUserHated(movie, movieUser);
    }

    public void addHate(Hate hate, Long userId, Long movieId) {
        MovieUser movieUser = UserServiceImpl.unwrapMovieUser(userRepository.findById(userId), userId);
        Movie movie = MovieServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);

        hate.setMovieUserHated(movieUser);
        hate.setMovieHated(movie);
        movie.setHates(movie.getHates() + 1);

        hateRepository.save(hate);
    }

    public void deleteHate(Long userId, Long movieId) {
        MovieUser movieUser = UserServiceImpl.unwrapMovieUser(userRepository.findById(userId), userId);
        Movie movie = MovieServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);

        movie.setHates(movie.getHates() - 1);
        hateRepository.deleteHatesByMovieHatedAndMovieUserHated(movie, movieUser);
    }

    @Override
    public void setOpinion(boolean userOpinion, Long userId, Long movieId) {
        if (userOpinion) {
            Like like = new Like();

            if (getLikesByMovieAndMovieUser(movieId, userId) == 0) {
                addLike(like, userId, movieId);
            } else {
                deleteLike(movieId, userId);
            }

            if (getHatesByMovieAndMovieUser(movieId, userId) > 0) {
                deleteHate(userId, movieId);
            }
        } else {
            Hate hate = new Hate();

            if (getHatesByMovieAndMovieUser(movieId, userId) == 0) {
                addHate(hate, userId, movieId);
            } else {
                deleteHate(userId, movieId);
            }

            if (getLikesByMovieAndMovieUser(movieId, userId) > 0) {
                deleteLike(movieId, userId);
            }
        }
    }
}
