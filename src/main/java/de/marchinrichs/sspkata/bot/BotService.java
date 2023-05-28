package de.marchinrichs.sspkata.bot;

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

    public BotId addBot(Bot bot) {

        BotEntity botEntity = botRepository.save(
                botEntityMapper.mapBotToBotEntity(bot));

        return BotId.builder().id(botEntity.getId()).build();
    }

    public BotInfo getBot(UUID uuid) throws NotFoundException {
        return botEntityMapper.mapBotEntityToBotInfo(botRepository.findById(uuid));
    }

    public List<BotInfo> getBots() {
        return botEntityMapper.mapBotEntitieListToBotInfoList(botRepository.findAll());
    }
}
