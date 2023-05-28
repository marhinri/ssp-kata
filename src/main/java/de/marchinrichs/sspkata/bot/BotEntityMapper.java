package de.marchinrichs.sspkata.bot;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BotEntityMapper {

    public BotEntity mapBotToBotEntity(Bot bot) {
        return BotEntity.builder()
                .name(bot.getName())
                .clientURL(bot.getClientURL())
                .build();
    }

    public BotInfo mapBotEntityToBotInfo(Optional<BotEntity> byId) {
        return BotInfo.builder().build();
    }
}
