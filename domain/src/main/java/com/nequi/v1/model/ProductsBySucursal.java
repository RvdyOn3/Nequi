package com.nequi.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsBySucursal {
    private String id;
    private String name;
    private Integer stock;
    private String sucursalId;
    private String nameSucursal;
}
