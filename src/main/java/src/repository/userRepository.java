package src.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import src.model.user;

@Repository
public interface userRepository extends MongoRepository<user,String> {
    user findByEmail(String email);

    boolean existsByEmail(String email);

    Page<user> findAllBy(Pageable pageable);
}
