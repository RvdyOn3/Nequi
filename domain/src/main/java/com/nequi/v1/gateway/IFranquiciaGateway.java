package com.nequi.v1.gateway;

import com.nequi.v1.model.Franquicia;
import reactor.core.publisher.Mono;

public interface IFranquiciaGateway {
    Mono<Franquicia> addFranquicia(Franquicia franquicia);
    Mono<Franquicia> getFranquiciaById(String id);
    Mono<Void> updateFranquiciaName(String id, String name);
}
