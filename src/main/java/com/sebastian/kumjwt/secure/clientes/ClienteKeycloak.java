package com.sebastian.kumjwt.secure.clientes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;
import org.jboss.weld.exceptions.IllegalStateException;

/**
 * cliente para acceder a keycloak.
 *
 * @author Sebastian Avila A.
 *
 */
public final class ClienteKeycloak {

  private ClienteKeycloak() {

  }

  public static String obtenerToken(final String usuario, final String clave) {
    String token;
    try {
      final URLConnection con = new URL(System.getenv("url_keycloak")).openConnection();
      con.setDoOutput(true);
      try (final PrintWriter ps = new PrintWriter(con.getOutputStream())) {
        ps.write(new StringBuilder().append("grant_type=password&client_id=cliente-mp&username=")
            .append(usuario).append("&password=").append(clave).toString());
      }
      final String result = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
          .collect(Collectors.joining(""));
      token = result.split(":")[1].split("\",\"")[0].substring(1);
    } catch (final IOException e) {
      throw new IllegalStateException("error al obtener token", e);
    }
    return token;
  }
}
