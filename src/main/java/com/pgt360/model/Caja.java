package com.pgt360.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "pg_cajas")
public class Caja extends Base{
	@NotNull(message = "El número de caja no puede ser nulo")
    @Column(name = "numero_caja")
    private int numberCash;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_comercio")
    private Comercio comercio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dispositivo", referencedColumnName = "id")
    private Dispositivo dispositivo;

    @OneToMany(mappedBy = "caja",fetch = FetchType.LAZY)
    private List<Flujo> flujoList;
}
