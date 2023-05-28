package de.marchinrichs.sspkata.bot;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.net.URI;
import java.util.UUID;

@Entity
@Table(name = "Bot")
@Getter
@Builder
public class BotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private URI clientURL;
}
