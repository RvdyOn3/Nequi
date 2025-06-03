package com.nequi.jpa.v1.service;

import com.nequi.jpa.v1.mapper.ISucursalMapper;
import com.nequi.jpa.v1.repository.SucursalMongoRepository;
import com.nequi.v1.gateway.ISucursalGateway;
import com.nequi.v1.model.Producto;
import com.nequi.v1.model.Sucursal;
import com.nequi.v1.model.error.CustomException;
import com.nequi.v1.model.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SucursalService implements ISucursalGateway {
    private final SucursalMongoRepository sucursalMongoRepository;
    private final ISucursalMapper iSucursalMapper;

    public Mono<Sucursal> addSucursal(Sucursal sucursal) {
        return getSucursalByName(sucursal.getName())
                .flatMap(existingSucursal ->
                        Mono.<Sucursal>error(new CustomException(ResponseCode.NEQUI001, "La sucursal ya se encuentra registrada."))
                )
                .switchIfEmpty(Mono.defer(() ->
                        sucursalMongoRepository.save(iSucursalMapper.toDocument(sucursal))
                                .map(iSucursalMapper::toModel)
                ))
                .onErrorResume(throwable -> {
                    if (throwable instanceof CustomException) {
                        return Mono.<Sucursal>error((CustomException) throwable);
                    }
                    return Mono.<Sucursal>error(new CustomException(ResponseCode.NEQUI003, "Error inesperado al procesar la sucursal."));
                });
    }

    public Mono<Sucursal> getSucursalByName(String name){
        return sucursalMongoRepository.findByName(name.toUpperCase())
                .map(iSucursalMapper::toModel)
                .switchIfEmpty(Mono.error(new CustomException(ResponseCode.NEQUI006, "Sucursal no encontrada.")));
    }

    public Mono<Sucursal> getSucursalById(String id) {
        return sucursalMongoRepository.findById(id)
                .map(iSucursalMapper::toModel)
                .switchIfEmpty(Mono.error(new CustomException(ResponseCode.NEQUI007, "Sucursal no encontrada.")));
    }
}
