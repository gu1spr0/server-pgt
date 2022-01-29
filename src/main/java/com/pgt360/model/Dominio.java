package com.pgt360.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "pg_dominios")
public class Dominio extends Base{
	@NotNull(message = "El tipo de dominio no debe ser nulo")
    @Column(name = "dom_tipo_dominio", length = 30)
    private String domainType;

    @NotNull(message = "El codigo de dominio no debe ser nulo")
    @Column(name = "dom_codigo_dominio", length = 100)
    private String domainCode;

    @NotNull(message = "El nombre de dominio no debe ser nulo")
    @Column(name = "dom_nombre_dominio", length = 100)
    private String domainName;

    @NotNull(message = "La descripcion del dominio no debe ser nulo")
    @Column(name = "dom_descripcion", length = 300)
    private String domainDescription;

    @OneToMany(mappedBy = "dominio",fetch = FetchType.LAZY)
    private List<DominioValor> dominioValorList;
}
