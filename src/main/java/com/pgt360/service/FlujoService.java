package com.pgt360.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.pgt360.dao.FlujoDao;
import com.pgt360.dto.FlujoAddDto;
import com.pgt360.dto.FlujoQueryDto;
import com.pgt360.dto.FlujoUpdateDto;
import com.pgt360.model.Flujo;
import com.pgt360.util.Constants;
import com.pgt360.util.FileProperties;

public class FlujoService implements FlujoServiceLocal{
	private static Logger logger;
	private Properties properties;
    private FileProperties fileProperties;
    private static FlujoDao flujoDao;
	public FlujoService() {
        try {
        	this.fileProperties = new FileProperties();
			this.properties = this.fileProperties.getConfiguration();
			logger = this.fileProperties.getLogger(FlujoService.class);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
        flujoDao = new FlujoDao();
	}

	@Override
	public FlujoQueryDto agregarFlujo(FlujoAddDto pFlujoAddDto) {
		if(pFlujoAddDto == null) {
			logger.error("Error en el objeto FlujoAddDto!");
		}
		Flujo vFlujo = new Flujo();
		FlujoQueryDto pFlujoQueryDto = new FlujoQueryDto();
		try {
			BeanUtils.copyProperties(pFlujoAddDto, vFlujo);
			Flujo nFlujo = flujoDao.persistirEntidad(vFlujo);
			BeanUtils.copyProperties(nFlujo, pFlujoQueryDto);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Error en el copy de objetos pFlujoAddDto y flujo!");
		}
		return pFlujoQueryDto;
	}

	@Override
	public FlujoQueryDto modificarFlujo(int pFlujoId, FlujoUpdateDto pFlujoUpdateDto) {
		if((Integer) pFlujoId == null) {
			logger.error("Error pFlujoId nulo");
		}
		if(pFlujoUpdateDto == null) {
			logger.error("Error pFlujoUpdateDto nulo");
		}
		Flujo vFlujo = new Flujo();
		FlujoQueryDto vFlujoQueryDto = new FlujoQueryDto();
		try {
			BeanUtils.copyProperties(pFlujoUpdateDto, vFlujo);
			Flujo vFlujoUpdate = flujoDao.actualizarEntidad(vFlujo);
			
			BeanUtils.copyProperties(vFlujoUpdate, vFlujoQueryDto);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Error en el copy de pFlujoUpdateDto a vFlujo!");
		}
		return vFlujoQueryDto;
	}

	@Override
	public FlujoQueryDto buscarFlujo(int pFlujoId) {
		if((Integer)pFlujoId==null) {
			logger.error("Error pFlujoId nulo!");
		}
		Flujo vFlujo = flujoDao.obtenerFlujoPorId(pFlujoId);
		if(vFlujo == null) {
			logger.error("Error Entidad flujo con id "+pFlujoId+" no encontrado!");
		}
		FlujoQueryDto vFlujoQueryDto = new FlujoQueryDto();
		try {
			Flujo vFlujoUpdate = flujoDao.actualizarEntidad(vFlujo);
			BeanUtils.copyProperties(vFlujoUpdate, vFlujoQueryDto);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Error en el copy de vFlujoUpdate a vFlujoQueryDto!");
		}
		return vFlujoQueryDto;
	}

	@Override
	public void eliminarFlujo(int pFlujoId) {
		if((Integer)pFlujoId==null) {
			logger.error("Error pFlujoId nulo!");
		}
		FlujoQueryDto vFlujoQueryDto = this.buscarFlujo(pFlujoId);
		Flujo vFlujo = new Flujo();
		try {
			BeanUtils.copyProperties(vFlujoQueryDto, vFlujo);
			vFlujo.setState(Constants.STATE_INACTIVE);
			flujoDao.actualizarEntidad(vFlujo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Error en el copy de vFlujoQueryDto a vFlujo!");
		}
		
		
	}
	
	
}
