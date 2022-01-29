package com.pgt360.dto;

import lombok.Data;


@Data
public class VentaQueryDto {
	private String codAutoriz;
    private Double montoCompra;
    private String numRecibo;
    private String rrn;
    private int terminalId;
    private String fechaTransac;
    private String horaTransac;
    private int codRespuesta;
    private String tipoCuenta;
    private int numCuotas;
    private int ultFourDigits;
    private String msgError;
    private int binTarjeta;
}
