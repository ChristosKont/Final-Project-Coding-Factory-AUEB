package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.Likes;

import java.util.List;

public interface ILikeService {
//    List<Likes> getLikesByUserId(Long userId);
//    List<Likes> getLikesByMovieId(Long movieId);
    void addLike(Likes like, Long userId, Long movieId);
    void deleteLike(Long movieId, Long userId);

    int getLikesByMovieAndMovieUser(Long movieId, Long userId);
}
