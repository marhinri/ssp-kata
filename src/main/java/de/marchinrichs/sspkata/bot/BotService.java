package de.marchinrichs.sspkata.bot;

import de.marchinrichs.sspkata.bot.model.Bot;
import de.marchinrichs.sspkata.bot.model.BotId;
import de.marchinrichs.sspkata.bot.model.BotWrite;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BotService {

    private final BotRepository botRepository;

    private final BotEntityMapper botEntityMapper;

    public BotId addBot(BotWrite botWrite) {

        BotEntity botEntity = botRepository.save(
                botEntityMapper.mapBotToBotEntity(botWrite));

        return BotId.builder().id(botEntity.getId()).build();
    }

    public Bot getBot(UUID uuid) throws NotFoundException {
        return botEntityMapper.mapBotEntityToBotInfo(botRepository.findById(uuid));
    }

    public List<Bot> getBots() {
        return botEntityMapper.mapBotEntitieListToBotInfoList(botRepository.findAll());
    }
}
