package com.nequi.api.v1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FranquiciaRequestDto {

    @NotBlank(message = "El nombre de la franquicia no puede estar vacío")
    private String name;
}
