package de.marchinrichs.sspkata.sspapi.model.bot;

import lombok.*;

import java.net.URL;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BotWrite {

    private String name;

    private String clientURL;
}
