package com.nequi.jpa.v1.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sucursal")
@Getter
@Setter
public class SucursalDocument {
    @Id
    private String id;
    private String name;
    private ObjectId franquiciaId;
}
