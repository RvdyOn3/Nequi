package com.nequi.usecase.v1;

import com.nequi.v1.gateway.IFranquiciaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpdateNameFranquiciaUseCase {
    private final IFranquiciaGateway franquiciaGateway;

    public Mono<Void> execute(String id, String name) {
        return franquiciaGateway.updateFranquiciaName(id, name);
    }
}
