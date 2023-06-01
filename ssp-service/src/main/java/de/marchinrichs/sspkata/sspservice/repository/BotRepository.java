package de.marchinrichs.sspkata.sspservice.repository;

import de.marchinrichs.sspkata.sspservice.entity.BotEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BotRepository extends CrudRepository<BotEntity, UUID> {

   @Query(value = "SELECT id FROM BotEntity WHERE credit > 0")
   List<UUID> findIdsWithCredit();
}
