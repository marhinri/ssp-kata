package de.marchinrichs.sspkata.sspbot.repository;

import de.marchinrichs.sspkata.sspbot.entity.BotEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BotRepository extends CrudRepository<BotEntity, UUID> {
}
