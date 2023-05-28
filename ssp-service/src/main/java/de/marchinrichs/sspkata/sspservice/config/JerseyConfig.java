package de.marchinrichs.sspkata.sspservice.config;

import de.marchinrichs.sspkata.sspservice.bot.BotResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(BotResource.class);
    }
}
