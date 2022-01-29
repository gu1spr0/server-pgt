package com.pgt360.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {
	private String codAutoriz;
    private String montoCompra;
    private String numRecibo;
    private String rrn;
    private String terminalId;
    private String fechaTransac;
    private String horaTransac;
    private String codRespuesta;
    private String tipoCuenta;
    private String numCuotas;
    private String ultFourDigits;
    private String msgError;
    private String binTarjeta;
}
