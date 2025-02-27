package src.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

public class MongoConfig extends AbstractMongoClientConfiguration {
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
