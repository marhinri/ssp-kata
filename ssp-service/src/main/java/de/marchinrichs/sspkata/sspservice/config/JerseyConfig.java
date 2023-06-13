package de.marchinrichs.sspkata.sspservice.config;

import de.marchinrichs.sspkata.sspservice.controller.BotController;
import de.marchinrichs.sspkata.sspservice.controller.GameController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(BotController.class);
        register(GameController.class);
    }
}
