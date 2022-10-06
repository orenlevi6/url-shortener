package com.oren.urlshortener.config;

import com.oren.urlshortener.beans.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.index.Index;

@Configuration
public class MongoConfig {
    @Value("${spring.data.mongodb.uri}")
    private String connectionString;

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(databaseFactory());
        mongoTemplate.dropCollection(User.class);
        mongoTemplate.createCollection(User.class);
        mongoTemplate.indexOps(User.class).ensureIndex(new Index("username", Direction.ASC).unique());
        return mongoTemplate;
    }

    @Bean
    public MongoDatabaseFactory databaseFactory() {
        return new SimpleMongoClientDatabaseFactory(connectionString);
    }
}
