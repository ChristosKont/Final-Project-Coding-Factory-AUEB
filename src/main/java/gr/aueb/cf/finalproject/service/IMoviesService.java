package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.Movies;

import java.util.List;

public interface IMoviesService {
    Movies getMovie(Long id);
    Movies addMovie(Movies movie, Long userId);
    void deleteMovie(Long id);
    List<Movies> getMovies();
    List<Movies> getMoviesByUser(Long userId);
}
