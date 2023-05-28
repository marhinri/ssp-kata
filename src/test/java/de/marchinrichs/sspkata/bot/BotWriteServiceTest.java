package de.marchinrichs.sspkata.bot;

import de.marchinrichs.sspkata.bot.model.BotId;
import de.marchinrichs.sspkata.bot.model.BotWrite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BotWriteServiceTest {

    @Mock
    private BotRepository botRepository;

    @Mock
    private BotEntityMapper botEntityMapper;

    @InjectMocks
    private BotService botService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBot() {
        UUID expectedId = UUID.randomUUID();

        BotWrite botWrite = BotWrite.builder()
                .name("sample-bot")
                .clientURL(URI.create("http://localhost:1234"))
                .build();

        BotEntity botEntity = BotEntity.builder()
                .id(expectedId)
                .name(botWrite.getName())
                .clientURL(botWrite.getClientURL())
                .build();

        when(botEntityMapper.mapBotToBotEntity(botWrite)).thenReturn(botEntity);
        when(botRepository.save(botEntity)).thenReturn(botEntity);

        BotId botId = botService.addBot(botWrite);

        assertEquals(expectedId, botId.getId());
    }
}