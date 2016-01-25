/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mediaserv.fibre.protocole.domain.acces.commande;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.mediaserv.fibre.protocole.domain.acces.RouteOptique;
import org.mediaserv.fibre.protocole.domain.acces.Commande;
import org.mediaserv.fibre.protocole.domain.gestionfichier.Fichier;

/**
 *
 * @author RREMBLIN
 */
@Entity
@Table(name = "CMD_ACCES_CR")
public class CmdAccesCr implements Serializable {

    @Id
    @Column(name = "CR_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_CR", initialValue = 1, allocationSize = 1)
    private Long accesCrId;

    @JoinColumn(name = "CMD_ID", foreignKey = @ForeignKey(name = "FK_CR_CMD"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Commande commande;

    @Column(name = "REF_PRESTA")
    private String referencePrestationPrise;

    @Column(name = "ETAT")
    private String etatCrCommandePrise;

    @Column(name = "MOTIF_REJET")
    private String motifKoCrCommandePrise;

    @Column(name = "DATE_CMD")
    private String dateCrCommandePrise;

    @Column(name = "TYPE_CMD_RETENU")
    private String typeCommandeRetenu;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "OPT_CIRCUIT_ID", foreignKey = @ForeignKey(name = "FK_CR_CIRC_OPT"))
    private Collection<RouteOptique> circuitOptique;

    @JoinColumn(name = "FIC_ID", foreignKey = @ForeignKey(name = "FK_CR_FICHIER"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Fichier fichier;

    private String commentaire;

    @Column(name = "DATE_INSERT")
    private Timestamp dateInsert;

    @JoinColumn(name = "NOTIF_ID", referencedColumnName = "FIC_ID", foreignKey = @ForeignKey(name = "FK_CR_NOT"))
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

    public Long getAcces_cr_id() {
        return accesCrId;
    }

    public void setAcces_cr_id(Long acces_cr_id) {
        this.accesCrId = acces_cr_id;
    }

    public String getReferencePrestationPrise() {
        return referencePrestationPrise;
    }

    public void setReferencePrestationPrise(String referencePrestationPrise) {
        this.referencePrestationPrise = referencePrestationPrise;
    }

    public Long getAccesCrId() {
        return accesCrId;
    }

    public void setAccesCrId(Long accesCrId) {
        this.accesCrId = accesCrId;
    }

    public String getEtatCrCommandePrise() {
        return etatCrCommandePrise;
    }

    public void setEtatCrCommandePrise(String etatCrCommandePrise) {
        this.etatCrCommandePrise = etatCrCommandePrise;
    }

    public String getMotifKoCrCommandePrise() {
        return motifKoCrCommandePrise;
    }

    public void setMotifKoCrCommandePrise(String motifKoCrCommandePrise) {
        this.motifKoCrCommandePrise = motifKoCrCommandePrise;
    }

    public String getDateCrCommandePrise() {
        return dateCrCommandePrise;
    }

    public void setDateCrCommandePrise(String dateCrCommandePrise) {
        this.dateCrCommandePrise = dateCrCommandePrise;
    }

    public String getTypeCommandeRetenu() {
        return typeCommandeRetenu;
    }

    public void setTypeCommandeRetenu(String typeCommandeRetenu) {
        this.typeCommandeRetenu = typeCommandeRetenu;
    }

    public Collection<RouteOptique> getCircuitOptique() {
        return circuitOptique;
    }

    public void setCircuitOptique(Collection<RouteOptique> circuitOptique) {
        this.circuitOptique = circuitOptique;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

}
