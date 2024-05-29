package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.Hates;
import gr.aueb.cf.finalproject.model.MovieUsers;
import gr.aueb.cf.finalproject.model.Movies;
import gr.aueb.cf.finalproject.repository.HateRepository;
import gr.aueb.cf.finalproject.repository.MovieRepository;
import gr.aueb.cf.finalproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HateServiceImpl implements IHateService {

    private HateRepository hateRepository;
    private UserRepository userRepository;
    private MovieRepository movieRepository;

//    @Override
//    public List<Hates> getHatesByUserId(Long userId) {
//        return null;
//    }
//
//    @Override
//    public List<Hates> getHatesByMovieId(Long movieId) {
//        return null;
//    }


    @Override
    public int getHatesByMovieAndMovieUser(Long movieId, Long userId) {
        MovieUsers movieUser = UserServiceImpl.unwrapMovieUser(userRepository.findById(userId), userId);
        Movies movie = MoviesServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);

        return hateRepository.countHatesByMovieHatedAndMovieUserHated(movie, movieUser);
    }

    @Override
    public void addHate(Hates hate, Long userId, Long movieId) {
        MovieUsers movieUser = UserServiceImpl.unwrapMovieUser(userRepository.findById(userId), userId);
        Movies movie = MoviesServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);

        hate.setMovieUserHated(movieUser);
        hate.setMovieHated(movie);
        movie.setHates(movie.getHates() + 1);

        hateRepository.save(hate);
    }

    @Override
    public void deleteHate(Long userId, Long movieId) {
        MovieUsers movieUser = UserServiceImpl.unwrapMovieUser(userRepository.findById(userId), userId);
        Movies movie = MoviesServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);

        movie.setHates(movie.getHates() - 1);
        hateRepository.deleteHatesByMovieHatedAndMovieUserHated(movie, movieUser);
    }
}
