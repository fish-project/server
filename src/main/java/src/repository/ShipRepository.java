package src.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import src.model.Ship;
@Repository
public interface ShipRepository extends MongoRepository<Ship, String> {
    Ship findByshipName (String shipName);
}
