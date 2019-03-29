package com.sebastian.kumjwt;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import static org.assertj.core.api.Assertions.assertThat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

/**
 * test para la clase {@link HelloController}.
 *
 * @author Sebastian Avila A.
 *
 */
public class HelloControllerTest extends JerseyTest {

  @Override
  protected Application configure() {
    return new ResourceConfig(HelloController.class);
  }

  @Test
  public void sayHelloTest() {
    Response rs = target("/hello").request().get();
    assertThat(rs.getStatus()).isEqualTo(200);
    assertThat(rs.readEntity(String.class)).isEqualTo("Hello World");
  }
}
