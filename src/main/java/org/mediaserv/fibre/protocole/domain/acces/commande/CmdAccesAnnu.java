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
import org.mediaserv.fibre.protocole.domain.acces.PTO;
import org.mediaserv.fibre.protocole.domain.acces.RDV;
import org.mediaserv.fibre.protocole.domain.gestionfichier.Fichier;

/**
 *
 * @author RREMBLIN
 */
@Entity
@Table(name = "CMD_ACCES_ANNU")
public class CmdAccesAnnu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_ANNU", initialValue = 1, allocationSize = 1)
     @Column(name = "ANNU_ID")
    private Long idAccesAnnu;

    @JoinColumn(name = "CMD_ID", foreignKey = @ForeignKey(name = "FK_ANNU_COMMANDE"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Commande commande;

    @JoinColumn(name = "RDV_ID", foreignKey = @ForeignKey(name = "FK_ANNU_RDV"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private RDV rdv;

    @JoinColumn(name = "PTO_ID", foreignKey = @ForeignKey(name = "FK_ANNU_PTO"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private PTO pto;

    @Column(name = "REF_PRESTA")
    private String referencePrestationPrise;

    @Column(name = "DATE_ANNU")
    private String dateAnnulation;

    @Column(name = "RESPONSABILITE_ANNU")
    private String responsabiliteAnnulationCommandePrise;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

    @JoinColumn(name = "FIC_ID", foreignKey = @ForeignKey(name = "FK_ANNU_FIC"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Fichier fichier;

    @JoinColumn(name = "ACCES_ANNU_CR_ID", foreignKey = @ForeignKey(name = "FK_ANNU_CR"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private CmdAccesCr cmdAccesCr;

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

    public CmdAccesCr getCmdAccesCr() {
        return cmdAccesCr;
    }

    public void setCmdAccesCr(CmdAccesCr cmdAccesCr) {
        this.cmdAccesCr = cmdAccesCr;
    }

    public String getReferencePrestationPrise() {
        return referencePrestationPrise;
    }

    public void setReferencePrestationPrise(String referencePrestationPrise) {
        this.referencePrestationPrise = referencePrestationPrise;
    }

    public String getDateAnnulation() {
        return dateAnnulation;
    }

    public void setDateAnnulation(String dateAnnulation) {
        this.dateAnnulation = dateAnnulation;
    }

    public String getResponsabiliteAnnulationCommandePrise() {
        return responsabiliteAnnulationCommandePrise;
    }

    public void setResponsabiliteAnnulationCommandePrise(String responsabiliteAnnulationCommandePrise) {
        this.responsabiliteAnnulationCommandePrise = responsabiliteAnnulationCommandePrise;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Long getId_acces_annu() {
        return idAccesAnnu;
    }

    public void setId_acces_annu(Long id_acces_annu) {
        this.idAccesAnnu = id_acces_annu;
    }

    public RDV getRdv() {
        return rdv;
    }

    public void setRdv(RDV rdv) {
        this.rdv = rdv;
    }

    public PTO getPto() {
        return pto;
    }

    public void setPto(PTO pto) {
        this.pto = pto;
    }

}
