package gr.aueb.cf.finalproject.repository;

import gr.aueb.cf.finalproject.model.MovieUsers;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<MovieUsers, Long> {
}
