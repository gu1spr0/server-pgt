package com.pgt360.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.pgt360.util.Constants;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Base {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @PastOrPresent(message = "La fecha de alta del registro debe ser actual")
    @NotNull(message = "La fecha de alta del registro no debe ser nula")
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @NotNull(message = "El usuario que dió de alta el registro no debe ser nula")
    @Column(name = "usuario_alta")
    private Integer createdBy;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @Column(name = "usuario_baja")
    private Integer deletedBy;

    @Column(name = "fecha_modificacion", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Column(name = "usuario_modificacion", nullable = true)
    private Integer lastModifiedBy;

    @NotNull(message = "El estado no puede ser nulo")
    @Column(name = "estado")
    private String state;

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        if (createdDate == null) {
            createdDate = now;
            createdBy = 0;
            state = Constants.STATE_ACTIVE;
        }
    }

    @PreUpdate
    public void preUpdate(){
        //lastModifiedBy = Security.getUserOfAuthenticatedUser();
        lastModifiedBy = 0;
        lastModifiedDate = new Date();
        if  (deletedDate != null){
            deletedBy = 0;
            state = Constants.STATE_DELETED;
        }
    }
}
