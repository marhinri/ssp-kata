package de.marchinrichs.sspkata.sspapi.model.round;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoundResult {

    private Result result;

    private int looserStake;
}
