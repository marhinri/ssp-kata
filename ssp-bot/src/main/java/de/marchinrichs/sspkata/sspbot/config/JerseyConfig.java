package de.marchinrichs.sspkata.sspbot.config;

import de.marchinrichs.sspkata.sspbot.resource.RoundResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RoundResource.class);
    }
}
