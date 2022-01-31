package com.pgt360.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConexionUpdateDto {
    private String idCanal;
    private Long idDispositivo;
    private Date dateConnection;
    private Date hourConnection;
    private Date dateDisconnect;
    private Date hourDisconnect;
    private String state;
}
