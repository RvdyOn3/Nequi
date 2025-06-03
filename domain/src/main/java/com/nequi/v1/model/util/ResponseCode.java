package com.nequi.v1.model.util;

public enum ResponseCode {

    NEQUI001(500, "Error en la creación, el nombre {0} ya existe."),
    NEQUI002(200, "Registro creado correctamente."),
    NEQUI003(500, "Ocurrió un error en el proceso."),
    NEQUI004(400, "Error en validación."),
    NEQUI005(404, "No se encontraron datos."),
    NEQUI006(404, "No se encontraron datos. La franquicia no existe."),
    NEQUI007(404, "No se encontraron datos. La sucursal no existe."),
    NEQUI008(200, "Consulta ejecutada.");

    private final int status;
    private final String htmlMessage;

    ResponseCode(int status, String htmlMessage) {
        this.status = status;
        this.htmlMessage = htmlMessage;
    }

    public int getStatus() {
        return this.status;
    }

    public String getHtmlMessage() {
        return this.htmlMessage;
    }
}
