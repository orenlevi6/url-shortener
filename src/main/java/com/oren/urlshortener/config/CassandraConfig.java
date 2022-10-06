package com.oren.urlshortener.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;

import java.util.List;

@Configuration
public class CassandraConfig {
    @Value("${spring.data.cassandra.keyspace-name}")
    private String keySpace;
    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;
    @Value("${spring.data.cassandra.port}")
    private int port;
    @Value("${spring.data.cassandra.local-datacenter}")
    private String dataCenter;

    @Bean
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean session = new CqlSessionFactoryBean();
        session.setKeyspaceCreations(List.of(CreateKeyspaceSpecification
                .createKeyspace(keySpace).ifNotExists().withSimpleReplication(1)));
        session.setKeyspaceName(keySpace);
        session.setContactPoints(contactPoints);
        session.setPort(port);
        session.setLocalDatacenter(dataCenter);
        return session;
    }

}
