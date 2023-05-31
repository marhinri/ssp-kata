package de.marchinrichs.sspkata.sspservice.resource;

import de.marchinrichs.sspkata.sspapi.model.bot.Bot;
import de.marchinrichs.sspkata.sspapi.model.bot.BotId;
import de.marchinrichs.sspkata.sspapi.model.bot.BotWrite;
import de.marchinrichs.sspkata.sspservice.JerseySpringTest;
import de.marchinrichs.sspkata.sspservice.resource.BotResource;
import de.marchinrichs.sspkata.sspservice.service.BotService;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BotResourceTest extends JerseySpringTest {

    @InjectMocks
    private BotResource subject;

    @Mock
    private BotService botService;


    @Override
    protected List<Object> getBeans() {
        return List.of(subject, botService);
    }

    @Override
    protected Class<?> getResourceClass() {
        return BotResource.class;
    }

    @Test
    public void addBot_returns_created() {
        String name = "bot1";
        String url = "http://localhost:1234";
        UUID uuid = UUID.randomUUID();

        BotWrite botWrite = BotWrite.builder()
                .name(name)
                .clientURL(url)
                .build();

        when(botService.addBot(any()))
                .thenReturn(BotId.builder().id(uuid).build());

        Response response = target("bots").request()
                .post(Entity.json(botWrite));

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(uuid, response.readEntity(BotId.class).getId());
    }

    @Test
    public void getBots_returns_ok() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        Bot bot1 = Bot.builder()
                .id(uuid1)
                .name("sample-bot-1")
                .won(0)
                .lost(0)
                .credit(100)
                .clientURL("http://localhost:1234")
                .build();

        Bot bot2 = Bot.builder()
                .id(uuid2)
                .name("sample-bot-2")
                .won(0)
                .lost(0)
                .credit(100)
                .clientURL("http://localhost:2345")
                .build();

        when(botService.getBots()).thenReturn(List.of(bot1, bot2));
        Response response = target("bots").request().get();

        List<Bot> bots = response.readEntity(new GenericType<>() {
        });

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(2, bots.size());
    }


    @Test
    public void getBot_returns_ok() throws JSONException {
        UUID uuid = UUID.randomUUID();

        Bot bot = Bot.builder()
                .id(uuid)
                .name("sample-bot")
                .won(0)
                .lost(0)
                .credit(100)
                .clientURL("http://localhost:1234")
                .build();

        when(botService.getBot(uuid)).thenReturn(bot);
        Response response = target("bots").path(uuid.toString()).request().get();

        Bot botResponse = response.readEntity(Bot.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(uuid, botResponse.getId());
    }

    @Test
    public void getBot_returns_return_notFound() {
        UUID uuid = UUID.randomUUID();

        when(botService.getBot(uuid)).thenThrow(NotFoundException.class);

        Response response = target("bots").path(uuid.toString()).request().get();

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
}
