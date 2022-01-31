package com.pgt360.dao.comun;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class GenericDao<T> {
	@PersistenceContext(name="aplicacion")
	private EntityManager entityManager;
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	private Class<T> claseEntidad;
	
	public GenericDao(Class<T> claseEntidad) {
		this.claseEntidad = claseEntidad;
	}
	
	public T persistirEntidad(T entidad) {
		this.entityManager.persist(entidad);
		this.entityManager.flush();
		return entidad;
	}
	
	public T actualizarEntidad(T entidad) {
		return this.entityManager.merge(entidad);
	}
	
	public T seleccionarEntidad(long entidadId) {
		return this.entityManager.find(this.claseEntidad, entidadId);
	}
	
	@SuppressWarnings("unchecked")
	protected T consultaSimple(String consulta, HashMap<String, Object> parametros) {
		T entidadResultado = null;
		Query query;
		query = this.entityManager.createQuery(consulta);
		if(parametros != null && parametros.size() > 0)
			this.agregarParametrosConsulta(query, parametros);
		try {
			entidadResultado = (T) query.getSingleResult();
		} catch(NoResultException e) {
			entidadResultado = null;
		}
		return entidadResultado;
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> consultaVarios(String consulta, HashMap<String, Object> parametros){
		List<T> entidadesResultado = null;
		Query query;
		query = this.entityManager.createQuery(consulta);
		if(parametros != null && parametros.size() > 0)
			this.agregarParametrosConsulta(query, parametros);
		List<T> resultList = (List<T>) query.getResultList();
		entidadesResultado = resultList;
		return entidadesResultado;
	}
	
	@SuppressWarnings("unchecked")
	protected T consultaNativaSimple(String consulta, HashMap<String, Object> parametros) {
		T entidadResultado = null;
		Query query;
		query = this.entityManager.createNativeQuery(consulta, this.claseEntidad);
		if(parametros != null && parametros.size()>0)
			this.agregarParametrosConsulta(query, parametros);
		try {
			entidadResultado = (T) query.getSingleResult();
		} catch(NoResultException e) {
			entidadResultado = null;
		}
		return entidadResultado;
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> consultaNativaVarios(String consulta,
			HashMap<String, Object> parametros) {
		List<T> entidadesResultado = null;
		Query query;

		query = this.entityManager.createNativeQuery(consulta,
				this.claseEntidad);

		if (parametros != null && parametros.size() > 0)
			this.agregarParametrosConsulta(query, parametros);

		entidadesResultado = (List<T>) query.getResultList();

		return entidadesResultado;
	}
	
	private void agregarParametrosConsulta(Query query, HashMap<String, Object> parametros) {
		for(Entry<String, Object> entrada : parametros.entrySet())
			query.setParameter(entrada.getKey(), entrada.getValue());
	}
	
	public Date fechaActual() {
		return new Date();
	}
	
	

}
