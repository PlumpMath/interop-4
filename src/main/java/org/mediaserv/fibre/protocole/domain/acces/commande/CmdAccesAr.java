/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mediaserv.fibre.protocole.domain.acces.commande;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.mediaserv.fibre.protocole.domain.acces.Commande;
import org.mediaserv.fibre.protocole.domain.gestionfichier.Fichier;

/**
 *
 * @author RREMBLIN
 */
@Entity
@Table(name = "CMD_ACCES_AR")
public class CmdAccesAr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_AR", initialValue = 1, allocationSize = 1)
    @Column(name = "AR_ID")
    private Long accesArId;

    @JoinColumn(name = "CMD_ID", foreignKey = @ForeignKey(name = "FK_AR_CMD"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Commande commande;

    @Column(name = "REF_PRESTA")
    private String referencePrestationPrise;

    @Column(name = "ETAT")
    private String etatArCommandePrise;

    @Column(name = "DATE_CMD")
    private String dateArCommandePrise;

    @Column(name = "MOTIF_REJET")
    private String motifKoArCommandePrise;

    @JoinColumn(name = "FIC_ID", foreignKey = @ForeignKey(name = "FK_AR_FIC"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Fichier fichier;

    @Column(name = "DATE_INSERT")
    private Timestamp dateInsert;

    @JoinColumn(name = "NOTIF_ID", referencedColumnName = "FIC_ID", foreignKey = @ForeignKey(name = "FK_AR_NOT"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Fichier notification;

    public Fichier getNotification() {
        return notification;
    }

    public void setNotification(Fichier notification) {
        this.notification = notification;
    }

    public Timestamp getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(Timestamp dateInsert) {
        this.dateInsert = dateInsert;
    }

    public Fichier getFichier() {
        return fichier;
    }

    public void setFichier(Fichier fichier) {
        this.fichier = fichier;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Long getAcces_ar_id() {
        return accesArId;
    }

    public void setAcces_ar_id(Long acces_ar_id) {
        this.accesArId = acces_ar_id;
    }

    public String getReferencePrestationPrise() {
        return referencePrestationPrise;
    }

    public void setReferencePrestationPrise(String referencePrestationPrise) {
        this.referencePrestationPrise = referencePrestationPrise;
    }

    public Long getAccesArId() {
        return accesArId;
    }

    public void setAccesArId(Long accesArId) {
        this.accesArId = accesArId;
    }

    public String getDateArCommandePrise() {
        return dateArCommandePrise;
    }

    public void setDateArCommandePrise(String dateArCommandePrise) {
        this.dateArCommandePrise = dateArCommandePrise;
    }

    public String getEtatArCommandePrise() {
        return etatArCommandePrise;
    }

    public void setEtatArCommandePrise(String etatArCommandePrise) {
        this.etatArCommandePrise = etatArCommandePrise;
    }

    public String getMotifKoArCommandePrise() {
        return motifKoArCommandePrise;
    }

    public void setMotifKoArCommandePrise(String motifKoArCommandePrise) {
        this.motifKoArCommandePrise = motifKoArCommandePrise;
    }

}
