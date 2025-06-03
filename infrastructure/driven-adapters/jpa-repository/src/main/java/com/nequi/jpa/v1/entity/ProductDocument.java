package com.nequi.jpa.v1.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "producto")
@Getter
@Setter
public class ProductDocument {
    @Id
    private String id;
    private String name;
    private Integer stock;
    private ObjectId sucursalId;
}
