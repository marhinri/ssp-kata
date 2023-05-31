package de.marchinrichs.sspkata.sspservice.repository;

import de.marchinrichs.sspkata.sspservice.entity.BotEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BotRepository extends CrudRepository<BotEntity, UUID> {
}
