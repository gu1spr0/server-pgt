package com.pgt360.dao;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pgt360.dao.comun.GenericDao;
import com.pgt360.model.Conexion;
import com.pgt360.util.Constants;

public class ConexionDao extends GenericDao<Conexion>{
	@PersistenceContext
	private EntityManager em;
	
	public ConexionDao() {
		super(Conexion.class);
	}
	
	public Conexion obtenerConexionPorId(int idConexion) {
		StringBuffer sbfConsulta = new StringBuffer();
		HashMap<String, Object> mapParametros = new HashMap<>();
		sbfConsulta.append("SELECT c ");
		sbfConsulta.append("FROM Conexion c ");
		sbfConsulta.append("WHERE c.estado = :estado ");
		sbfConsulta.append("AND c.id = :idConexion");
		mapParametros.put("estado", Constants.STATE_ACTIVE);
		mapParametros.put("idConexion", idConexion);
		return super.consultaSimple(sbfConsulta.toString(), mapParametros);
	}
	
	public Conexion obtenerConexionPorCodigo(String pCanalId) {
		StringBuffer sbfConsulta = new StringBuffer();
		HashMap<String, Object> mapParametros = new HashMap<>();
		sbfConsulta.append("SELECT c ");
		sbfConsulta.append("FROM Conexion c ");
		sbfConsulta.append("WHERE c.estado = :estado ");
		sbfConsulta.append("AND c.idcanal = :pCanalId");
		mapParametros.put("estado", Constants.STATE_ACTIVE);
		mapParametros.put("pCanalId", pCanalId);
		return super.consultaSimple(sbfConsulta.toString(), mapParametros);
	}
}
