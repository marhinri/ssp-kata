package de.marchinrichs.sspkata.bot;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void testAddBot_returns_created() {
        String name = "bot1";
        String url = "http://localhost:1234";
        UUID uuid = UUID.randomUUID();

        when(botService.addBot(Bot.builder()
                .name(name)
                .clientURL(URI.create(url))
                .build()))
                .thenReturn(BotId.builder().id(uuid).build());

        Response response = target("bots").request()
                .post(Entity.json("{\"name\":\"bot1\",\"clientURL\":\"http://localhost:1234\"}"));

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertTrue(response.readEntity(String.class).contains("{\"id\":\""+ uuid + "\""));
    }

    @Test
    public void getBots_returns_ok() {
        Response response = target("bots").request().get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }


    @Test
    public void getBot_returns_ok() {
        UUID uuid = UUID.randomUUID();

        BotInfo botInfo = BotInfo.builder()
                .id(uuid)
                .name("sample-bot")
                .won(0)
                .lost(0)
                .credit(100)
                .clientURL(URI.create("http://localhost:1234"))
                .build();

        when(botService.getBotInfo(uuid)).thenReturn(botInfo);
        Response response = target("bots").path(uuid.toString()).request().get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.readEntity(String.class).contains("\"id\":\""+ uuid + "\""));
    }

    @Test
    public void getBot_returns_return_notFound() {
        UUID uuid = UUID.randomUUID();

        when(botService.getBotInfo(uuid)).thenThrow(NotFoundException.class);

        Response response = target("bots").path(uuid.toString()).request().get();

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
}
