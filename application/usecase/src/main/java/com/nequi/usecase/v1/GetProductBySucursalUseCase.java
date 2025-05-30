package com.nequi.usecase.v1;

import com.nequi.v1.gateway.IProductoGateway;
import com.nequi.v1.model.ProductsBySucursal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class GetProductBySucursalUseCase {
    private final IProductoGateway productoGateway;

    public Flux<ProductsBySucursal> execute(String franquiciaId) {
        return productoGateway.getProductBySucursal(franquiciaId);
    }
}
