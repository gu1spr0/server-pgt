package com.pgt360.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "pg_conexiones")
public class Conexion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@NotNull(message = "El id de canal no puede ser nulo")
    @Column(name = "idcanal", length = 20)
    private String idCanal;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dispositivo")
    private Dispositivo dispositivo;
	
	@Column(name = "fecha_conexion")
    @Temporal(TemporalType.DATE)
    private Date dateConnection;
	
	@Column(name = "hora_conexion")
    @Temporal(TemporalType.TIME)
    private Date hourConnection;
	
	@Column(name = "fecha_desconexion")
    @Temporal(TemporalType.DATE)
    private Date dateDisconnect;
	
	@Column(name = "hora_desconexion")
    @Temporal(TemporalType.TIME)
    private Date hourDisconnect;
	
	@NotNull(message = "El estado no puede ser nulo")
    @Column(name = "estado", length = 10)
    private String state;
}
