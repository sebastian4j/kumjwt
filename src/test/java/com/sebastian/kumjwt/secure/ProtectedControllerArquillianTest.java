package com.sebastian.kumjwt.secure;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import java.io.IOException;
import java.net.MalformedURLException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.sebastian.kumjwt.KumjwtRestApplication;
import com.sebastian.kumjwt.secure.clientes.ClienteKeycloak;

/**
 * test para la clase {@link ProtectedController}.
 *
 * @author Sebastian Avila A.
 *
 */
@RunWith(Arquillian.class)
public class ProtectedControllerArquillianTest {

  @Deployment
  public static JavaArchive createDeployment() {
    return ShrinkWrap.create(JavaArchive.class).addPackage(KumjwtRestApplication.class.getPackage())
        .addPackage(ProtectedController.class.getPackage()).addAsResource("config.yaml")
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Test
  @RunAsClient
  public void noEsPosibleAccederRecursoProtegido() {
    when().get("/data/protected").then().statusCode(401);
  }

  @Test
  @RunAsClient
  public void esPosibleAccederConHeaderCorrecto() throws MalformedURLException, IOException {
    given()
        .header("Authorization",
            "Bearer " + ClienteKeycloak.obtenerToken(System.getenv("usuario_keycloak"),
                System.getenv("clave_keycloak")))
        .when().get("/data/protected").then().statusCode(200);
  }

}
