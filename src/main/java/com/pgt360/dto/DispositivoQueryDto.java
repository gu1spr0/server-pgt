package com.pgt360.dto;

import java.util.Date;
import lombok.Data;

@Data
public class DispositivoQueryDto {
    private Long id;
    private String name;
    private String model;
    private String ip;
    private int mpk;
    private String pnr;
    private int terminalId;
    private Long cajaId;
    private Date createdDate;
    private Integer createdBy;
    private String state;
}
