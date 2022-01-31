package com.pgt360.service;

import com.pgt360.dto.ConexionAddDto;
import com.pgt360.dto.ConexionQueryDto;
import com.pgt360.dto.ConexionUpdateDto;

public interface ConexionServiceLocal {
	public ConexionQueryDto agregarConexion(ConexionAddDto pConexionAddDto);
	public ConexionQueryDto modificarConexion(int pConexionId, ConexionUpdateDto pConexionUpdateDto);
	public ConexionQueryDto buscarConexion(int pConexionId);
	public ConexionQueryDto buscarConexionPorCodigo(String pCanalId);
	public void eliminarConexion(int pConexionId);
}
