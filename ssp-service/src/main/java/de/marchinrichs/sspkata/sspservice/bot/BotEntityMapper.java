package de.marchinrichs.sspkata.sspservice.bot;

import de.marchinrichs.sspkata.sspapi.model.bot.Bot;
import de.marchinrichs.sspkata.sspapi.model.bot.BotWrite;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BotEntityMapper {

    public BotEntity mapBotWriteToBotEntity(BotWrite botWrite) {
        return BotEntity.builder()
                .name(botWrite.getName())
                .credit(100)
                .won(0)

                .lost(0)
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
