package src.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import src.model.Ship;

public interface ShipRepository extends MongoRepository<Ship, String> {
}
