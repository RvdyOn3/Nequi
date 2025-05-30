package com.nequi.jpa.v1.mapper;

import com.nequi.jpa.v1.entity.ProductDocument;
import com.nequi.v1.model.Producto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IProductoMapper {
    Producto toModel(ProductDocument productDocument);
    ProductDocument toDocument(Producto producto);
}
