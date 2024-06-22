package gr.aueb.cf.finalproject.controller;

import gr.aueb.cf.finalproject.dto.MovieDTO;
import gr.aueb.cf.finalproject.dto.MovieUserDTO;
import gr.aueb.cf.finalproject.model.Movie;
import gr.aueb.cf.finalproject.service.MovieServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/movieRest")
public class MovieRestController {

    MovieServiceImpl movieService;

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long movieId) {
        Movie movie = movieService.getMovie(movieId);
        MovieUserDTO userDTO = new MovieUserDTO(movie.getMovieUser().getUsername(), movie.getMovieUser().getEmail(), movie.getMovieUser().getFirstName(), movie.getMovieUser().getLastName(), movie.getMovieUser().getBirthDate());
        MovieDTO movieDTO = new MovieDTO(movie.getTitle(), movie.getDescription(), movie.getType(), movie.getDuration(), movie.getDirector(), userDTO);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @PostMapping("/addMovie/user/{id}")
    public ResponseEntity<MovieDTO> saveMovieUser(@PathVariable Long id, @RequestBody Movie movie) {
        Movie movieToBeAdded = movieService.addMovie(movie, id);
        MovieUserDTO userDTO = new MovieUserDTO(movieToBeAdded.getMovieUser().getUsername(), movieToBeAdded.getMovieUser().getEmail(), movieToBeAdded.getMovieUser().getFirstName(), movieToBeAdded.getMovieUser().getLastName(), movieToBeAdded.getMovieUser().getBirthDate());
        MovieDTO movieDTO = new MovieDTO(movieToBeAdded.getTitle(), movieToBeAdded.getDescription(), movieToBeAdded.getType(), movieToBeAdded.getDuration(), movieToBeAdded.getDirector(), userDTO);
        return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Movie>> getMoviesByUser(@PathVariable Long id) {
        return new ResponseEntity<>(movieService.getMoviesByUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<HttpStatus> deleteMovieUser(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{movieId}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long movieId, @RequestBody Movie movie) {
        movieService.updateMovie(movieId, movie);
        Movie movieUpdated = movieService.getMovie(movieId);
        MovieUserDTO userDTO = new MovieUserDTO(movieUpdated.getMovieUser().getUsername(), movieUpdated.getMovieUser().getEmail(), movieUpdated.getMovieUser().getFirstName(), movieUpdated.getMovieUser().getLastName(), movieUpdated.getMovieUser().getBirthDate());
        MovieDTO movieDTO = new MovieDTO(movieUpdated.getTitle(), movieUpdated.getDescription(), movieUpdated.getType(), movieUpdated.getDuration(), movieUpdated.getDirector(), userDTO);

        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<>(movieService.getMovies(), HttpStatus.OK);
    }
}
