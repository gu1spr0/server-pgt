package com.pgt360.service;

import com.pgt360.dto.FlujoAddDto;
import com.pgt360.dto.FlujoQueryDto;
import com.pgt360.dto.FlujoUpdateDto;

public interface FlujoServiceLocal {
	public FlujoQueryDto agregarFlujo(FlujoAddDto pFlujoAddDto);
	public FlujoQueryDto modificarFlujo(int pFlujoId, FlujoUpdateDto pFlujoUpdateDto);
	public FlujoQueryDto buscarFlujo(int pFlujoId);
	public void eliminarFlujo(int pFlujoId);
}
