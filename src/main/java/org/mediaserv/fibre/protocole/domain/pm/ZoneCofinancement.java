package org.mediaserv.fibre.protocole.domain.pm;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Thomas Lecocq
 */
@Entity
@Table(name = "ZONE_COFI", uniqueConstraints = {@UniqueConstraint(name = "UK_REF_ZONE_COFI", columnNames = {"ZCOF_REF_REGROUP_PM"})})
public class ZoneCofinancement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_ZONE_COFI", initialValue = 1, allocationSize = 1)
    @Column(name = "ZCOF_ID")
    private Long id;

    // Je me demande si le nom de la colonne va herite de la PK de l'objet PM !
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "zoneCofinancement")
    private Collection<PM> pms;

    /**
     * "Champ facultatif, utilisé par certains opérateurs d'immeuble qui en font
     * référence dans leur contrat, notamment pour gérer l'appartenance d'un PM
     * qui a fait l'objet d'un appel au cofinancement en ZMD. En ZTD c'est le
     * champ ReferenceConsultationNative qui est utilisé pour un usage
     * équivalent
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse."
     *
     */
    
    @Column(name = "ZCOF_REF_REGROUP_PM")
    private String refRegroupementPM;

    public String getRefRegroupementPM() {
        return refRegroupementPM;
    }

    public void setRefRegroupementPM(String refRegroupementPM) {
        this.refRegroupementPM = refRegroupementPM;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<PM> getPms() {
        return pms;
    }

    public void setPms(Collection<PM> pms) {
        this.pms = pms;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (this.refRegroupementPM != null ? this.refRegroupementPM.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ZoneCofinancement other = (ZoneCofinancement) obj;
        if ((this.refRegroupementPM == null) ? (other.refRegroupementPM != null) : !this.refRegroupementPM.equals(other.refRegroupementPM)) {
            return false;
        }
        return true;
    }
}
