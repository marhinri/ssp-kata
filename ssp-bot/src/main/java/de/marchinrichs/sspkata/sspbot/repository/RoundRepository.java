package de.marchinrichs.sspkata.sspbot.repository;

import de.marchinrichs.sspkata.sspbot.entity.RoundEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoundRepository extends CrudRepository<RoundEntity, UUID> {
}
