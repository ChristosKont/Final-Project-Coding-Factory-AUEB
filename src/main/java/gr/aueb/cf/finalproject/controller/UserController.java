package gr.aueb.cf.finalproject.controller;

import gr.aueb.cf.finalproject.dto.LoginMovieUserDTO;
import gr.aueb.cf.finalproject.model.MovieUser;
import gr.aueb.cf.finalproject.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@AllArgsConstructor
public class UserController {

    UserServiceImpl userService;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("movieUser", new MovieUser());
        model.addAttribute("loginMovieUser", new LoginMovieUserDTO());
        return "index";
    }

    @GetMapping("/about-us")
    public String getAboutUsPage() {
        return "about-us-page";
    }

    @PostMapping("/registerUser")
    public String registerUser(MovieUser movieUser, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        userService.addMovieUser(movieUser);
        redirectAttributes.addFlashAttribute("register", true);
        redirectAttributes.addFlashAttribute("msgReg", "You have successfully registered to our website!!");

        return "redirect:/";
    }

    @PostMapping("/loginUser")
    public String loginUser(LoginMovieUserDTO movieUser, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        MovieUser loggedInUser = userService.findMovieUserByUsername(movieUser.getUsername(), movieUser.getPassword());

        redirectAttributes.addFlashAttribute("userId", loggedInUser.getId());
        request.getSession().setAttribute("userId",loggedInUser.getId());

        return "redirect:/movies";
    }
}
