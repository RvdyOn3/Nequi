package com.nequi.jpa.v1.mapper;

import com.nequi.jpa.v1.entity.SucursalDocument;
import com.nequi.v1.model.Sucursal;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface ISucursalMapper {
    Sucursal toModel(SucursalDocument sucursalDocument);
    SucursalDocument toDocument(Sucursal sucursal);
}
