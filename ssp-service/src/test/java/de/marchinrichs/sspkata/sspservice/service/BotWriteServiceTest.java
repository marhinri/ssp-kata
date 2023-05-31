package de.marchinrichs.sspkata.sspservice.service;

import de.marchinrichs.sspkata.sspapi.model.bot.BotId;
import de.marchinrichs.sspkata.sspapi.model.bot.BotWrite;
import de.marchinrichs.sspkata.sspservice.entity.BotEntity;
import de.marchinrichs.sspkata.sspservice.entity.BotEntityMapper;
import de.marchinrichs.sspkata.sspservice.repository.BotRepository;
import de.marchinrichs.sspkata.sspservice.service.BotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
                .clientURL("http://localhost:1234")
                .build();

        BotEntity botEntity = BotEntity.builder()
                .id(expectedId)
                .name(botWrite.getName())
                .clientURL(botWrite.getClientURL())
                .build();

        when(botEntityMapper.mapBotWriteToBotEntity(botWrite)).thenReturn(botEntity);
        when(botRepository.save(botEntity)).thenReturn(botEntity);

        BotId botId = botService.addBot(botWrite);

        assertEquals(expectedId, botId.getId());
    }
}