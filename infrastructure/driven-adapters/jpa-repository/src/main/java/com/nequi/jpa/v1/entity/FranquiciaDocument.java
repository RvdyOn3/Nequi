package com.nequi.jpa.v1.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "franquicia")
@Getter
@Setter
public class FranquiciaDocument {
    @Id
    private String id;
    private String name;
}
