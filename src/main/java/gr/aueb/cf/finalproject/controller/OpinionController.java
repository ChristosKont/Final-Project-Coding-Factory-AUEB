package gr.aueb.cf.finalproject.controller;

import gr.aueb.cf.finalproject.model.Hates;
import gr.aueb.cf.finalproject.model.Likes;
import gr.aueb.cf.finalproject.service.HateServiceImpl;
import gr.aueb.cf.finalproject.service.LikeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/opinion")
public class OpinionController {

    private LikeServiceImpl likeService;
    private HateServiceImpl hateService;

    @PostMapping("/user/{userId}/movie/{movieId}")
    public ResponseEntity<String> userOpinion(@PathVariable Long userId, @PathVariable Long movieId, @RequestBody String opinion) {

        Likes like = new Likes();
        Hates hate = new Hates();

        if (opinion.equals("like")) {

            if (likeService.getLikesByMovieAndMovieUser(movieId, userId) == 0) {
                likeService.addLike(like, userId, movieId);
            }

            if (hateService.getHatesByMovieAndMovieUser(movieId, userId) > 0) {
                hateService.deleteHate(userId, movieId);
            }

            return new ResponseEntity<>("You liked the movie!!", HttpStatus.OK);

        } else {

            if (hateService.getHatesByMovieAndMovieUser(movieId, userId) == 0) {
                hateService.addHate(hate, userId, movieId);
            }

            if (likeService.getLikesByMovieAndMovieUser(movieId, userId) > 0) {
                likeService.deleteLike(movieId, userId);
            }

            return new ResponseEntity<>("You disliked the movie!!", HttpStatus.OK);
        }
    }
}
