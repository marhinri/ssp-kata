package de.marchinrichs.sspkata.bot;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BotControllerIntegrationTest extends JerseySpringTest {

    @Override
    protected Class<?> getResourceClass() {
        return BotController.class;
    }
    @Test
    public void testAddBot_returns_created() {
        Response response = target("bots").request()
                .post(Entity.json("{\"name\":\"bot1\",\"clientURL\":\"http://localhost:1234\"}"));

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertTrue(response.readEntity(String.class).contains("id"));

    }
}
