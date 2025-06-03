package com.nequi.api.v1.controller;

import com.nequi.api.v1.dto.GenericResponseDto;
import com.nequi.api.v1.dto.request.FranquiciaRequestDto;
import com.nequi.api.v1.dto.request.UpdateNameRequestDto;
import com.nequi.api.v1.dto.response.FranquiciaResponseDto;
import com.nequi.api.v1.handler.FranquiciaHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/franquicia")
@Tag(name = "Franquicia", description = "Endpoints relacionados con las franquicias")
public class FranquiciaController {

    private final FranquiciaHandler franquiciaHandler;

    public FranquiciaController(FranquiciaHandler franquiciaHandler) {
        this.franquiciaHandler = franquiciaHandler;
    }
    @PostMapping
    @Operation(summary = "Crear nueva sucursal", description = "Guarda la sucursal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal guardada"),
            @ApiResponse(responseCode = "500", description = "Ocurrió un error en el proceso")
    })
    public Mono<GenericResponseDto<FranquiciaResponseDto>> createFranquicia(@Valid @RequestBody FranquiciaRequestDto franquiciaRequestDto) {
        return franquiciaHandler.addFranquicia(franquiciaRequestDto);
    }

    @PatchMapping("/{code}/name")
    @Operation(summary = "Actualizar nombre", description = "Actualiza el nombre de la franquicia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nombre actualizado"),
            @ApiResponse(responseCode = "500", description = "Ocurrió un error en el proceso")
    })
    public Mono<ResponseEntity<Void>> updateName(@PathVariable String code,@Valid @RequestBody UpdateNameRequestDto updateNameRequestDto) {
        return franquiciaHandler.updateName(code, updateNameRequestDto.getName()).thenReturn(ResponseEntity.noContent().build());
    }
}
