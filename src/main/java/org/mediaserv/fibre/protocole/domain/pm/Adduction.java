package org.mediaserv.fibre.protocole.domain.pm;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Thomas Lecocq
 */
@Entity
@Table(name = "ADDUCTION")
public class Adduction implements Serializable {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_ADDU", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "ADDUCT_ID")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "ADDUCT_DATE_NOTIF_RACC_PM")
    private Date dateNotifRaccordementPM;

    @Temporal(TemporalType.DATE)
    @Column(name = "ADDUCT_DATE_PREV")
    private Date datePrevisionnelleAdduction;

    @Temporal(TemporalType.DATE)
    @Column(name = "ADDUCT_DATE_NOTIF")
    private Date dateNotifAdduction;

    @Temporal(TemporalType.DATE)
    @Column(name = "ADDUCT_DATE")
    private Date dateAdduction;

    @Column(name = "ADDUCT_ETAT")
    private String etatAdduction;

    @Column(name = "ADDUCT_MOTIF_KO")
    private String motifKoAdduction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateNotifRaccordementPM() {
        return dateNotifRaccordementPM;
    }

    public void setDateNotifRaccordementPM(Date dateNotifRaccordementPM) {
        this.dateNotifRaccordementPM = dateNotifRaccordementPM;
    }

    public Date getDatePrevisionnelleAdduction() {
        return datePrevisionnelleAdduction;
    }

    public void setDatePrevisionnelleAdduction(Date DatePrevisionnelleAdduction) {
        this.datePrevisionnelleAdduction = DatePrevisionnelleAdduction;
    }

    public Date getDateNotifAdduction() {
        return dateNotifAdduction;
    }

    public void setDateNotifAdduction(Date dateNotifAdduction) {
        this.dateNotifAdduction = dateNotifAdduction;
    }

    public Date getDateAdduction() {
        return dateAdduction;
    }

    public void setDateAdduction(Date dateAdduction) {
        this.dateAdduction = dateAdduction;
    }

    public String getEtatAdduction() {
        return etatAdduction;
    }

    public void setEtatAdduction(String etatAdduction) {
        this.etatAdduction = etatAdduction;
    }

    public String getMotifKoAdduction() {
        return motifKoAdduction;
    }

    public void setMotifKoAdduction(String motifKoAdduction) {
        this.motifKoAdduction = motifKoAdduction;
    }

}
