package com.pgt360.dto;

import java.util.Date;

import com.pgt360.model.Dispositivo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConexionAddDto {
    private String idCanal;
    private int idDispositivo;
    private Date dateConnection;
    private Date hourConnection;
    private Date dateDisconnect;
    private Date hourDisconnect;
    private String state;
}
