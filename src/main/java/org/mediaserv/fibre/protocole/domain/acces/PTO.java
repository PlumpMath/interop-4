/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mediaserv.fibre.protocole.domain.acces;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author RREMBLIN
 */
@Entity
@Table(name = "PTO", uniqueConstraints = {
    @UniqueConstraint(name = "UK_REF_PTO", columnNames = {"REF_PTO"})})
public class PTO implements Serializable {

    @Id
    @Column(name = "PTO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_PTO", initialValue = 1, allocationSize = 1)
    private Long ptoId;

    @Column(name = "REF_PTO")
    private String referencePrise;

    @NotNull
    @Column(name = "PTO_EXISTANTE")
    @Enumerated(EnumType.STRING)
    private PriseExistante priseExistante;

    @Column(name = "PTO_CONNECTEUR")
    private String connecteurPriseNumero;

    @Column(name = "PTO_COULEUR")
    private String connecteurPriseCouleur;

    public String getReferencePrise() {
        return referencePrise;
    }

    public void setReferencePrise(String referencePrise) {
        this.referencePrise = referencePrise;
    }

    public PriseExistante getPriseExistante() {
        return priseExistante;
    }

    public void setPriseExistante(PriseExistante priseExistante) {
        this.priseExistante = priseExistante;
    }

    public Long getPto_id() {
        return ptoId;
    }

    public void setPto_id(Long pto_id) {
        this.ptoId = pto_id;
    }

    public String getConnecteurPriseNumero() {
        return connecteurPriseNumero;
    }

    public void setConnecteurPriseNumero(String connecteurPriseNumero) {
        this.connecteurPriseNumero = connecteurPriseNumero;
    }

    public String getConnecteurPriseCouleur() {
        return connecteurPriseCouleur;
    }

    public void setConnecteurPriseCouleur(String connecteurPriseCouleur) {
        this.connecteurPriseCouleur = connecteurPriseCouleur;
    }

}
