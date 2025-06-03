package com.nequi.usecase.v1;

import com.nequi.v1.gateway.ISucursalGateway;
import com.nequi.v1.model.Franquicia;
import com.nequi.v1.model.Sucursal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateSucursalUseCase {

    private final GetFranquiciaUseCase getFranquiciaUseCase;
    private final ISucursalGateway sucursalRepository;

    public Mono<Sucursal> execute(Sucursal sucursal) {
        return getFranquicia(sucursal.getFranquiciaId())
                .flatMap(franquicia -> createSucursal(sucursal));
    }

    private Mono<Sucursal> createSucursal(Sucursal sucursal) {
        return sucursalRepository.addSucursal(sucursal);
    }

    public Mono<Franquicia> getFranquicia(String id) {
        return getFranquiciaUseCase.execute(id);
    }

}
