package de.marchinrichs.sspkata.sspbot.round;

import de.marchinrichs.sspkata.sspapi.round.Call;
import de.marchinrichs.sspkata.sspapi.round.CallResponse;
import de.marchinrichs.sspkata.sspapi.round.RoundResult;
import de.marchinrichs.sspkata.sspapi.round.Symbol;
import de.marchinrichs.sspkata.sspbot.data.BotEntity;
import de.marchinrichs.sspkata.sspbot.data.BotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoundService {

    private final RandomStakeGenerator randomStakeGenerator;

    private final BotRepository botRepository;

    public CallResponse call(Call call) {

        BotEntity botEntity = botRepository.findAll().iterator().next();

        CallResponse callResponse = CallResponse.builder()
                .symbol(Symbol.valueOf(RandomSymbol.randomSymbol().name()))
                .stake(randomStakeGenerator.randomStake(botEntity.getCredit())).build();

        return callResponse;
    }

    public void addRoundResult(UUID id, RoundResult result) {

    }
}
