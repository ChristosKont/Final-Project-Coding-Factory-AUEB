package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.MovieUser;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    MovieUser getMovieUser(Long id);
    MovieUser addMovieUser(MovieUser movieUsers);
    Optional<MovieUser> getMovieUserByUsername(String username);
    List<MovieUser> getMovieUsers();
    void deleteMovieUser(Long id);
    MovieUser findMovieUserByUsername(String username, String password);
}
