package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import src.helper.roleHelper;
import src.model.user;

import java.util.concurrent.ExecutionException;

@Service
public class userService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void updateDisplayName(String email, String displayName) {
        Query query = new Query(Criteria.where("_id").is(email));

        Update update = new Update().set("displayName", displayName);

        mongoTemplate.updateFirst(query, update, user.class);
    }

    public void assignRole(String email, String role) throws IllegalArgumentException {
        if (role == null || role.trim().isEmpty() || !roleHelper.checkRole(role)) {
            throw new IllegalArgumentException("Role cannot be null, empty, or invalid.");
        }

        Query query = new Query(Criteria.where("_id").is(email));

        Update update = new Update().addToSet("role", role.toLowerCase());

        mongoTemplate.updateFirst(query, update, user.class);
    }
}
