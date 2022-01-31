package com.pgt360.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.pgt360.dao.ConexionDao;
import com.pgt360.dao.DispositivoDao;
import com.pgt360.dto.DispositivoQueryDto;
import com.pgt360.model.Dispositivo;
import com.pgt360.util.FileProperties;

public class DispositivoService implements DispositivoServiceLocal{

	private static Logger logger;
	private Properties properties;
    private FileProperties fileProperties;
    private static DispositivoDao dispositivoDao;
    
    public DispositivoService() {
    	try {
        	this.fileProperties = new FileProperties();
			this.properties = this.fileProperties.getConfiguration();
			logger = this.fileProperties.getLogger(DispositivoService.class);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
    	dispositivoDao = new DispositivoDao();
    }
    
	@Override
	public DispositivoQueryDto buscarDispositivo(int pDispositivoId) {
		if((Integer)pDispositivoId==null) {
			logger.error("pDispositivoId nulo!");
		}
		Dispositivo vDispositivo = dispositivoDao.obtenerDispositivoPorId(pDispositivoId);
		if(vDispositivo==null) {
			logger.error("Entidad vDispositivo no existente!");
		}
		DispositivoQueryDto vDispositivoQueryDto = new DispositivoQueryDto();
		try {
			BeanUtils.copyProperties(vDispositivoQueryDto, vDispositivo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("Error copy de vDispositivo a vDispositivoQueryDto!");
		}
		return vDispositivoQueryDto;
		
	}

}
