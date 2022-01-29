package com.pgt360.dao;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pgt360.dao.comun.GenericDao;
import com.pgt360.model.Flujo;
import com.pgt360.util.Constants;

public class FlujoDao extends GenericDao<Flujo>{
	@PersistenceContext
	private EntityManager em;
	
	public FlujoDao() {
		super(Flujo.class);
	}
	
	public Flujo obtenerFlujoPorId(int idFlujo) {
		StringBuffer sbfConsulta = new StringBuffer();
		HashMap<String, Object> mapParametros = new HashMap<>();
		sbfConsulta.append("SELECT f ");
		sbfConsulta.append("FROM Flujo f ");
		sbfConsulta.append("WHERE f.estado = :estado ");
		sbfConsulta.append("AND f.id = :idFlujo");
		mapParametros.put("estado", Constants.STATE_ACTIVE);
		mapParametros.put("idFlujo", idFlujo);
		return super.consultaSimple(sbfConsulta.toString(), mapParametros);
	}
	
}
