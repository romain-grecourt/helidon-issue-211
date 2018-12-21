package io.helidon.bugs.mpapp;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.helidon.common.CollectionsHelper;

/**
 * 
 * @author rgrecour
 */
@ApplicationScoped
@ApplicationPath("/")
public class HelloApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return CollectionsHelper.setOf(HelloResource.class);
    }
}
