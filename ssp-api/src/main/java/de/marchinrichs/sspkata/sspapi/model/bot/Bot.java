package de.marchinrichs.sspkata.sspapi.model.bot;

import lombok.*;

import java.net.URI;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bot {

    private UUID id;

    private String name;

    private int credit;

    private int won;

    private int lost;

    private String clientURL;
}
