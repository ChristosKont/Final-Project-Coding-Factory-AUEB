package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.Likes;
import gr.aueb.cf.finalproject.model.MovieUsers;
import gr.aueb.cf.finalproject.model.Movies;
import gr.aueb.cf.finalproject.repository.LikeRepository;
import gr.aueb.cf.finalproject.repository.MovieRepository;
import gr.aueb.cf.finalproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements ILikeService{

    private LikeRepository likeRepository;
    private UserRepository userRepository;
    private MovieRepository movieRepository;

//    @Override
//    public List<Likes> getLikesByUserId(Long userId) {
//        return null;
//    }
//
//    @Override
//    public List<Likes> getLikesByMovieId(Long movieId) {
//        return null;
//    }

    @Override
    public int getLikesByMovieAndMovieUser(Long movieId, Long userId) {
        MovieUsers movieUser = UserServiceImpl.unwrapMovieUser(userRepository.findById(userId), userId);
        Movies movie = MoviesServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);

        return likeRepository.countLikesByMovieLikedAndMovieUserLiked(movie, movieUser);
    }

    @Override
    public void addLike(Likes like, Long userId, Long movieId) {
        MovieUsers movieUser = UserServiceImpl.unwrapMovieUser(userRepository.findById(userId), userId);
        Movies movie = MoviesServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);

        like.setMovieUserLiked(movieUser);
        like.setMovieLiked(movie);
        movie.setLikes(movie.getLikes() + 1);

        likeRepository.save(like);
    }

    @Override
    public void deleteLike(Long movieId, Long userId) {
        MovieUsers movieUser = UserServiceImpl.unwrapMovieUser(userRepository.findById(userId), userId);
        Movies movie = MoviesServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);

        movie.setLikes(movie.getLikes() - 1);
        likeRepository.deleteLikesByMovieLikedAndMovieUserLiked(movie, movieUser);
    }
}
