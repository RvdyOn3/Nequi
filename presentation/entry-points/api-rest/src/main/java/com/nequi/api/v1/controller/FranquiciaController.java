package com.nequi.api.v1.controller;

import com.nequi.api.v1.dto.request.FranquiciaRequestDto;
import com.nequi.api.v1.dto.response.FranquiciaResponseDto;
import com.nequi.api.v1.handler.FranquiciaHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public Mono<ResponseEntity<FranquiciaResponseDto>> createFranquicia(@Valid @RequestBody FranquiciaRequestDto franquiciaRequestDto) {
        return franquiciaHandler.addFranquicia(franquiciaRequestDto).map(ResponseEntity::ok);
    }
}
