package de.marchinrichs.sspkata.bot;

import lombok.Builder;
import lombok.Data;

import java.net.URI;
import java.util.UUID;

@Data
@Builder
public class BotInfo {

    private UUID id;

    private String name;

    private int credit;

    private int won;

    private int lost;

    private URI clientURL;
}
