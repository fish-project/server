package src.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import src.model.ship;

import java.util.List;

public interface ShipRepository extends MongoRepository<ship, String> {
    List<ship> findByShipOwner(String shipOwner);
}
