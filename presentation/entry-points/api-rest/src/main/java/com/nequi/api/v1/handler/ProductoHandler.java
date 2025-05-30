package com.nequi.api.v1.handler;

import com.nequi.api.v1.dto.request.ProductoRequestDto;
import com.nequi.api.v1.dto.request.UpdateStockRequestDto;
import com.nequi.api.v1.dto.response.ProductoResponseDto;
import com.nequi.usecase.v1.CreateProductoUseCase;
import com.nequi.usecase.v1.DeleteProductoUseCase;
import com.nequi.usecase.v1.UpdateStockUseCase;
import com.nequi.v1.model.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductoHandler {
    private final CreateProductoUseCase createProductoUseCase;
    private final DeleteProductoUseCase deleteProductoUseCase;
    private final UpdateStockUseCase updateStockUseCase;

    public Mono<ProductoResponseDto> addProducto(ProductoRequestDto productoRequestDto) {
        return createProductoUseCase.execute(buildProducto(productoRequestDto)).flatMap(this::buildProductoResponse);
    }

    public Mono<Void> deleteProducto(String id) {
        return deleteProductoUseCase.execute(id);
    }

    public Mono<Void> updateStock(String id, Integer stock) {
        return updateStockUseCase.execute(id, stock);
    }

    private Producto buildProducto(ProductoRequestDto productoRequestDto) {
        Producto producto = new Producto();
        producto.setName(productoRequestDto.getName());
        producto.setStock(productoRequestDto.getStock());
        producto.setSucursalId(productoRequestDto.getSucursalId());
        return producto;
    }

    private Mono<ProductoResponseDto> buildProductoResponse(Producto producto) {
        ProductoResponseDto productoResponseDto = new ProductoResponseDto();
        productoResponseDto.setCode(producto.getId());
        productoResponseDto.setName(producto.getName());
        productoResponseDto.setStock(producto.getStock());
        return Mono.just(productoResponseDto);
    }
}
