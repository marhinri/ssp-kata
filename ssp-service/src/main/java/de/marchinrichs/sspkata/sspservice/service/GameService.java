package de.marchinrichs.sspkata.sspservice.service;

import de.marchinrichs.sspkata.sspapi.model.game.Game;
import de.marchinrichs.sspkata.sspapi.model.game.GameCall;
import de.marchinrichs.sspkata.sspapi.model.round.*;
import de.marchinrichs.sspkata.sspservice.entity.BotEntity;
import de.marchinrichs.sspkata.sspservice.entity.BotEntityMapper;
import de.marchinrichs.sspkata.sspservice.exception.NotEnoughPlayersException;
import de.marchinrichs.sspkata.sspservice.repository.BotRepository;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private final BotRepository botRepository;

    private final BotEntityMapper botEntityMapper;

    private static final Random PRNG = new Random();

    private final Client client = ClientBuilder.newClient();

    public List<Game> play(int rounds) {
        List<Game> gameList = new ArrayList<>();
        for (int i = 1; i<= rounds; i++) {
            try {
                gameList.add(playGame());
            } catch (NotEnoughPlayersException e) {
                break;
            }
        }

        return gameList;
    }

    private Game playGame() throws NotEnoughPlayersException {
        Game game = new Game();

        UUID roundID = UUID.randomUUID();


        List<BotEntity> players = selectPlayers();

        BotEntity player1 = players.get(0);
        BotEntity player2 = players.get(1);

        Call callPlayer1 = Call.builder()
                .opponent(player2.getName())
                .roundId(roundID)
                .build();

        Call callPlayer2 = Call.builder()
                .opponent(player1.getName())
                .roundId(roundID)
                .build();

        CallResponse callResponse1 = callBot(player1, callPlayer1);
        CallResponse callResponse2 = callBot(player2, callPlayer2);

        Symbol player1Symbol = callResponse1.getSymbol();
        Symbol player2Symbol = callResponse2.getSymbol();

        GameCall calls = GameCall.builder()
                .symbolPlayerOne(player1Symbol)
                .symbolPlayerTwo(player2Symbol)
                .build();

        game.setCalls(List.of(calls));

        RoundResult result1;
        RoundResult result2;

        if (player1Symbol.equals(player2Symbol)) {
            game.setStake(0);
            result1 = RoundResult.builder().result(Result.draw).build();
            result2 = RoundResult.builder().result(Result.draw).build();
        } else if (isPlayer1Win(player1Symbol, player2Symbol)) {
            player1.setWon(player1.getWon()+1);
            player2.setLost(player2.getLost()+1);

            game.setStake(callResponse2.getStake());

            player1.setCredit(player1.getCredit() + game.getStake());
            player2.setCredit(player2.getCredit() - game.getStake());

            game.setWinner(botEntityMapper.mapBotEntityToBot(player1));

            result1 = RoundResult.builder()
                    .result(Result.won)
                    .looserStake(game.getStake())
                    .build();

            result2 = RoundResult.builder()
                    .result(Result.lost)
                    .looserStake(game.getStake())
                    .build();
        } else {
            player2.setWon(player2.getWon()+1);
            player1.setLost(player1.getLost()+1);

            game.setStake(callResponse1.getStake());

            player1.setCredit(player1.getCredit() - game.getStake());
            player2.setCredit(player2.getCredit() + game.getStake());

            game.setWinner(botEntityMapper.mapBotEntityToBot(player2));

            result1 = RoundResult.builder()
                    .result(Result.lost)
                    .looserStake(game.getStake())
                    .build();

            result2 = RoundResult.builder()
                    .result(Result.won)
                    .looserStake(game.getStake())
                    .build();
        }

        game.setPlayer1(botEntityMapper.mapBotEntityToBot(player1));
        game.setPlayer2(botEntityMapper.mapBotEntityToBot(player2));

        botRepository.save(player1);
        botRepository.save(player2);

        sendRoundToBot(player1, result1);
        sendRoundToBot(player2, result2);

        return game;
    }


    private CallResponse callBot(BotEntity p, Call call) {
        return client
                .target(p.getClientURL())
                .path("round/call")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(call),
                        CallResponse.class);
    }

    private void sendRoundToBot(BotEntity p, RoundResult result) {
        client.target(p.getClientURL())
                .path("round/call")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(result));
    }

    private List<BotEntity> selectPlayers() throws NotEnoughPlayersException {
        List<UUID> botIds = botRepository.findIdsWithCredit();

        if (botIds.size() < 2) {
            throw new NotEnoughPlayersException("Only "+ botIds.size() + " players left");
        }

        List<BotEntity> players = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            int randomIndex = PRNG.nextInt(botIds.size());
            UUID randomElement = botIds.get(randomIndex);
            botIds.remove(randomIndex);

            players.add(botRepository.findById(randomElement).get());
        }

        return players;
    }

    private boolean isPlayer1Win(Symbol player1Symbol, Symbol player2Symbol) {
        return player1Symbol.equals(Symbol.ROCK) && player2Symbol.equals(Symbol.SCISSORS)
                || (player1Symbol.equals(Symbol.SCISSORS) && player2Symbol.equals(Symbol.PAPER))
                || (player1Symbol.equals(Symbol.PAPER) && player2Symbol.equals(Symbol.ROCK));
    }
}

