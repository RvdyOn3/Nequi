package com.nequi.jpa.v1.mapper;

import com.nequi.jpa.v1.entity.ProductDocument;
import com.nequi.v1.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = ObjectIdMapper.class
)
public interface IProductoMapper {
    IProductoMapper INSTANCE = Mappers.getMapper(IProductoMapper.class);
    Producto toModel(ProductDocument productDocument);
    ProductDocument toDocument(Producto producto);
}
