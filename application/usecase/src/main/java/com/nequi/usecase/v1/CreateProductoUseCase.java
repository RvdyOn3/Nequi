package com.nequi.usecase.v1;

import com.nequi.v1.gateway.IProductoGateway;
import com.nequi.v1.model.Producto;
import com.nequi.v1.model.Sucursal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateProductoUseCase {

    private final GetSucursalUseCase getSucursalUseCase;
    private final IProductoGateway iProductoGateway;

    public Mono<Producto> execute(Producto producto) {
        return getSucursal(producto.getSucursalId())
                .flatMap(sucursal -> createProducto(producto));
    }

    public Mono<Producto> createProducto(Producto producto) {
        return iProductoGateway.addProducto(producto);
    }

    public Mono<Sucursal> getSucursal(String id) {
        return getSucursalUseCase.execute(id);
    }
}
