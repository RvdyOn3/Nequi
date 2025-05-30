package com.nequi.jpa.v1.mapper;

import com.nequi.jpa.v1.entity.FranquiciaDocument;
import com.nequi.v1.model.Franquicia;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IFranquiciaMapper {

    Franquicia toModel(FranquiciaDocument franquiciaDocument);
    FranquiciaDocument toDocument(Franquicia franquicia);
}
