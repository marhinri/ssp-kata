package de.marchinrichs.sspkata.bot;

import de.marchinrichs.sspkata.bot.model.Bot;
import de.marchinrichs.sspkata.bot.model.BotWrite;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public Bot mapBotEntityToBot(BotEntity botEntity) {
        return Bot.builder()
                .id(botEntity.getId())
                .name(botEntity.getName())
                .credit(botEntity.getCredit())
                .won(botEntity.getWon())
                .lost(botEntity.getLost())
                .clientURL(botEntity.getClientURL())
                .build();
    }

    public List<Bot> mapBotEntitieListToBotInfoList(Iterable<BotEntity> botEntities) {
        List<Bot> botList = new ArrayList();
        botEntities.forEach(b -> botList.add(mapBotEntityToBot(b)));
        
        return botList;
    }
}
