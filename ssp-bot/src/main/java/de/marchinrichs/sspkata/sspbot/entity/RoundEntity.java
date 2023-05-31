package de.marchinrichs.sspkata.sspbot.entity;

import de.marchinrichs.sspkata.sspapi.model.round.Result;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "round")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoundEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name="bot_id", nullable=false)
    private BotEntity bot;

    private String opponent;

    private Result result;

    private int stake;
}
