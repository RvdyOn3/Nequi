package com.nequi.jpa.v1.repository;

import com.nequi.jpa.v1.entity.ProductDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductoMongoRepository extends ReactiveMongoRepository<ProductDocument, String> {
    Mono<ProductDocument> findByName(String name);
}
