package de.marchinrichs.sspkata.sspapi.round;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallResponse {

    private int stake;

    private Symbol symbol;
}

