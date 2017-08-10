package ch.trivadis.workshop.controller;

import ch.trivadis.workshop.entity.Person;
import ch.trivadis.workshop.repository.ReactiveUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Created by Andy Moncsek on 04.04.17.
 */
@RestController
@RequestMapping("/api")
public class FrontendController {

  @Autowired
  private ReactiveUserRepository repository;


  @GetMapping(path = "/users/{id}")
  public Mono<Person> get(@PathVariable("id") String uuid) {
    return repository.findById(uuid);
  }

  @GetMapping(path = "/users")
  public Flux<Person> getUsers() {
    return repository.findAll();
  }

  @PostMapping(path = "/users")
  public Mono<Person> createUser(@RequestBody Person person) {
    return repository.save(person);
  }

  @PutMapping(path = "/users/{id}")
  public Mono<Person> updateUser(@RequestBody Person person) {
    return repository.save(person);
  }

  @DeleteMapping(path = "/users/{id}")
  public void deleteUser(@PathVariable("id") String uuid) {
    repository.deleteById(p -> p.onNext(uuid));
  }
}
