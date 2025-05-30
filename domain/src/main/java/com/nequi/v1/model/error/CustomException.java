package com.nequi.v1.model.error;

import com.nequi.v1.model.util.ResponseCode;

import java.io.Serial;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 3508567824775716466L;
    private final ResponseCode responseCode;
    private final List<FieldErrorModel> fieldErrorModels;

    public CustomException(ResponseCode responseCode, String... params) {
        super(MessageFormat.format(responseCode.getHtmlMessage(), (Object[]) params));
        this.responseCode = responseCode;
        this.fieldErrorModels = new ArrayList<>();
    }

    public CustomException(ResponseCode responseCode, List<FieldErrorModel> fieldErrorModels) {
        // Puedes poner un mensaje genérico para la superclase
        super(MessageFormat.format(responseCode.getHtmlMessage(), "Errores de validación de campos"));
        this.responseCode = responseCode;
        // Asignamos la lista de FieldErrorModel directamente
        this.fieldErrorModels = fieldErrorModels != null ? fieldErrorModels : new ArrayList<>();
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public List<FieldErrorModel> getFieldErrors() {
        return fieldErrorModels;
    }

    public void addFieldError(FieldErrorModel fieldErrorModel) {
        this.fieldErrorModels.add(fieldErrorModel);
    }
}
