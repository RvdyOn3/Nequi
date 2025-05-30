package com.nequi.api.v1.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoSucursalResponseDto {
    private String code;
    private String name;
    private Integer stock;
    private String sucursalCode;
    private String nameSucursal;
}
