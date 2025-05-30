package com.nequi.api.v1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequestDto {
    @NotBlank(message = "El código de la sucursal no debe estar vacío")
    private String sucursalId;
    @NotBlank(message = "El nombre del producto no debe estar vacío")
    private String name;

    private Integer stock;
}
