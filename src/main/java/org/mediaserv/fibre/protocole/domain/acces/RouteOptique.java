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

/**
 *
 * @author RREMBLIN
 */
@Entity
@Table(name = "CIRCUIT_OPTIQUE")
public class RouteOptique implements Serializable {

    @Id
    @Column(name = "OPT_CIRCUIT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_RO", initialValue = 1, allocationSize = 1)
    private Long optCircuitId;

    @Column(name = "OC")
    private String oC;

    @Column(name = "NOM_PM")
    private String nomModulePm;

    @Column(name = "POS_PM")
    private String positionModulePm;

    @Column(name = "REF_CAB_PM")
    private String referenceCableModulePm;

    @Column(name = "INFO_TUBE_PM")
    private String informationTubeModulePm;

    @Column(name = "REF_PBO")
    private String referencePBO;

    @Column(name = "LOC_TUBE_PBO")
    private String localisationTubePBO;

    @Column(name = "INFO_TUBE_PBO")
    private String informationTubePBO;

    @Column(name = "INFO_FIB_PBO")
    private String informationFibrePBO;

    @Column(name = "CONN_PTO_NUMB")
    private String connecteurPriseNumero;

    @Column(name = "CONN_PTO_COLOR")
    private String connecteurPriseCouleur;

    public Long getOptCircuitId() {
        return optCircuitId;
    }

    public void setOptCircuitId(Long opt_circuit_id) {
        this.optCircuitId = opt_circuit_id;
    }

    public String getOC() {
        return oC;
    }

    public void setOC(String oC) {
        this.oC = oC;
    }

    public String getNomModulePm() {
        return nomModulePm;
    }

    public void setNomModulePm(String nomModulePm) {
        this.nomModulePm = nomModulePm;
    }

    public String getPositionModulePm() {
        return positionModulePm;
    }

    public void setPositionModulePm(String positionModulePm) {
        this.positionModulePm = positionModulePm;
    }

    public String getReferenceCableModulePm() {
        return referenceCableModulePm;
    }

    public void setReferenceCableModulePm(String referenceCableModulePm) {
        this.referenceCableModulePm = referenceCableModulePm;
    }

    public String getInformationTubeModulePm() {
        return informationTubeModulePm;
    }

    public void setInformationTubeModulePm(String informationTubeModulePm) {
        this.informationTubeModulePm = informationTubeModulePm;
    }

    public String getReferencePBO() {
        return referencePBO;
    }

    public void setReferencePBO(String referencePBO) {
        this.referencePBO = referencePBO;
    }

    public String getLocalisationTubePBO() {
        return localisationTubePBO;
    }

    public void setLocalisationTubePBO(String localisationTubePBO) {
        this.localisationTubePBO = localisationTubePBO;
    }

    public String getInformationTubePBO() {
        return informationTubePBO;
    }

    public void setInformationTubePBO(String informationTubePBO) {
        this.informationTubePBO = informationTubePBO;
    }

    public String getInformationFibrePBO() {
        return informationFibrePBO;
    }

    public void setInformationFibrePBO(String informationFibrePBO) {
        this.informationFibrePBO = informationFibrePBO;
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
