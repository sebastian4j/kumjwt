package com.sebastian.kumjwt.secure;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;

/**
 *
 */
@Path("/protected")
@RequestScoped
public class ProtectedController {

  @Inject
  @Claim("atributo-clave")
  private ClaimValue<String> custom;

  @GET
  @RolesAllowed("administracion")
  public String getJWTBasedValue() {
    return "Protected Resource; Custom value : " + custom.getValue();
  }
}
