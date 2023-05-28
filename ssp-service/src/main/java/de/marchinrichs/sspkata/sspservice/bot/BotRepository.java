package de.marchinrichs.sspkata.sspservice.bot;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BotRepository extends CrudRepository<BotEntity, UUID> {
}
