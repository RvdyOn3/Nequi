package com.nequi.api.v1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequestDto {
    @NotBlank(message = "El código de la sucursal no debe estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "El nombre solo puede contener letras, números.")
    private String sucursalId;

    @NotBlank(message = "El nombre del producto no debe estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "El nombre solo puede contener letras, números y espacios.")
    private String name;

    @PositiveOrZero(message = "El stock debe ser un número positivo o cero.")
    private Integer stock;
}
