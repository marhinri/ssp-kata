package de.marchinrichs.sspkata.sspbot.data;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoundRepository extends CrudRepository<RoundEntity, UUID> {
}
