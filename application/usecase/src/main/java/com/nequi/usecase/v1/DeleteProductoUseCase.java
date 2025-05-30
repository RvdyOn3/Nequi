package com.nequi.usecase.v1;

import com.nequi.v1.gateway.IProductoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeleteProductoUseCase {
    private final IProductoGateway productoGateway;

    public Mono<Void> execute(String id) {
        return productoGateway.deleteProducto(id);
    }
}
