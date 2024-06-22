package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.Movie;

import java.util.List;
import java.util.Optional;
public interface IMovieService {
    Movie getMovie(Long id);
    Movie addMovie(Movie movie, Long userId);
    void deleteMovie(Long id);
    void updateMovie(Long movieId, Movie newMovie);
    Optional<Movie> getMovieByTitle(String title);
    List<Movie> getMovies();
    List<Movie> getMoviesByUser(Long userId);
    List<Movie> getMoviesOrderByLikesDesc();
    List<Movie> getMoviesOrderByHatesDesc();
    List<Movie> getMoviesOrderByDateDesc();
}
