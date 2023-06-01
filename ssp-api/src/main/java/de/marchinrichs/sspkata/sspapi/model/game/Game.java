package de.marchinrichs.sspkata.sspapi.model.game;

import de.marchinrichs.sspkata.sspapi.model.bot.Bot;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    private Bot player1;

    private Bot player2;

    private Bot winner;

    private List<GameCall> calls;

    private int stake;
}
