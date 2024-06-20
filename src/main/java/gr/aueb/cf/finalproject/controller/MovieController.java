package gr.aueb.cf.finalproject.controller;

import gr.aueb.cf.finalproject.model.Movie;
import gr.aueb.cf.finalproject.service.*;
import gr.aueb.cf.finalproject.service.exceptions.ErrorMessageResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class MovieController {
    static Long id;

    MovieServiceImpl movieService;
    UserServiceImpl userService;
    OpinionServiceImpl opinionService;


    @GetMapping("/movies")
    public String getMoviePage(Model model, HttpServletRequest request) throws Exception {

        id = (Long) request.getSession().getAttribute("userId");

        if (id == null) throw new Exception("You Do Not Have Permission To Access!!");

        model.addAttribute("username", userService.getMovieUser(id).getUsername());
        model.addAttribute("movies", movieService.getMovies());
        model.addAttribute("movie", new Movie());
        model.addAttribute("userId", id);

        model.addAttribute("opinion", opinionService);

        return "movie-page";
    }

    @PostMapping("/addMovie")
    public String addMovie(Movie movie, RedirectAttributes redirectAttributes) {
        String title = movie.getTitle();

        if (movieService.getMovieByTitle(title).isPresent()) {
            ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse("This movie title " + title + " already exists.");
            redirectAttributes.addFlashAttribute("emailExistsError", true);
            redirectAttributes.addFlashAttribute("error", errorMessageResponse.toString());
            return "redirect:/movies";
        }

        movieService.addMovie(movie, id);

        return "redirect:/movies";
    }

    @GetMapping("/like/{movieId}")
    public String likeMovie(@PathVariable(value = "movieId") Long movieId) {
        opinionService.setOpinion(true, id, movieId);
        return "redirect:/movies";
    }

    @GetMapping("/hate/{movieId}")
    public String hateMovie(@PathVariable(value = "movieId") Long movieId) {
        opinionService.setOpinion(false, id, movieId);
        return "redirect:/movies";
    }

    @GetMapping("/movies/orderByLikes")
    public String moviesOrderByLikes(Model model) {
        model.addAttribute("username", userService.getMovieUser(id).getUsername());
        model.addAttribute("movies", movieService.getMoviesOrderByLikesDesc());
        model.addAttribute("movie", new Movie());
        model.addAttribute("userId",id);
        model.addAttribute("opinion", opinionService);
        return "movie-page";
    }

    @GetMapping("/movies/orderByDislikes")
    public String moviesOrderByHates(Model model) {
        model.addAttribute("username", userService.getMovieUser(id).getUsername());
        model.addAttribute("movies", movieService.getMoviesOrderByHatesDesc());
        model.addAttribute("movie", new Movie());
        model.addAttribute("userId",id);
        model.addAttribute("opinion", opinionService);
        return "movie-page";
    }

    @GetMapping("/movies/orderByDate")
    public String moviesOrderByDate(Model model) {
        model.addAttribute("username", userService.getMovieUser(id).getUsername());
        model.addAttribute("movies", movieService.getMoviesOrderByDateDesc());
        model.addAttribute("movie", new Movie());
        model.addAttribute("userId",id);
        model.addAttribute("opinion", opinionService);
        return "movie-page";
    }

    @GetMapping("/formUpdateMovie/{movieId}")
    public String getUpdateMoviePage(@PathVariable(value = "movieId") Long movieId, Model model) throws Exception {

        if (id == null) throw new Exception("You Do Not Have Permission To Access!!");

        Movie movie = movieService.getMovie(movieId);
        model.addAttribute("movie", movie);

        return "update-movie-page";
    }

    @PostMapping("/update/{movieId}")
    public String updateMovie(@PathVariable(value = "movieId") Long movieId, @Valid Movie movie) {
        movieService.updateMovie(movieId, movie);
        return "redirect:/movies";
    }

    @GetMapping("/delete/{movieId}")
    public String deleteMovie(@PathVariable(value = "movieId") Long movieId) {
        movieService.deleteMovie(movieId);
        return "redirect:/movies";
    }

    @GetMapping("/signOut")
    public String logout(HttpServletRequest request){
        try {
            request.logout();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }
}
