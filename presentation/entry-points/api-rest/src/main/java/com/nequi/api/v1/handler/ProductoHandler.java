package com.nequi.api.v1.handler;

import com.nequi.api.v1.dto.GenericResponseDto;
import com.nequi.api.v1.dto.request.ProductoRequestDto;
import com.nequi.api.v1.dto.response.ProductoResponseDto;
import com.nequi.api.v1.dto.response.ProductoSucursalResponseDto;
import com.nequi.usecase.v1.CreateProductoUseCase;
import com.nequi.usecase.v1.DeleteProductoUseCase;
import com.nequi.usecase.v1.GetProductBySucursalUseCase;
import com.nequi.usecase.v1.UpdateStockUseCase;
import com.nequi.v1.model.Producto;
import com.nequi.v1.model.ProductsBySucursal;
import com.nequi.v1.model.error.CustomException;
import com.nequi.v1.model.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class ProductoHandler {
    private final CreateProductoUseCase createProductoUseCase;
    private final DeleteProductoUseCase deleteProductoUseCase;
    private final UpdateStockUseCase updateStockUseCase;
    private final GetProductBySucursalUseCase getProductBySucursalUseCase;

    public Mono<GenericResponseDto<ProductoResponseDto>> addProducto(ProductoRequestDto productoRequestDto) {
        return createProducto(productoRequestDto)
                .map(this::buildProductoResponse)
                .onErrorResume(CustomException.class, customException ->
                        Mono.just(buildErrorResponse(customException, productoRequestDto.getName()))
                )
                .onErrorResume(throwable ->
                        Mono.just(buildUnexpectedErrorResponse())
                );
    }

    public Mono<Void> deleteProducto(String id) {
        return deleteProductoUseCase.execute(id);
    }

    public Mono<Void> updateStock(String id, Integer stock) {
        return updateStockUseCase.execute(id, stock);
    }

    public Flux<GenericResponseDto<ProductoSucursalResponseDto>> getProductosConMayorStockPorSucursal(String franquiciaId) {
        return getProductBySucursalUseCase.execute(franquiciaId)
                .map(this::buildProductoBySucursalResponse)
                .onErrorResume(CustomException.class, customException ->
                        Mono.just(new GenericResponseDto<>(
                                customException.getResponseCode().getStatus(),
                                customException.getResponseCode().toString(),
                                MessageFormat.format(customException.getResponseCode().getHtmlMessage(), franquiciaId),
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

    private Mono<Producto> createProducto(ProductoRequestDto productoRequestDto) {
        return createProductoUseCase.execute(buildProducto(productoRequestDto));
    }

    private Producto buildProducto(ProductoRequestDto productoRequestDto) {
        Producto producto = new Producto();
        producto.setName(productoRequestDto.getName().toUpperCase());
        producto.setStock(productoRequestDto.getStock());
        producto.setSucursalId(productoRequestDto.getSucursalId());
        return producto;
    }

    private GenericResponseDto<ProductoResponseDto> buildProductoResponse(Producto producto) {
        ProductoResponseDto productoResponseDto = new ProductoResponseDto();
        productoResponseDto.setCode(producto.getId());
        productoResponseDto.setName(producto.getName());
        productoResponseDto.setStock(producto.getStock());

        return new GenericResponseDto<>(ResponseCode.NEQUI002.getStatus(),ResponseCode.NEQUI002.getHtmlMessage(),
                "", productoResponseDto);
    }

    private GenericResponseDto<ProductoSucursalResponseDto> buildProductoBySucursalResponse(ProductsBySucursal producto) {
        ProductoSucursalResponseDto productoResponseDto = new ProductoSucursalResponseDto();
        productoResponseDto.setCode(producto.getId());
        productoResponseDto.setName(producto.getName());
        productoResponseDto.setStock(producto.getStock());
        productoResponseDto.setSucursalCode(producto.getSucursalId());
        productoResponseDto.setNameSucursal(producto.getNameSucursal());

        return new GenericResponseDto<>(ResponseCode.NEQUI008.getStatus(),ResponseCode.NEQUI008.getHtmlMessage(),
                "", productoResponseDto);
    }

    private GenericResponseDto<ProductoResponseDto> buildErrorResponse(CustomException ex, String productName) {
        return new GenericResponseDto<>(
                ex.getResponseCode().getStatus(),
                ex.getResponseCode().toString(),
                MessageFormat.format(ex.getResponseCode().getHtmlMessage(), productName),
                ex.getFieldErrors(),
                null
        );
    }

    private GenericResponseDto<ProductoResponseDto> buildUnexpectedErrorResponse() {
        return new GenericResponseDto<>(
                ResponseCode.NEQUI003.getStatus(),
                ResponseCode.NEQUI003.getHtmlMessage(),
                "Error inesperado al procesar el producto.",
                null,
                null
        );
    }
}
