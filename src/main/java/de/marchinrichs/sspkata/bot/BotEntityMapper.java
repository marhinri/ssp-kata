package de.marchinrichs.sspkata.bot;

import org.springframework.stereotype.Component;

@Component
public class BotEntityMapper {

    public BotEntity mapBotToBotEntity(Bot bot) {
        return BotEntity.builder()
                .name(bot.getName())
                .clientURL(bot.getClientURL())
                .build();
    }
}
