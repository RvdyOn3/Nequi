package com.nequi.jpa.v1.repository;

import com.nequi.jpa.v1.entity.SucursalDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SucursalMongoRepository extends ReactiveMongoRepository<SucursalDocument, String> {
    Mono<SucursalDocument> findByName(String name);
}
