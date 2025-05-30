package com.nequi.jpa.v1.service;

import com.nequi.jpa.v1.mapper.IProductoMapper;
import com.nequi.jpa.v1.repository.ProductoMongoRepository;
import com.nequi.v1.gateway.IProductoGateway;
import com.nequi.v1.model.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoGateway {
    private final ProductoMongoRepository productoMongoRepository;
    private final IProductoMapper iProductoMapper;

    public Mono<Producto> addProducto(Producto producto) {
        return productoMongoRepository.save(iProductoMapper.toDocument(producto)).map(iProductoMapper::toModel);
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
}
