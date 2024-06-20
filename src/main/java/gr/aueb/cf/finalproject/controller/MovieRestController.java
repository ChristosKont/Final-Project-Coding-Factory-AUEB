package gr.aueb.cf.finalproject.controller;

import gr.aueb.cf.finalproject.dto.MovieDTO;
import gr.aueb.cf.finalproject.dto.MovieUserDTO;
import gr.aueb.cf.finalproject.model.Movie;
import gr.aueb.cf.finalproject.service.MovieServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieRestController {

   MovieServiceImpl moviesService;

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieUser(@PathVariable Long id) {
        return new ResponseEntity<>(moviesService.getMovie(id), HttpStatus.OK);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<MovieDTO> saveMovieUser(@PathVariable Long id, @Valid @RequestBody Movie movie) {
        Movie movieToBeAdded = moviesService.addMovie(movie, id);
        MovieUserDTO userDTO = new MovieUserDTO(movieToBeAdded.getMovieUser().getUsername(), movieToBeAdded.getMovieUser().getEmail(), movieToBeAdded.getMovieUser().getFirstName(), movieToBeAdded.getMovieUser().getLastName(), movieToBeAdded.getMovieUser().getBirthDate());
        MovieDTO movieDTO = new MovieDTO(movieToBeAdded.getTitle(), movieToBeAdded.getDescription(), movieToBeAdded.getType(), movieToBeAdded.getDuration(), movieToBeAdded.getDirector(), userDTO);
        return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMovieUser(@PathVariable Long id) {
        moviesService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovieUsers() {
        return new ResponseEntity<>(moviesService.getMovies(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Movie>> getMoviesByUser(@PathVariable Long id) {
        return new ResponseEntity<>(moviesService.getMoviesByUser(id), HttpStatus.OK);
    }
}
