package com.nequi.usecase.v1;

import com.nequi.v1.gateway.IFranquiciaGateway;
import com.nequi.v1.model.Franquicia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateFranquiciaUseCase {

    private final IFranquiciaGateway franquiciaRepository;

    public Mono<Franquicia> execute(Franquicia franquicia) {
        return franquiciaRepository.addFranquicia(franquicia);
    }
}
