package com.nequi.api.v1.handler;

import com.nequi.api.v1.dto.GenericResponseDto;
import com.nequi.api.v1.dto.request.SucursalRequestDto;
import com.nequi.api.v1.dto.response.SucursalResponseDto;
import com.nequi.usecase.v1.CreateSucursalUseCase;
import com.nequi.v1.model.Sucursal;
import com.nequi.v1.model.error.CustomException;
import com.nequi.v1.model.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class SucursalHandler {
    private final CreateSucursalUseCase createSucursalUseCase;

    public Mono<GenericResponseDto<SucursalResponseDto>> addSucursal(SucursalRequestDto sucursalRequestDto) {
        return createSucursalUseCase.execute(buildSucursal(sucursalRequestDto))
                .map(this::buildSucursalResponse)
                .onErrorResume(CustomException.class, customException ->
                        Mono.just(new GenericResponseDto<>(
                                customException.getResponseCode().getStatus(),
                                customException.getResponseCode().toString(),
                                MessageFormat.format(customException.getResponseCode().getHtmlMessage(), sucursalRequestDto.getName()),
                                customException.getFieldErrors(),
                                null
                        ))
                )
                .onErrorResume(sucursalResponseDto ->
                        Mono.just(new GenericResponseDto<>(
                                ResponseCode.NEQUI003.getStatus(),
                                ResponseCode.NEQUI003.getHtmlMessage(),
                                "Error inesperado al procesar el producto.",
                                null,
                                null
                        ))
                );

    }

    private Sucursal buildSucursal(SucursalRequestDto sucursalRequestDto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setName(sucursalRequestDto.getName().toUpperCase());
        sucursal.setFranquiciaId(sucursalRequestDto.getFranquiciaId());
        return sucursal;
    }

    private GenericResponseDto<SucursalResponseDto> buildSucursalResponse(Sucursal sucursal) {
        SucursalResponseDto sucursalResponseDto = new SucursalResponseDto();
        sucursalResponseDto.setCode(sucursal.getId());
        sucursalResponseDto.setName(sucursal.getName());
        return new GenericResponseDto<>(ResponseCode.NEQUI002.getStatus(),ResponseCode.NEQUI002.getHtmlMessage(),
                "", sucursalResponseDto);
    }
}
