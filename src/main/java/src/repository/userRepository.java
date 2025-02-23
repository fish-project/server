package src.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReadPreference;
import org.springframework.stereotype.Repository;
import src.model.user;

@Repository
public interface userRepository extends MongoRepository<user,String> {
    user findByEmail(String email);
}
