package com.sebastian.kumjwt;

import com.kumuluz.ee.jwt.auth.feature.JWTRolesAllowedDynamicFeature;
import com.kumuluz.ee.jwt.auth.filter.JWTAuthorizationFilter;
import com.sebastian.kumjwt.secure.ProtectedController;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

/**
 *
 */
@ApplicationPath("/data")

@LoginConfig(authMethod = "MP-JWT")
@DeclareRoles({"administracion"})

public class KumjwtRestApplication extends Application {

  @Override
  public Set<Class<?>> getClasses() {

    Set<Class<?>> classes = new HashSet<>();

    // microprofile jwt auth filters
    classes.add(JWTAuthorizationFilter.class);
    classes.add(JWTRolesAllowedDynamicFeature.class);

    // resources
    classes.add(HelloController.class);

    classes.add(ProtectedController.class);

    return classes;
  }

}
