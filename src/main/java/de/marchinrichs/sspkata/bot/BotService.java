package de.marchinrichs.sspkata.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BotService {
    public BotId addBot(Bot bot) {
        return BotId.builder().id(UUID.randomUUID()).build();
    }
}
