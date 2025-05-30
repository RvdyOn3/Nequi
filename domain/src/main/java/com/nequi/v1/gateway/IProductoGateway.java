package com.nequi.v1.gateway;

import com.nequi.v1.model.Producto;
import com.nequi.v1.model.ProductsBySucursal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductoGateway {
    Mono<Producto> addProducto(Producto producto);
    Mono<Void> deleteProducto(String id);
    Mono<Void> updateStock(String id, Integer stock);
    Flux<ProductsBySucursal> getProductBySucursal(String franquiciaId);
}
