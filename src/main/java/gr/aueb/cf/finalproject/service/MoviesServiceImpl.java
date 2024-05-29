package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.MovieUsers;
import gr.aueb.cf.finalproject.model.Movies;
import gr.aueb.cf.finalproject.repository.MovieRepository;
import gr.aueb.cf.finalproject.repository.UserRepository;
import gr.aueb.cf.finalproject.service.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MoviesServiceImpl implements IMoviesService{

    private MovieRepository movieRepository;
    private UserRepository userRepository;

    @Override
    public Movies getMovie(Long id) {
        Optional<Movies> movie = movieRepository.findById(id);
        return unwrapMovie(movie, id);
    }

    @Override
    public Movies addMovie(Movies movie, Long userId) {
        Optional<MovieUsers> movieUser = userRepository.findById(userId);
        MovieUsers user = UserServiceImpl.unwrapMovieUser(movieUser, userId);
        movie.setMovieUser(user);
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movies> getMovies() {
        return (List<Movies>) movieRepository.findAll();
    }

    @Override
    public List<Movies> getMoviesByUser(Long userId) {
        return movieRepository.findByMovieUserId(userId);
    }

    static Movies unwrapMovie(Optional<Movies> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Movies.class);
    }
}
