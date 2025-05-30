package com.nequi.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto {
    private String id;
    private String name;
    private Integer stock;
    private String sucursalId;
}
