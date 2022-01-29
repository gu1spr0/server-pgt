package com.pgt360.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "pg_flujos")
public class Flujo extends Base{
	@NotNull(message = "El código de flujo no puede ser nulo")
    @Column(name = "codigo_flujo")
    private int codeFlow;

    @NotNull(message = "La descripción del flujo no puede ser nulo")
    @Column(name = "descripcion", length = 40)
    private String description;

    @Column(name = "paso")
    private int step;

    @Column(name = "tamaño")
    private int size;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_caja")
    private Caja caja;
}
