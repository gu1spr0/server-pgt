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
@Table(name = "no_dominios_valores")
public class DominioValor extends Base{
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dva_domcodigo")
    private Dominio dominio;

    @NotNull(message = "El codigo valor no puede ser nulo")
    @Column(name = "dva_codigo_valor", length = 100)
    private String codeValue;

    @Column(name = "dva_titulo_descripcion", length = 300)
    private String titleDescription;

    @Column(name = "dva_valor_caracter", length = 300)
    private String charValue;

    @Column(name = "dva_valor_numerico")
    private Long numericValue;

    @Column(name = "dva_valor_caracter_extra", length = 300)
    private String charValueExtra;

    @Column(name = "dva_valor_numerico_extra")
    private Long numericValueExtra;
}
