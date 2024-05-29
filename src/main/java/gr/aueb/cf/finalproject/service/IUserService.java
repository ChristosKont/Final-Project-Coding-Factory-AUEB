package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.MovieUsers;

import java.util.List;

public interface IUserService {

    MovieUsers getMovieUser(Long id);
    MovieUsers addMovieUser(MovieUsers movieUsers);
    List<MovieUsers> getMovieUsers();
    void deleteMovieUser(Long id);
}
