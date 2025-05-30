package com.nequi.jpa.v1.service;

import com.nequi.jpa.v1.entity.SucursalDocument;
import com.nequi.jpa.v1.mapper.IProductoMapper;
import com.nequi.jpa.v1.repository.FranquiciaMongoRepository;
import com.nequi.jpa.v1.repository.ProductoMongoRepository;
import com.nequi.v1.gateway.IProductoGateway;
import com.nequi.v1.model.Producto;
import com.nequi.v1.model.ProductsBySucursal;
import com.nequi.v1.model.error.CustomException;
import com.nequi.v1.model.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoGateway {
    private final ProductoMongoRepository productoMongoRepository;
    private final FranquiciaMongoRepository franquiciaMongoRepository;
    private final IProductoMapper iProductoMapper;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<Producto> addProducto(Producto producto) {
        return productoMongoRepository.findByName(producto.getName().toUpperCase())
                .flatMap(existingProduct ->
                        Mono.<Producto>error(new CustomException(ResponseCode.NEQUI001, "El producto ya se encuentra registrado."))
                )
                .switchIfEmpty(Mono.defer(() ->
                        productoMongoRepository.save(iProductoMapper.toDocument(producto))
                                .map(iProductoMapper::toModel)
                ))
                .onErrorResume(throwable -> {
                    if (throwable instanceof CustomException) {
                        return Mono.<Producto>error((CustomException) throwable);
                    }
                    return Mono.<Producto>error(new CustomException(ResponseCode.NEQUI003, "Error inesperado al procesar el producto."));
                });
    }

    public Mono<Void> deleteProducto(String id) {
        return productoMongoRepository.deleteById(id);
    }

    public Mono<Void> updateStock(String id, Integer stock) {
        return productoMongoRepository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Producto no encontrado")))
                .flatMap(producto -> {
                        producto.setStock(stock);
                        return productoMongoRepository.save(producto);
                })
                .then();
    }

    @Override
    public Flux<ProductsBySucursal> getProductBySucursal(String franquiciaId) {
        return franquiciaMongoRepository.findById(franquiciaId)
                .switchIfEmpty(Mono.error(new CustomException(ResponseCode.NEQUI005, "Franquicia no encontrada")))
                .flatMapMany(f -> runAggregationMayorStock(franquiciaId));
    }

    private Flux<ProductsBySucursal> runAggregationMayorStock(String franquiciaId) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.lookup("sucursal", "sucursalId", "_id", "sucursal"),
                Aggregation.unwind("sucursal"),
                Aggregation.match(Criteria.where("sucursal.franquiciaId").is(new ObjectId(franquiciaId))),
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "stock")),
                Aggregation.group("sucursalId")
                        .first("name").as("name")
                        .first("stock").as("stock")
                        .first("sucursal.name").as("nameSucursal")
                        .first("sucursalId").as("sucursalId")
                        .first("_id").as("id")
        );

        return reactiveMongoTemplate.aggregate(aggregation, "producto", ProductsBySucursal.class);
    }
}
