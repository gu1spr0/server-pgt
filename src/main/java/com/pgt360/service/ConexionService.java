package com.pgt360.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.pgt360.dao.ConexionDao;
import com.pgt360.dao.FlujoDao;
import com.pgt360.dto.ConexionAddDto;
import com.pgt360.dto.ConexionQueryDto;
import com.pgt360.dto.ConexionUpdateDto;
import com.pgt360.dto.DispositivoQueryDto;
import com.pgt360.model.Conexion;
import com.pgt360.model.Dispositivo;
import com.pgt360.util.Constants;
import com.pgt360.util.FileProperties;

public class ConexionService implements ConexionServiceLocal{
	private static Logger logger;
	private Properties properties;
    private FileProperties fileProperties;
    private static ConexionDao conexionDao;
    private DispositivoServiceLocal dispositivoServiceLocal;

    public ConexionService() {
    	try {
        	this.fileProperties = new FileProperties();
			this.properties = this.fileProperties.getConfiguration();
			logger = this.fileProperties.getLogger(ConexionService.class);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
        conexionDao = new ConexionDao();
        this.dispositivoServiceLocal = new DispositivoService();
    }
	@Override
	public ConexionQueryDto agregarConexion(ConexionAddDto pConexionAddDto) {
		if(pConexionAddDto == null) {
			logger.error("pConexionAddDto nulo!");
		}
		Conexion vConexion = new Conexion();
		ConexionQueryDto vConexionQueryDto = new ConexionQueryDto();
		try {
			BeanUtils.copyProperties(vConexion, pConexionAddDto);
			
			DispositivoQueryDto vDispositivoQueryDto= this.dispositivoServiceLocal.buscarDispositivo(pConexionAddDto.getIdDispositivo());
			if(vDispositivoQueryDto==null) {
				logger.error("vDispositivoQueryDto nulo!");
			}
			Dispositivo vDispositivo = new Dispositivo();
			BeanUtils.copyProperties(vDispositivo, vDispositivoQueryDto);
			vConexion.setDispositivo(vDispositivo);
			Conexion vNewConexion = conexionDao.persistirEntidad(vConexion);
			if(vNewConexion==null) {
				logger.error("La entidad vConexion no se guardo correctamente!");
			}
			BeanUtils.copyProperties(vNewConexion, vConexionQueryDto);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Error copy de pConexionAddDto a vConexion!");
		}
		return vConexionQueryDto;
	}

	@Override
	public ConexionQueryDto modificarConexion(int pConexionId, ConexionUpdateDto pConexionUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConexionQueryDto buscarConexion(int pConexionId) {
		if((Integer) pConexionId == null) {
			logger.error("Error pConexionId nulo!");
		}
		Conexion vConexion = conexionDao.obtenerConexionPorId(pConexionId);
		if(vConexion == null) {
			logger.error("No se obtuvo Entidad: Conexion con el Id:"+pConexionId+"!");
		}
		ConexionQueryDto vConexionQueryDto = new ConexionQueryDto();
		try {
			BeanUtils.copyProperties(vConexion, vConexionQueryDto);
			vConexionQueryDto.setIdDispositivo(vConexion.getDispositivo().getId());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Error copy de vConexion a vConexionQueryDto!");
		}
		return vConexionQueryDto;
	}

	@Override
	public ConexionQueryDto buscarConexionPorCodigo(String pCanalId) {
		if(pCanalId.isEmpty() || pCanalId==null) {
			logger.error("Error pCanalId no válido!");
		}
		Conexion vConexion = conexionDao.obtenerConexionPorCodigo(pCanalId);
		if(vConexion == null) {
			logger.error("Entidad Conexion con el id: "+pCanalId+" no encontrado!");
		}
		ConexionQueryDto vConexionQueryDto = new ConexionQueryDto();
		try {
			BeanUtils.copyProperties(vConexion, vConexionQueryDto);
			vConexionQueryDto.setIdDispositivo(vConexion.getDispositivo().getId());
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Error copy de vConexion a vConexionQueryDto!");
		}
		
		return vConexionQueryDto;
	}

	@Override
	public void eliminarConexion(int pConexionId) {
		if((Integer)pConexionId==null){
			logger.error("Id: "+pConexionId+" para la entidad Conexion no válido!");
		}
		Conexion vConexion = conexionDao.obtenerConexionPorId(pConexionId);
		if(vConexion == null) {
			logger.error("vConexion con el id "+pConexionId+" no encontrado!");
		}
		vConexion.setState(Constants.STATE_INACTIVE);
		Conexion vConexionUpdate = conexionDao.actualizarEntidad(vConexion);
		logger.error("Modificación realizada para la entidad Conexion con id: "+pConexionId);
	}

}
