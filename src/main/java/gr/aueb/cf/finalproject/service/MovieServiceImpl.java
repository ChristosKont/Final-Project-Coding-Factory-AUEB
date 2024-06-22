package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.MovieUser;
import gr.aueb.cf.finalproject.model.Movie;
import gr.aueb.cf.finalproject.repository.MovieRepository;
import gr.aueb.cf.finalproject.repository.UserRepository;
import gr.aueb.cf.finalproject.service.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements IMovieService {

    private MovieRepository movieRepository;
    private UserRepository userRepository;

    @Override
    public Movie getMovie(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return unwrapMovie(movie, id);
    }

    @Override
    public Movie addMovie(Movie movie, Long userId) {

        Optional<MovieUser> movieUser = userRepository.findById(userId);
        MovieUser user = UserServiceImpl.unwrapMovieUser(movieUser, userId);
        movie.setMovieUser(user);

        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void updateMovie(Long movieId, Movie newMovie) {
        Movie movie = movieRepository.findById(movieId).get();

        movie.setTitle(newMovie.getTitle());
        movie.setDescription(newMovie.getDescription());
        movie.setType(newMovie.getType());
        movie.setTimestamp(newMovie.getTimestamp());
        movie.setDuration(newMovie.getDuration());
        movie.setDirector(newMovie.getDirector());

        movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> getMovies() {
        return (List<Movie>) movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByUser(Long userId) {
        return movieRepository.findByMovieUserId(userId);
    }

    @Override
    public List<Movie> getMoviesOrderByLikesDesc() { return movieRepository.findAllByOrderByLikesDesc(); }

    @Override
    public List<Movie> getMoviesOrderByHatesDesc() { return movieRepository.findAllByOrderByHatesDesc(); }

    @Override
    public List<Movie> getMoviesOrderByDateDesc() { return movieRepository.findAllByOrderByTimestampDesc(); }

    static Movie unwrapMovie(Optional<Movie> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Movie.class);
    }

}
