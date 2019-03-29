package com.sebastian.kumjwt.secure.clientes;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 * test para la clase {@link ClienteKeycloak}.
 *
 * @author Sebastian Avila A.
 *
 */
public class ClienteKeycloakTest {

  @Test
  public void tokenObtenidoConDatosCorrectos() {
    assertThat(ClienteKeycloak.obtenerToken(System.getenv("usuario_keycloak"), System.getenv("clave_keycloak"))).isNotNull()
            .isNotEmpty();
  }
}
