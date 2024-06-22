package gr.aueb.cf.finalproject.controller;

import gr.aueb.cf.finalproject.service.MovieServiceImpl;
import gr.aueb.cf.finalproject.service.OpinionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/movieRest/opinion")
public class OpinionRestController {

    OpinionServiceImpl opinionService;
    MovieServiceImpl movieService;

    @PostMapping("/like/user/{userId}/movie/{movieId}")
    public ResponseEntity<String> likeMovie(@PathVariable Long userId, @PathVariable Long movieId) {
        opinionService.setOpinion(true, userId, movieId);

        if (opinionService.getLikesByMovieAndMovieUser(movieId, userId) == 0) {
            String messageUnlike = "You unliked the movie with title '" + movieService.getMovie(movieId).getTitle() + "'";
            return new ResponseEntity<>(messageUnlike, HttpStatus.CREATED);
        }

        String messageLike = "You liked the movie with title '" + movieService.getMovie(movieId).getTitle() + "'";

        return new ResponseEntity<>(messageLike, HttpStatus.CREATED);
    }

    @PostMapping("/dislike/user/{userId}/movie/{movieId}")
    public ResponseEntity<String> dislikeMovie(@PathVariable Long userId, @PathVariable Long movieId) {
        opinionService.setOpinion(false, userId, movieId);

        if (opinionService.getHatesByMovieAndMovieUser(movieId, userId) == 0) {
            String messageRemoveDislike = "You removed the dislike on the movie with title '" + movieService.getMovie(movieId).getTitle() + "'";
            return new ResponseEntity<>(messageRemoveDislike, HttpStatus.CREATED);
        }

        String messageDislike = "You disliked the movie with title '" + movieService.getMovie(movieId).getTitle() + "'";

        return new ResponseEntity<>(messageDislike, HttpStatus.CREATED);
    }
}
