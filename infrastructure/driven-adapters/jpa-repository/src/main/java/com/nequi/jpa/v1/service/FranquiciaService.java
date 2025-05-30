package com.nequi.jpa.v1.service;

import com.nequi.jpa.v1.entity.FranquiciaDocument;
import com.nequi.jpa.v1.mapper.IFranquiciaMapper;
import com.nequi.jpa.v1.repository.FranquiciaMongoRepository;
import com.nequi.v1.gateway.IFranquiciaGateway;
import com.nequi.v1.model.Franquicia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranquiciaService implements IFranquiciaGateway {

    private final FranquiciaMongoRepository franquiciaMongoRepository;
    private final IFranquiciaMapper iFranquiciaMapper;

    public Mono<Franquicia> addFranquicia(Franquicia franquicia) {
        FranquiciaDocument franquiciaDocument = iFranquiciaMapper.toDocument(franquicia);
        return franquiciaMongoRepository.save(franquiciaDocument).map(iFranquiciaMapper::toModel);
    }
}
