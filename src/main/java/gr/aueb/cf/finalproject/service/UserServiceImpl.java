package gr.aueb.cf.finalproject.service;

import gr.aueb.cf.finalproject.model.MovieUser;
import gr.aueb.cf.finalproject.model.Role;
import gr.aueb.cf.finalproject.repository.UserRepository;
import gr.aueb.cf.finalproject.service.exceptions.EmailAlreadyExistsException;
import gr.aueb.cf.finalproject.service.exceptions.UsernameAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
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
    public MovieUser getMovieUser(Long id) {
        Optional<MovieUser> movieUser = userRepository.findById(id);
        return unwrapMovieUser(movieUser, id);
    }

    @Override
    public Optional<MovieUser> getMovieUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public MovieUser addMovieUser(MovieUser movieUser) {
        if (userRepository.findByUsername(movieUser.getUsername()).isPresent()) throw new UsernameAlreadyExistsException(movieUser.getUsername());
        if (userRepository.findByEmail(movieUser.getEmail()).isPresent()) throw new EmailAlreadyExistsException(movieUser.getEmail());
        if (movieUser.getUsername().equals("admin") || movieUser.getUsername().equals("ADMIN")) { movieUser.setRole(Role.ADMIN);}
        movieUser.setPassword(passwordEncoder.encode(movieUser.getPassword()));

        return userRepository.save(movieUser);
    }

    @Override
    public List<MovieUser> getMovieUsers() {
        return (List<MovieUser>) userRepository.findAll();
    }

    @Override
    public void deleteMovieUser(Long id){ userRepository.deleteById(id); }

    @Override
    public MovieUser findMovieUserByUsername(String username, String password) {
        Optional<MovieUser> user = userRepository.findByUsername(username);
        MovieUser movieUser = unwrapMovieUser(user,404l);

        if (!passwordEncoder.matches(password, movieUser.getPassword())) {
            throw new BadCredentialsException("You provided incorrect username or password");
        }
        return movieUser;
    }

    public static MovieUser unwrapMovieUser(Optional<MovieUser> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new BadCredentialsException("You provided incorrect username or password");
    }
}
