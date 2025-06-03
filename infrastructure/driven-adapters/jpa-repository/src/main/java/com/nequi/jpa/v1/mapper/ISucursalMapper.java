package com.nequi.jpa.v1.mapper;

import com.nequi.jpa.v1.entity.SucursalDocument;
import com.nequi.v1.model.Sucursal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = ObjectIdMapper.class
)
public interface ISucursalMapper {
    ISucursalMapper INSTANCE = Mappers.getMapper(ISucursalMapper.class);
    Sucursal toModel(SucursalDocument sucursalDocument);
    SucursalDocument toDocument(Sucursal sucursal);
}
