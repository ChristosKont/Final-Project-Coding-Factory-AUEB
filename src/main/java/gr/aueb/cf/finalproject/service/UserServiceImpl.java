package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.MovieUsers;
import gr.aueb.cf.finalproject.repository.UserRepository;
import gr.aueb.cf.finalproject.service.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public MovieUsers getMovieUser(Long id) {
        Optional<MovieUsers> movieUser = userRepository.findById(id);
        return unwrapMovieUser(movieUser, id);
    }

    @Override
    public MovieUsers addMovieUser(MovieUsers movieUser) {
        movieUser.setPassword(passwordEncoder.encode(movieUser.getPassword()));
        return userRepository.save(movieUser);
    }

    @Override
    public List<MovieUsers> getMovieUsers() {
        return (List<MovieUsers>) userRepository.findAll();
    }

    @Override
    public void deleteMovieUser(Long id){ userRepository.deleteById(id); }

    public static MovieUsers unwrapMovieUser(Optional<MovieUsers> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, MovieUsers.class);
    }
}
