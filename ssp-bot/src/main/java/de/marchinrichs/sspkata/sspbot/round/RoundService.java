package de.marchinrichs.sspkata.sspbot.round;

import de.marchinrichs.sspkata.sspapi.round.Call;
import de.marchinrichs.sspkata.sspapi.round.CallResponse;
import de.marchinrichs.sspkata.sspapi.round.RoundResult;
import de.marchinrichs.sspkata.sspapi.round.Symbol;
import de.marchinrichs.sspkata.sspbot.data.BotEntity;
import de.marchinrichs.sspkata.sspbot.data.BotRepository;
import de.marchinrichs.sspkata.sspbot.data.RoundEntity;
import de.marchinrichs.sspkata.sspbot.data.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoundService {

    private final RandomStakeGenerator randomStakeGenerator;

    private final BotRepository botRepository;

    private RoundRepository roundRepository;

    public CallResponse call(Call call) {

        BotEntity botEntity = botRepository.findAll().iterator().next();

        int stake = randomStakeGenerator.randomStake(botEntity.getCredit());

        RoundEntity roundEntity = RoundEntity.builder()
                .bot(botEntity)
                .opponent(call.getOpponent())
                .id(call.getRoundId())
                .stake(stake)
                .build();
        roundRepository.save(roundEntity);

        CallResponse callResponse = CallResponse.builder()
                .symbol(Symbol.valueOf(RandomSymbol.randomSymbol().name()))
                .stake(stake).build();

        return callResponse;
    }

    public void addRoundResult(UUID id, RoundResult result) {

    }
}
