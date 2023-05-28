package de.marchinrichs.sspkata.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BotService {

    private final BotRepository botRepository;

    private final BotEntityMapper botEntityMapper;

    public BotId addBot(Bot bot) {

        BotEntity botEntity = botRepository.save(
                botEntityMapper.mapBotToBotEntity(bot));

        return BotId.builder().id(botEntity.getId()).build();
    }
}
