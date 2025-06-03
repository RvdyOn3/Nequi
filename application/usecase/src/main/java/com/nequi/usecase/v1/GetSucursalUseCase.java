package com.nequi.usecase.v1;

import com.nequi.v1.gateway.ISucursalGateway;
import com.nequi.v1.model.Sucursal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetSucursalUseCase {
    private final ISucursalGateway sucursalGateway;

    public Mono<Sucursal> execute(String id) {
        return sucursalGateway.getSucursalById(id);
    }
}
