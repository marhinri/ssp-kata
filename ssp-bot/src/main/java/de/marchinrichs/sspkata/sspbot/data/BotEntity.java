package de.marchinrichs.sspkata.sspbot.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
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
    private UUID id;

    private String name;

    private int credit = 100;

    private int won;

    private int lost;

    private int draw;

    @OneToMany(mappedBy="bot")
    private List<RoundEntity> roundEntityList;
}
