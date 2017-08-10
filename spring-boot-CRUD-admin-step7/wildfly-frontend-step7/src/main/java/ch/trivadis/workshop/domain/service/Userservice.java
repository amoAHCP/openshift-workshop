package ch.trivadis.workshop.domain.service;

import ch.trivadis.workshop.domain.model.Person;
import io.fabric8.annotations.ServiceName;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.jacpfx.discovery.annotation.K8SDiscovery;

/**
 * Created by amo on 11.06.17.
 */
@K8SDiscovery(namespace = "myproject", user = "developer") //  if no user provided: oadm policy add-role-to-user view system:serviceaccount:myproject:default -n myproject
@ApplicationScoped
public class Userservice {

  @ServiceName("read-step7")
  private String readService;


  private final Client client = ClientBuilder.newClient();


  public Person get(String uuid) {
    final WebTarget myResource = client.target("http://"+ readService).path("/api/users/"+uuid);
    return myResource.request(MediaType.APPLICATION_JSON).get(Person.class);
  }



  public List<Person> getUsers() {
    final WebTarget myResource = client.target("http://" + readService).path("/api/users");
    return myResource.request(MediaType.APPLICATION_JSON)
        .get(new GenericType<List<Person>>() {
        });
  }


}
