package de.marchinrichs.sspkata.bot;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BotId {
    UUID id;
}
