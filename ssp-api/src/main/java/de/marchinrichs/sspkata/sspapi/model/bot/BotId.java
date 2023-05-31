package de.marchinrichs.sspkata.sspapi.model.bot;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BotId {
    private UUID id;
}
