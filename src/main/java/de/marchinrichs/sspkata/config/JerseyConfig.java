package de.marchinrichs.sspkata.config;

import de.marchinrichs.sspkata.bot.BotResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(BotResource.class);
    }
}
