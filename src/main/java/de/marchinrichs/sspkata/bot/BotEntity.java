package de.marchinrichs.sspkata.bot;

import jakarta.persistence.*;
import lombok.*;

import java.net.URI;
import java.util.UUID;

@Entity
@Table(name = "Bot")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private int credit;

    private int won;

    private int lost;

    private String clientURL;
}
