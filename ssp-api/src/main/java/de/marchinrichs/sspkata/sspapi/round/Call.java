package de.marchinrichs.sspkata.sspapi.round;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Call {

    private String opponent;

    private UUID roundId;
}
