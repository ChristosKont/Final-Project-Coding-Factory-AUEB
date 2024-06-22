package gr.aueb.cf.finalproject.repository;

import gr.aueb.cf.finalproject.model.MovieUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<MovieUser, Long> {
    Optional<MovieUser> findByUsername(String username);
    Optional<MovieUser> findByEmail(String email);
}
