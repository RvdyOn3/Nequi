package com.nequi.usecase.v1;

import com.nequi.v1.gateway.ISucursalGateway;
import com.nequi.v1.model.Sucursal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateSucursalUseCase {

    private final ISucursalGateway sucursalRepository;

    public Mono<Sucursal> execute(Sucursal sucursal) {
        return sucursalRepository.addSucursal(sucursal);
    }
}
