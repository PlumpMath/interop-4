/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mediaserv.fibre.protocole.domain.acces;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author RREMBLIN
 */
@Entity
@Table(name = "RDV")
public class RDV implements Serializable {

    @Id
    @Column(name = "RDV_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_RDV", initialValue = 1, allocationSize = 1)
    private Long rdvId;

    @NotNull
    @Column(name = "REF_RDV")
    private String refRdv;

    public Long getRdv_id() {
        return rdvId;
    }

    public void setRdv_id(Long rdv_id) {
        this.rdvId = rdv_id;
    }

    public String getRef_rdv() {
        return refRdv;
    }

    public void setRef_rdv(String ref_rdv) {
        this.refRdv = ref_rdv;
    }

}
