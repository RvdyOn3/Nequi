package com.nequi.jpa.v1.repository;

import com.nequi.jpa.v1.entity.FranquiciaDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FranquiciaMongoRepository extends ReactiveMongoRepository<FranquiciaDocument, String> {
    Mono<FranquiciaDocument> findByName(String name);
}
