package com.ty.repository;

import com.ty.model.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationRepository extends MongoRepository<Configuration, String> {
    List<Configuration> findByApplicationNameAndIsActiveTrue(String applicationName);
}
