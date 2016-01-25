package org.mediaserv.fibre.protocole.domain.pm;

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
 * @author Thomas Lecocq
 */
@Entity
@Table(name = "POSITION")
public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_POSITION", initialValue = 1, allocationSize = 1)
    @Column(name = "POS_ID")
    private Long id;

    @Column(name = "POS_OC")
    private String OC;
    
    @Column(name = "POS_NOM_MODULE_PM")
    private String nomModulePM;
    
    @Column(name = "POS_MODULE_PM")
    private String positionModulePM;
    
    @Column(name = "POS_REF_CABLE_MODULE_PM")
    private String referenceCableModulePM;
    
    @Column(name = "POS_INFO_TUBE_MODULE_PM")
    private String informationTubeModulePM;
    
    @Column(name = "POS_INFO_BAGUE_TUBE_PM")
    private String informationBagueTubePM;
    
    @Column(name = "POS_INFO_FIBRE_MODULE_PM")
    private String informationFibreModulePM;
    
    @Column(name = "POS_INFO_BAGUE_FIBRE_PM")
    private String informationBagueFibrePM;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOC() {
        return OC;
    }

    public void setOC(String OC) {
        this.OC = OC;
    }

    public String getNomModulePM() {
        return nomModulePM;
    }

    public void setNomModulePM(String nomModulePM) {
        this.nomModulePM = nomModulePM;
    }

    public String getPositionModulePM() {
        return positionModulePM;
    }

    public void setPositionModulePM(String positionModulePM) {
        this.positionModulePM = positionModulePM;
    }

    public String getReferenceCableModulePM() {
        return referenceCableModulePM;
    }

    public void setReferenceCableModulePM(String referenceCableModulePM) {
        this.referenceCableModulePM = referenceCableModulePM;
    }

    public String getInformationTubeModulePM() {
        return informationTubeModulePM;
    }

    public void setInformationTubeModulePM(String informationTubeModulePM) {
        this.informationTubeModulePM = informationTubeModulePM;
    }

    public String getInformationBagueTubePM() {
        return informationBagueTubePM;
    }

    public void setInformationBagueTubePM(String informationBagueTubePM) {
        this.informationBagueTubePM = informationBagueTubePM;
    }

    public String getInformationFibreModulePM() {
        return informationFibreModulePM;
    }

    public void setInformationFibreModulePM(String informationFibreModulePM) {
        this.informationFibreModulePM = informationFibreModulePM;
    }

    public String getInformationBagueFibrePM() {
        return informationBagueFibrePM;
    }

    public void setInformationBagueFibrePM(String informationBagueFibrePM) {
        this.informationBagueFibrePM = informationBagueFibrePM;
    }

}
