package com.nequi.v1.gateway;

import com.nequi.v1.model.Sucursal;
import reactor.core.publisher.Mono;

public interface ISucursalGateway {
    Mono<Sucursal> addSucursal(Sucursal sucursal);
    Mono<Sucursal> getSucursalById(String id);
}
