/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mediaserv.fibre.protocole.domain.acces.commande;

import org.mediaserv.fibre.protocole.domain.acces.TypeOperation;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.mediaserv.fibre.protocole.domain.acces.EtatCmd;
import org.mediaserv.fibre.protocole.domain.acces.Rejet;
import org.mediaserv.fibre.protocole.domain.gestionfichier.Fichier;

/**
 *
 * @author RREMBLIN
 */
@Entity
@Table(name = "CMD_ACCES_ANNU_CR")

public class CmdAccesAnnuCr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_ANNUCR", initialValue = 1, allocationSize = 1)
    @Column(name = "ANNUCR_ID")
    private Long accesAnnuCrId;

    @JoinColumn(name = "CMD_ID", foreignKey = @ForeignKey(name = "FK_ANNUCR_CMD"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Commande commande;

    @Column(name = "DATE_CR_CMD_ANNU")
    private String dateCrCommandeAnnulPrise;

    @Column(name = "ETAT_CR_CMD_ANNU")
    @Enumerated(EnumType.STRING)
    private EtatCmd etatCrAnnResCommandePrise;

    @Column(name = "MOTIF_REJET")
    @Enumerated(EnumType.STRING)
    private Rejet rejet;

    @Column(name = "TYPE_OPERATION")
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    @JoinColumn(name = "FIC_ID", foreignKey = @ForeignKey(name = "FK_ANNUCR_FIC"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Fichier fichier;

    @JoinColumn(name = "NOTIF_ID", referencedColumnName = "FIC_ID", foreignKey = @ForeignKey(name = "FK_ANNUCR_NOT"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Fichier notification;

    public Fichier getNotification() {
        return notification;
    }

    public void setNotification(Fichier notification) {
        this.notification = notification;
    }

    @Column(name = "DATE_INSERT")
    private Timestamp dateInsert;

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

    public Long getAcces_annu_cr_id() {
        return accesAnnuCrId;
    }

    public void setAcces_annu_cr_id(Long acces_annu_cr_id) {
        this.accesAnnuCrId = acces_annu_cr_id;
    }

    public String getDateCrCommandeAnnulPrise() {
        return dateCrCommandeAnnulPrise;
    }

    public void setDateCrCommandeAnnulPrise(String dateCrCommandeAnnulPrise) {
        this.dateCrCommandeAnnulPrise = dateCrCommandeAnnulPrise;
    }

    public Long getAccesAnnuCrId() {
        return accesAnnuCrId;
    }

    public void setAccesAnnuCrId(Long accesAnnuCrId) {
        this.accesAnnuCrId = accesAnnuCrId;
    }

    public EtatCmd getEtatCrAnnResCommandePrise() {
        return etatCrAnnResCommandePrise;
    }

    public void setEtatCrAnnResCommandePrise(EtatCmd etatCrAnnResCommandePrise) {
        this.etatCrAnnResCommandePrise = etatCrAnnResCommandePrise;
    }

    public Rejet getRejet() {
        return rejet;
    }

    public void setRejet(Rejet rejet) {
        this.rejet = rejet;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

}
