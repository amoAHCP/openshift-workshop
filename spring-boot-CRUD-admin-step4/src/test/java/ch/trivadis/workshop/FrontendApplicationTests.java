package ch.trivadis.workshop;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class FrontendApplicationTests {


  @Autowired
  WebClient client;


  @Test
  @Ignore
  public void contextLoads() {
    assertNotNull(client);
  }

}
