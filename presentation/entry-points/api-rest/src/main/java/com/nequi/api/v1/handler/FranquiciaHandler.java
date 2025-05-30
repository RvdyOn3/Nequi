package com.nequi.api.v1.handler;

import com.nequi.api.v1.dto.request.FranquiciaRequestDto;
import com.nequi.api.v1.dto.response.FranquiciaResponseDto;
import com.nequi.usecase.v1.CreateFranquiciaUseCase;
import com.nequi.v1.model.Franquicia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranquiciaHandler {

    private final CreateFranquiciaUseCase createFranquiciaUseCase;

    public Mono<FranquiciaResponseDto> addFranquicia(FranquiciaRequestDto franquiciaRequestDto) {
        return createFranquiciaUseCase.execute(buildFranquicia(franquiciaRequestDto)).flatMap(this::buildFranquiciaResponse);
    }

    private Franquicia buildFranquicia(FranquiciaRequestDto franquiciaRequestDto) {
        Franquicia franquicia = new Franquicia();
        franquicia.setName(franquiciaRequestDto.getName());
        return franquicia;
    }

    private Mono<FranquiciaResponseDto> buildFranquiciaResponse(Franquicia franquicia) {
        FranquiciaResponseDto franquiciaResponseDto = new FranquiciaResponseDto();
        franquiciaResponseDto.setId(franquicia.getId());
        franquiciaResponseDto.setName(franquicia.getName());
        return Mono.just(franquiciaResponseDto);
    }
}
