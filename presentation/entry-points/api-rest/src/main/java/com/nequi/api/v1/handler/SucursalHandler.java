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
        return createSucursal(sucursalRequestDto)
                .map(this::buildSucursalResponse)
                .onErrorResume(CustomException.class, customException ->
                        Mono.just(buildErrorResponse(customException, sucursalRequestDto.getName()))
                )
                .onErrorResume(sucursalResponseDto ->
                        Mono.just(buildUnexpectedErrorResponse())
                );
    }

    private Mono<Sucursal> createSucursal(SucursalRequestDto sucursalRequestDto){
        return createSucursalUseCase.execute(buildSucursal(sucursalRequestDto));
    }

    private GenericResponseDto<SucursalResponseDto> buildErrorResponse(CustomException ex, String sucursalName) {
        return new GenericResponseDto<>(
                ex.getResponseCode().getStatus(),
                ex.getResponseCode().toString(),
                MessageFormat.format(ex.getResponseCode().getHtmlMessage(), sucursalName),
                ex.getFieldErrors(),
                null
        );
    }

    private GenericResponseDto<SucursalResponseDto> buildUnexpectedErrorResponse() {
        return new GenericResponseDto<>(
                ResponseCode.NEQUI003.getStatus(),
                ResponseCode.NEQUI003.toString(),
                "Error inesperado al procesar la sucursal.",
                null,
                null
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
