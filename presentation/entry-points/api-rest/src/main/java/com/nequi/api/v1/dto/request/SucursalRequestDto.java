package com.nequi.api.v1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SucursalRequestDto {
    @NotBlank(message = "El código de la franquicia no debe estar vacío")
    private String franquiciaId;
    @NotBlank(message = "El nombre de la sucursal no puede estar vacío")
    private String name;
}
