package ch.trivadis.workshop;

import ch.trivadis.workshop.entity.Person;
import ch.trivadis.workshop.repository.ReactiveUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootApplication
@ComponentScan
@EnableConfigurationProperties({MongoProperties.class})
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class FrontendApplication {

  public static void main(String[] args) {
    SpringApplication.run(FrontendApplication.class, args);
  }

  @Bean
  WebClient webclient() {
    return WebClient.create();
  }


  @Bean
  CommandLineRunner initData(ReactiveUserRepository personRepository) {
    Flux<Person> people = Flux.just(
        new Person("1", "Eric", "Foo", "Zh"),
        new Person("2", "Raymond", "Bar", "B"),
        new Person("3", "Paul", "Baz", "x")
    );

    return args -> {
      personRepository.deleteAll().thenMany(personRepository.saveAll(people)).blockLast();
    };

  }

}
