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
@Table(name = "pg_comercios")
public class Comercio extends Base{
	@NotNull(message = "La razon social no puede ser nulo")
    @Column(name = "razon_social", length = 40)
    private String socialReason;

    @OneToMany(mappedBy = "comercio",fetch = FetchType.LAZY)
    private List<Caja> cajaList;
}
