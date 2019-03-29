package com.sebastian.kumjwt;

import com.sebastian.kumjwt.secure.ProtectedController;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * test para la clase {@link ProtectedController}.
 *
 * @author Sebastian Avila A.
 *
 */
@RunWith(Arquillian.class)
public class HelloControllerArquillianTest {

  @Deployment
  public static JavaArchive createDeployment() {
    return ShrinkWrap.create(JavaArchive.class)
            .addPackages(true, KumjwtRestApplication.class.getPackage()).addAsResource("config.yaml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Test
  @RunAsClient
  public void sayHelloTest() {
    when().get("/data/hello").then().statusCode(200).and().body(is("Hello World"));
  }
}
