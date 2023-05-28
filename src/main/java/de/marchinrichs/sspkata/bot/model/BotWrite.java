package de.marchinrichs.sspkata.bot.model;

import lombok.Builder;
import lombok.Data;

import java.net.URL;

@Data
@Builder
public class BotWrite {

    private String name;

    private String clientURL;
}
