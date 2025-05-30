package com.nequi.usecase.v1;

import com.nequi.v1.gateway.IProductoGateway;
import com.nequi.v1.model.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateProductoUseCase {

    private final IProductoGateway iProductoGateway;

    public Mono<Producto> execute(Producto producto) {
        return iProductoGateway.addProducto(producto);
    }
}
