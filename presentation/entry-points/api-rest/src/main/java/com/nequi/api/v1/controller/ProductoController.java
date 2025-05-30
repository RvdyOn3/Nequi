package com.nequi.api.v1.controller;

import com.nequi.api.v1.dto.GenericResponseDto;
import com.nequi.api.v1.dto.request.ProductoRequestDto;
import com.nequi.api.v1.dto.request.UpdateStockRequestDto;
import com.nequi.api.v1.dto.response.ProductoResponseDto;
import com.nequi.api.v1.dto.response.ProductoSucursalResponseDto;
import com.nequi.api.v1.handler.ProductoHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Producto", description = "Endpoints relacionados productos de las sucursales")
public class ProductoController {

    private final ProductoHandler productoHandler;

    public ProductoController(ProductoHandler productoHandler) {
        this.productoHandler = productoHandler;
    }

    @PostMapping("/producto")
    @Operation(summary = "Crear nuevo producto", description = "Guarda el producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto guardado"),
            @ApiResponse(responseCode = "500", description = "Ocurrió un error en el proceso")
    })
    public Mono<GenericResponseDto<ProductoResponseDto>> createProducto(@Valid @RequestBody ProductoRequestDto productoRequestDto) {
        return productoHandler.addProducto(productoRequestDto);
    }

    @DeleteMapping("/{code}")
    @Operation(summary = "Eliminar producto", description = "Elimina el producto guardado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado"),
            @ApiResponse(responseCode = "500", description = "Ocurrió un error en el proceso")
    })
    public Mono<ResponseEntity<Void>> deleteProducto(@PathVariable String code) {
        return productoHandler.deleteProducto(code).then(Mono.just(ResponseEntity.noContent().build()));
    }

    @PatchMapping("/{code}/stock")
    @Operation(summary = "Actualizar stock del producto", description = "Actualiza el inventario del producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock actualizado"),
            @ApiResponse(responseCode = "500", description = "Ocurrió un error en el proceso")
    })
    public Mono<ResponseEntity<Void>> updateStock(@PathVariable String code, @RequestBody UpdateStockRequestDto updateStockRequestDto) {
        return productoHandler.updateStock(code, updateStockRequestDto.getStock()).thenReturn(ResponseEntity.noContent().build());
    }

    @GetMapping("/franquicia/{franquiciaId}/producto/mayor-stock-por-sucursal")
    public Flux<GenericResponseDto<ProductoSucursalResponseDto>> getProductosConMayorStockPorSucursal(@PathVariable String franquiciaId) {
        return productoHandler.getProductosConMayorStockPorSucursal(franquiciaId);
    }
}
