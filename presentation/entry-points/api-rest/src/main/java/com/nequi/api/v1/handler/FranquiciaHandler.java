package com.nequi.api.v1.handler;

import com.nequi.api.v1.dto.GenericResponseDto;
import com.nequi.api.v1.dto.request.FranquiciaRequestDto;
import com.nequi.api.v1.dto.response.FranquiciaResponseDto;
import com.nequi.usecase.v1.CreateFranquiciaUseCase;
import com.nequi.v1.model.Franquicia;
import com.nequi.v1.model.error.CustomException;
import com.nequi.v1.model.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class FranquiciaHandler {

    private final CreateFranquiciaUseCase createFranquiciaUseCase;

    public Mono<GenericResponseDto<FranquiciaResponseDto>> addFranquicia(FranquiciaRequestDto franquiciaRequestDto) {
        return createFranquiciaUseCase.execute(buildFranquicia(franquiciaRequestDto))
                .map(this::buildFranquiciaResponse)
                .onErrorResume(CustomException.class, customException ->
                        Mono.just(new GenericResponseDto<>(
                                customException.getResponseCode().getStatus(),
                                customException.getResponseCode().toString(),
                                MessageFormat.format(customException.getResponseCode().getHtmlMessage(), franquiciaRequestDto.getName()),
                                customException.getFieldErrors(),
                                null
                        ))
                )
                .onErrorResume(throwable ->
                        Mono.just(new GenericResponseDto<>(
                                ResponseCode.NEQUI003.getStatus(),
                                ResponseCode.NEQUI003.getHtmlMessage(),
                                "Error inesperado al procesar el producto.",
                                null,
                                null
                        ))
                );
    }

    private Franquicia buildFranquicia(FranquiciaRequestDto franquiciaRequestDto) {
        Franquicia franquicia = new Franquicia();
        franquicia.setName(franquiciaRequestDto.getName().toUpperCase());
        return franquicia;
    }

    private GenericResponseDto<FranquiciaResponseDto> buildFranquiciaResponse(Franquicia franquicia) {
        FranquiciaResponseDto franquiciaResponseDto = new FranquiciaResponseDto();
        franquiciaResponseDto.setCode(franquicia.getId());
        franquiciaResponseDto.setName(franquicia.getName());
        return new GenericResponseDto<>(ResponseCode.NEQUI002.getStatus(),ResponseCode.NEQUI002.getHtmlMessage(),
                "", franquiciaResponseDto);
    }
}
