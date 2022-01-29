package com.pgt360.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlujoQueryDto {
	private Long id;
    private int codeFlow;
    private String description;
    private int step;
    private int size;
    private Long idCaja;
    private Date createdDate;
    private Integer createdBy;
    private String state;
}
