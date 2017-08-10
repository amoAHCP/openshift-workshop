package ch.trivadis.workshop.api;


import ch.trivadis.workshop.domain.model.Person;
import ch.trivadis.workshop.domain.service.Userservice;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * Created by Andy Moncsek on 04.04.17.
 */
@ApplicationScoped
@Path("/users/{id}")
public class UserController {

  @Inject
  private Userservice userservice;


  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Person get(@PathParam("id") String uuid) {

    return userservice.get(uuid);
  }



}
