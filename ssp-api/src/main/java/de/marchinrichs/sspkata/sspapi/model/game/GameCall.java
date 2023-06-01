package de.marchinrichs.sspkata.sspapi.model.game;

import de.marchinrichs.sspkata.sspapi.model.round.Symbol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameCall {

    private Symbol symbolPlayerOne;

    private Symbol symbolPlayerTwo;
}
