package com.pgt360.dao;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pgt360.dao.comun.GenericDao;
import com.pgt360.model.Dispositivo;
import com.pgt360.model.Flujo;
import com.pgt360.util.Constants;

public class DispositivoDao extends GenericDao<Dispositivo>{
	@PersistenceContext
	private EntityManager em;
	
	public DispositivoDao() {
		super(Dispositivo.class);
	}
	
	public Dispositivo obtenerDispositivoPorId(int idDispositivo) {
		StringBuffer sbfConsulta = new StringBuffer("");
		HashMap<String, Object> mapParametros = new HashMap<>();
		sbfConsulta.append("SELECT d ");
		sbfConsulta.append("FROM Dispositivo d ");
		sbfConsulta.append("WHERE d.estado = :estado ");
		sbfConsulta.append("AND d.id = :idDispositivo ");
		mapParametros.put("estado", Constants.STATE_ACTIVE);
		mapParametros.put("idDispositivo", idDispositivo);
		return super.consultaSimple(sbfConsulta.toString(), mapParametros);
	}
}
