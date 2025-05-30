package com.nequi.api.v1.controller;

import com.nequi.api.v1.dto.request.ProductoRequestDto;
import com.nequi.api.v1.dto.request.UpdateStockRequestDto;
import com.nequi.api.v1.dto.response.ProductoResponseDto;
import com.nequi.api.v1.handler.ProductoHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/producto")
@Tag(name = "Producto", description = "Endpoints relacionados productos de las sucursales")
public class ProductoController {

    private final ProductoHandler productoHandler;

    public ProductoController(ProductoHandler productoHandler) {
        this.productoHandler = productoHandler;
    }

    @PostMapping
    public Mono<ResponseEntity<ProductoResponseDto>> createProducto(@Valid @RequestBody ProductoRequestDto productoRequestDto) {
        return productoHandler.addProducto(productoRequestDto).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{code}")
    public Mono<ResponseEntity<Void>> deleteProducto(@PathVariable String code) {
        return productoHandler.deleteProducto(code).then(Mono.just(ResponseEntity.noContent().build()));
    }

    @PatchMapping("/{code}/stock")
    public Mono<ResponseEntity<Void>> updateStock(@PathVariable String code, @RequestBody UpdateStockRequestDto updateStockRequestDto) {
        return productoHandler.updateStock(code, updateStockRequestDto.getStock()).thenReturn(ResponseEntity.noContent().build());
    }
}
