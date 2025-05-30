package com.nequi.v1.gateway;

import com.nequi.v1.model.Producto;
import reactor.core.publisher.Mono;

public interface IProductoGateway {
    Mono<Producto> addProducto(Producto producto);
    Mono<Void> deleteProducto(String id);
    Mono<Void> updateStock(String id, Integer stock);
}
