package com.nequi.jpa.v1.service;

import com.nequi.jpa.v1.mapper.ISucursalMapper;
import com.nequi.jpa.v1.repository.SucursalMongoRepository;
import com.nequi.v1.gateway.ISucursalGateway;
import com.nequi.v1.model.Sucursal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SucursalService implements ISucursalGateway {
    private final SucursalMongoRepository sucursalMongoRepository;
    private final ISucursalMapper iSucursalMapper;

    public Mono<Sucursal> addSucursal(Sucursal sucursal) {
        return sucursalMongoRepository.save(iSucursalMapper.toDocument(sucursal)).map(iSucursalMapper::toModel);
    }

}
