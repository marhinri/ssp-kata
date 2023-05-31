package de.marchinrichs.sspkata.sspapi.model.round;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallResponse {

    private int stake;

    private Symbol symbol;
}

