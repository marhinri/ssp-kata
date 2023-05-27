package de.marchinrichs.sspkata.bot;

import lombok.Builder;
import lombok.Data;

import java.net.URI;
import java.net.URL;
import java.util.UUID;

@Data
@Builder
public class Bot {

    private String name;

    private URI clientURL;
}
