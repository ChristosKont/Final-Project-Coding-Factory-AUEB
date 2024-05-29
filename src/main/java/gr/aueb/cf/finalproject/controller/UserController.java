package gr.aueb.cf.finalproject.controller;

import gr.aueb.cf.finalproject.dto.MovieUserDTO;
import gr.aueb.cf.finalproject.model.MovieUsers;
import gr.aueb.cf.finalproject.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<MovieUsers> getMovieUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getMovieUser(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieUserDTO> saveMovieUser(@Valid @RequestBody MovieUsers movieUser) {
        MovieUsers user = userService.addMovieUser(movieUser);
        return new ResponseEntity<>(new MovieUserDTO(user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getBirthDate()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMovieUser(@PathVariable Long id) {
        userService.deleteMovieUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieUsers>> getAllMovieUsers() {
        return new ResponseEntity<>(userService.getMovieUsers(), HttpStatus.OK);
    }
}
