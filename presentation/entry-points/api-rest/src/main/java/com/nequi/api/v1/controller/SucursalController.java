package com.nequi.api.v1.controller;

import com.nequi.api.v1.dto.GenericResponseDto;
import com.nequi.api.v1.dto.request.SucursalRequestDto;
import com.nequi.api.v1.dto.response.SucursalResponseDto;
import com.nequi.api.v1.handler.SucursalHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/sucursal")
@Tag(name = "Sucursal", description = "Endpoints relacionados con las sucursales de las franquicias")
public class SucursalController {

    private final SucursalHandler sucursalHandler;

    private SucursalController(SucursalHandler sucursalHandler) {
        this.sucursalHandler = sucursalHandler;
    }

    @PostMapping
    public Mono<GenericResponseDto<SucursalResponseDto>> createSucursal(@Valid @RequestBody SucursalRequestDto sucursalRequestDto) {
        return sucursalHandler.addSucursal(sucursalRequestDto);
    }
}
