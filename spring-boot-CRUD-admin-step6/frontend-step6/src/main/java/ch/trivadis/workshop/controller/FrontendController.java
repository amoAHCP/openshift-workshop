package ch.trivadis.workshop.controller;

import ch.trivadis.workshop.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Created by Andy Moncsek on 04.04.17.
 */
@RestController
@RequestMapping("/api")
public class FrontendController {

  @Autowired
  private WebClient client;

  private @Value("${read.service}")
  String readHost;

  private @Value("${read.service.port}")
  String readPort;

  private @Value("${write.service}")
  String writeHost;

  private @Value("${write.service.port}")
  String writePort;


  @GetMapping(path = "/users/{id}")
  public Mono<Person> get(@PathVariable("id") String uuid) {
    return this.client
        .get().uri(getReadURL() + "/api/users/", uuid)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .flatMap(resp -> resp.bodyToMono(Person.class));
  }

  @GetMapping(path = "/users")
  public Flux<Person> getUsers() {
    return this.client
        .get().uri(getReadURL() + "/api/users")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .flatMapMany(response -> response.bodyToFlux(Person.class));
  }

  @PostMapping(path = "/users")
  public Mono<Person> createUser(@RequestBody Person person) {
    return this.client
        .post().uri(getWriteURL() + "/api/users")
        .accept(MediaType.APPLICATION_JSON)
        .syncBody(person)
        .exchange()
        .flatMap(resp -> resp.bodyToMono(Person.class));
  }

  @PutMapping(path = "/users/{id}")
  public Mono<Person> updateUser(@RequestBody Person person) {
    return this.client
        .put().uri(getWriteURL() + "/api/" + person.getId())
        .accept(MediaType.APPLICATION_JSON)
        .syncBody(person)
        .exchange()
        .flatMap(resp -> resp.bodyToMono(Person.class));
  }

  @DeleteMapping(path = "/users/{id}")
  public void deleteUser(@PathVariable("id") String uuid) {
    this.client
        .delete().uri(getWriteURL() + "/api/" + uuid)
        .accept(MediaType.APPLICATION_JSON)
        .exchange();
  }


  /**
   * Returns the URL for the "read" application using environmental variables
   */
  private String getReadURL() {
    return System.getenv(readHost)!=null?"http://" + System.getenv(readHost) + ":" + System
        .getenv(readPort):"http://"+readHost+":"+readPort;
  }

  /**
   * Returns the URL for the "write" application using environmental variables
   */
  private String getWriteURL() {
    return System.getenv(writeHost)!=null?"http://" + System.getenv(writeHost) + ":" + System
        .getenv(writePort):"http://"+writeHost+":"+writePort;
  }
}
