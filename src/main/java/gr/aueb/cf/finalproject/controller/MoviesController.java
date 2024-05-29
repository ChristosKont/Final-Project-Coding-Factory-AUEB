package gr.aueb.cf.finalproject.controller;

import gr.aueb.cf.finalproject.dto.MovieDTO;
import gr.aueb.cf.finalproject.dto.MovieUserDTO;
import gr.aueb.cf.finalproject.model.Movies;
import gr.aueb.cf.finalproject.service.MoviesServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/movie")
public class MoviesController {

   MoviesServiceImpl moviesService;

    @GetMapping("/{id}")
    public ResponseEntity<Movies> getMovieUser(@PathVariable Long id) {
        return new ResponseEntity<>(moviesService.getMovie(id), HttpStatus.OK);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<MovieDTO> saveMovieUser(@PathVariable Long id, @Valid @RequestBody Movies movie) {
        Movies movieToBeAdded = moviesService.addMovie(movie, id);
        MovieUserDTO userDTO = new MovieUserDTO(movieToBeAdded.getMovieUser().getUsername(), movieToBeAdded.getMovieUser().getEmail(), movieToBeAdded.getMovieUser().getFirstName(), movieToBeAdded.getMovieUser().getLastName(), movieToBeAdded.getMovieUser().getBirthDate());
        MovieDTO movieDTO = new MovieDTO(movieToBeAdded.getTitle(), movieToBeAdded.getDescription(), movieToBeAdded.getType(), movieToBeAdded.getDuration(), movieToBeAdded.getDirector(), movieToBeAdded.getTimestamp(), movieToBeAdded.getLikes(), movieToBeAdded.getHates(), userDTO);
        return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMovieUser(@PathVariable Long id) {
        moviesService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movies>> getAllMovieUsers() {
        return new ResponseEntity<>(moviesService.getMovies(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Movies>> getMoviesByUser(@PathVariable Long id) {
        return new ResponseEntity<>(moviesService.getMoviesByUser(id), HttpStatus.OK);
    }
}
