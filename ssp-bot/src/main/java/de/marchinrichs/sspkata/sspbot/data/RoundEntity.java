package de.marchinrichs.sspkata.sspbot.data;

import de.marchinrichs.sspkata.sspapi.round.Result;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    UUID id;

    Result result;
}
