package de.marchinrichs.sspkata.bot;

import de.marchinrichs.sspkata.bot.model.Bot;
import de.marchinrichs.sspkata.bot.model.BotWrite;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BotEntityMapper {

    public BotEntity mapBotToBotEntity(BotWrite botWrite) {
        return BotEntity.builder()
                .name(botWrite.getName())
                .clientURL(botWrite.getClientURL())
                .build();
    }

    public Bot mapBotEntityToBotInfo(Optional<BotEntity> byId) {
        return Bot.builder().build();
    }

    public List<Bot> mapBotEntitieListToBotInfoList(Iterable<BotEntity> all) {
        return List.of();
    }
}
