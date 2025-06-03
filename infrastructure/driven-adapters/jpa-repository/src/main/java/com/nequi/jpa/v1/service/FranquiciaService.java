package com.nequi.jpa.v1.service;

import com.nequi.jpa.v1.entity.FranquiciaDocument;
import com.nequi.jpa.v1.mapper.IFranquiciaMapper;
import com.nequi.jpa.v1.repository.FranquiciaMongoRepository;
import com.nequi.v1.gateway.IFranquiciaGateway;
import com.nequi.v1.model.Franquicia;
import com.nequi.v1.model.error.CustomException;
import com.nequi.v1.model.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranquiciaService implements IFranquiciaGateway {

    private final FranquiciaMongoRepository franquiciaMongoRepository;
    private final IFranquiciaMapper iFranquiciaMapper;

    public Mono<Franquicia> addFranquicia(Franquicia franquicia) {
        return franquiciaMongoRepository.findByName(franquicia.getName().toUpperCase())
                .flatMap(existingFranquicia ->
                        Mono.<Franquicia>error(new CustomException(ResponseCode.NEQUI001, "La franquicia ya se encuentra registrada.")))
                .switchIfEmpty(Mono.defer(() ->
                        franquiciaMongoRepository.save(iFranquiciaMapper.toDocument(franquicia))
                                .map(iFranquiciaMapper::toModel)
                ))
                .onErrorResume(throwable -> {
                    if (throwable instanceof CustomException) {
                        return Mono.<Franquicia>error((CustomException) throwable);
                    }
                    return Mono.<Franquicia>error(new CustomException(ResponseCode.NEQUI003, "Error inesperado al procesar la franquicia."));
                });
    }

    public Mono<Franquicia> getFranquiciaById(String id) {
        return franquiciaMongoRepository.findById(id)
                .map(iFranquiciaMapper::toModel)
                .switchIfEmpty(Mono.error(new CustomException(ResponseCode.NEQUI006, "Franquicia no encontrada.")));
    }

    public Mono<Void> updateFranquiciaName(String id, String name) {
        return franquiciaMongoRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(ResponseCode.NEQUI006, "Franquicia no encontrada.")))
                .flatMap(existingFranquicia -> {
                    existingFranquicia.setName(name.toUpperCase());
                    return franquiciaMongoRepository.save(existingFranquicia);
                })
                .then();
    }
}
