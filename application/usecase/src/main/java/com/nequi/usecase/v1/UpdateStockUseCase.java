package com.nequi.usecase.v1;

import com.nequi.v1.gateway.IProductoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpdateStockUseCase {
    private final IProductoGateway productoGateway;

    public Mono<Void> execute(String id, Integer stock) {
        return productoGateway.updateStock(id, stock);
    }
}
