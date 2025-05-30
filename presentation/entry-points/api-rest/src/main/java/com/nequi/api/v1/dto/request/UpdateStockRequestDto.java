package com.nequi.api.v1.dto.request;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStockRequestDto {

    @PositiveOrZero(message = "El stock debe ser un número positivo o cero.")
    private Integer stock;
}
