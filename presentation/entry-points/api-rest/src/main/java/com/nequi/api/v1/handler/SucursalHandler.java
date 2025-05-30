package com.nequi.api.v1.handler;

import com.nequi.api.v1.dto.request.SucursalRequestDto;
import com.nequi.api.v1.dto.response.SucursalResponseDto;
import com.nequi.usecase.v1.CreateSucursalUseCase;
import com.nequi.v1.model.Sucursal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SucursalHandler {
    private final CreateSucursalUseCase createSucursalUseCase;

    public Mono<SucursalResponseDto> addSucursal(SucursalRequestDto sucursalRequestDto) {
        return createSucursalUseCase.execute(buildSucursal(sucursalRequestDto)).flatMap(this::buildSucursalResponse);
    }

    private Sucursal buildSucursal(SucursalRequestDto sucursalRequestDto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setName(sucursalRequestDto.getName());
        sucursal.setFranquiciaId(sucursalRequestDto.getFranquiciaId());
        return sucursal;
    }

    private Mono<SucursalResponseDto> buildSucursalResponse(Sucursal sucursal) {
        SucursalResponseDto sucursalResponseDto = new SucursalResponseDto();
        sucursalResponseDto.setCode(sucursal.getId());
        sucursalResponseDto.setName(sucursal.getName());
        return Mono.just(sucursalResponseDto);
    }
}
