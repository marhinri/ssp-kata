package de.marchinrichs.sspkata.sspservice.bot.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BotId {
    UUID id;
}
