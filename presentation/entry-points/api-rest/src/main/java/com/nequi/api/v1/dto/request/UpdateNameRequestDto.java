package com.nequi.api.v1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNameRequestDto {
    @NotBlank(message = "El nombre de la franquicia no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "El nombre solo puede contener letras, números y espacios.")
    private String name;
}
