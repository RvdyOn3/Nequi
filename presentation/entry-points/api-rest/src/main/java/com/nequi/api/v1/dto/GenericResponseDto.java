package com.nequi.api.v1.dto;

import com.nequi.v1.model.error.FieldErrorModel;
import com.nequi.v1.model.util.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de salida generica")
public class GenericResponseDto<T> {

    @Schema(description = "Código interno de la respuesta")
    private String responseCode;

    @Schema(description = "Código http de la respuesta")
    private int status;

    @Schema(description = "Mensaje adicional de la respuesta")
    private String responseMessage;

    @Schema(description = "Contenido de la respuesta")
    private T data;

    @Schema(description = "Lista de campos de errores de validación")
    private List<FieldErrorModel> fieldErrorModels;


    public GenericResponseDto(ResponseCode responseCode, T data, List<FieldErrorModel> fieldErrorModels) {
        this.responseCode = responseCode.toString();
        this.status = responseCode.getStatus();
        this.responseMessage = responseCode.getHtmlMessage();
        this.data = data;
        this.fieldErrorModels = fieldErrorModels;
    }
    public GenericResponseDto(int status, String responseCode, String responseMessage, List<FieldErrorModel> fieldErrorModels, T data) {
        this.status = status;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.fieldErrorModels = fieldErrorModels;
        this.data = data;
    }


    public GenericResponseDto(int status, String responseCode, String responseMessage, T data) {
        this(status, responseCode, responseMessage, null, data);
    }

    public GenericResponseDto(int status, String responseCode, String responseMessage) {
        this(status, responseCode, responseMessage, null, null);

    }

    public GenericResponseDto(ResponseCode responseCode) {
        this(responseCode.getStatus(), responseCode.getHtmlMessage(), null, null);
    }

}
