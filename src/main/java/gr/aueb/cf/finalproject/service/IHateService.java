package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.Hates;

import java.util.List;

public interface IHateService {
//    List<Hates> getHatesByUserId(Long userId);
//    List<Hates> getHatesByMovieId(Long movieId);
    void addHate(Hates hate, Long userId, Long movieId);
    int getHatesByMovieAndMovieUser(Long movieId, Long userId);
    void deleteHate(Long userId, Long movieId);
}
