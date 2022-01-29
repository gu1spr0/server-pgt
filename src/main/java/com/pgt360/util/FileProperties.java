package com.pgt360.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class FileProperties {
	private static Logger logger;
	private Properties properties;
	private boolean controlLogProceso = true;
	public FileProperties() {
		try {
			String log4jConfigFile = System.getProperty("user.dir") + File.separator +"configuracion"+File.separator+"log4j.properties";
			File file = new File(log4jConfigFile);
			PropertyConfigurator.configure(new FileInputStream(file));
			logger = Logger.getLogger(FileProperties.class);
			logger.debug("Archivo de configuración de Log encontrado!");
		} catch(FileNotFoundException ex) {
			this.controlLogProceso = false;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Logger getLogger(Class clazz) {
		logger = Logger.getLogger(clazz);
		logger.debug("Se retornó Logger para la clase: "+clazz.getName());
		return logger;
	}
	public Properties getConfiguration() throws FileNotFoundException, IOException {
		try {
			String pathFile = System.getProperty("user.dir") + File.separator +"configuracion"+File.separator+"parameters.properties" ;
			File file = new File(pathFile);
			this.properties = new Properties();
			this.properties.load(new FileInputStream(file));
		}catch(FileNotFoundException fn) {
			logger.error("Archivo de configuraciones no encontrado!");
		}catch(IOException io) {
			logger.error("Error de lectura de archivo!");
		}
		return this.properties;
	}
	public boolean isControlLogProceso() {
		return this.controlLogProceso;
	}
}
