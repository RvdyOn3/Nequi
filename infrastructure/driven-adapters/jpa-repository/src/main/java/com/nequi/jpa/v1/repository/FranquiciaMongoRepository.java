package com.nequi.jpa.v1.repository;

import com.nequi.jpa.v1.entity.FranquiciaDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiciaMongoRepository extends ReactiveMongoRepository<FranquiciaDocument, String> {
}
