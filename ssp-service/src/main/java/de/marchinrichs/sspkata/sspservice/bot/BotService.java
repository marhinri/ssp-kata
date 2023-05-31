package de.marchinrichs.sspkata.sspservice.bot;

import de.marchinrichs.sspkata.sspapi.model.bot.Bot;
import de.marchinrichs.sspkata.sspapi.model.bot.BotId;
import de.marchinrichs.sspkata.sspapi.model.bot.BotWrite;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BotService {

    private final BotRepository botRepository;

    private final BotEntityMapper botEntityMapper;

    public BotId addBot(BotWrite botWrite) {

        BotEntity botEntity = botRepository.save(
                botEntityMapper.mapBotWriteToBotEntity(botWrite));

        return BotId.builder().id(botEntity.getId()).build();
    }

    public Bot getBot(UUID uuid) throws NotFoundException {
        Optional<BotEntity> botEntity = botRepository.findById(uuid);

        if (botEntity.isEmpty()) {
            throw new NotFoundException("entity with uuid " + uuid + " not found");
        }

        return botEntityMapper.mapBotEntityToBot(botRepository.findById(uuid).get());
    }

    public List<Bot> getBots() {
        return botEntityMapper.mapBotEntitieListToBotInfoList(botRepository.findAll());
    }
}
