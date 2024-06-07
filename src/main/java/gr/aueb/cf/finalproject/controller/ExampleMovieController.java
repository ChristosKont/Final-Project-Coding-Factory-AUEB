package gr.aueb.cf.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleMovieController {

    // For test purposes!!
    @GetMapping("/movie-page")
    public String getMoviePage(Model model) {
        return "movie-page";
    }
}
