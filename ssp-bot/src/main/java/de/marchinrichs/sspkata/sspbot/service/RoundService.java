package de.marchinrichs.sspkata.sspbot.service;

import de.marchinrichs.sspkata.sspapi.model.round.Call;
import de.marchinrichs.sspkata.sspapi.model.round.CallResponse;
import de.marchinrichs.sspkata.sspapi.model.round.RoundResult;
import de.marchinrichs.sspkata.sspapi.model.round.Symbol;
import de.marchinrichs.sspkata.sspbot.entity.BotEntity;
import de.marchinrichs.sspkata.sspbot.repository.BotRepository;
import de.marchinrichs.sspkata.sspbot.entity.RoundEntity;
import de.marchinrichs.sspkata.sspbot.repository.RoundRepository;
import de.marchinrichs.sspkata.sspbot.util.RandomStakeGenerator;
import de.marchinrichs.sspkata.sspbot.util.RandomSymbol;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoundService {

    private final RandomStakeGenerator randomStakeGenerator;

    private final BotRepository botRepository;

    private final RoundRepository roundRepository;

    @Transactional
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

    @Transactional
    public void addRoundResult(UUID id, RoundResult result) {
        Optional<RoundEntity> roundEntityOptional = roundRepository.findById(id);

        if (roundEntityOptional.isEmpty()) {
            throw new NotFoundException("Round " + id + " not found on bot");
        }

        RoundEntity roundEntity = roundEntityOptional.get();
        BotEntity botEntity = roundEntity.getBot();

        roundEntity.setResult(result.getResult());

        processResult(botEntity, result);

        botRepository.save(botEntity);
        roundRepository.save(roundEntity);
    }

    private void processResult(BotEntity botEntity, RoundResult result) {

        switch (result.getResult()) {
            case won -> {
                botEntity.setWon(botEntity.getWon()+1);
                botEntity.setCredit(botEntity.getCredit()+result.getLooserStake());
            }
            case lost -> {
                botEntity.setLost(botEntity.getLost()+1);
                botEntity.setCredit(botEntity.getCredit()-result.getLooserStake());
            }
            case draw -> botEntity.setDraw(botEntity.getDraw()+1);
        }

    }

}
