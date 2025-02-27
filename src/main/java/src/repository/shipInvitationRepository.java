package src.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import src.model.shipInvitation;

public interface shipInvitationRepository extends MongoRepository<shipInvitation, String> {
}
