package de.marchinrichs.sspkata.sspbot.init;

import de.marchinrichs.sspkata.sspapi.model.bot.BotId;
import de.marchinrichs.sspkata.sspapi.model.bot.BotWrite;
import de.marchinrichs.sspkata.sspbot.entity.BotEntity;
import de.marchinrichs.sspkata.sspbot.repository.BotRepository;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class SignIn {

    @Value("${sspkata.bot.name}")
    private String name;

    @Value("${sspkata.bot.url}")
    private String url;

    @Value("${sspkata.server.url}")
    private String serverUrl;


    @Autowired
    private BotRepository botRepository;

    private static final String REST_URI
            = "http://localhost:9090/bots";

    private Client client = ClientBuilder.newClient();

    @EventListener(ApplicationReadyEvent.class)
    public void signInToServer(ApplicationReadyEvent event) throws URISyntaxException {

        BotId botId = addBot(BotWrite.builder()
                .name(name)
                .clientURL(url)
                .build());

        BotEntity botEntity = new BotEntity();
        botEntity.setId(botId.getId());
        botEntity.setName(name);

        botRepository.save(botEntity);
    }

    private BotId addBot(BotWrite botWrite) {
        return client
                .target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(botWrite),
                        BotId.class);
    }
}