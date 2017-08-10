package ch.trivadis.workshop.repository;

import ch.trivadis.workshop.entity.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * Created by Andy Moncsek on 22.06.17.
 */

public interface ReactiveUserRepository extends ReactiveCrudRepository<Person, String> {

}
