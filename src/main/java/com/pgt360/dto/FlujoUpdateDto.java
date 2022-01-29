package com.pgt360.dto;

import lombok.Data;

@Data
public class FlujoUpdateDto {
	private int codeFlow;
    private String description;
    private int step;
    private int size;
    private Long idCaja;
    private String state;
}
