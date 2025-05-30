package com.nequi.v1.model.error;
import java.io.Serial;
import java.io.Serializable;

public record FieldErrorModel(String field, String error) implements Serializable {
    @Serial
    private static final long serialVersionUID = 3708567824775716466L;
}