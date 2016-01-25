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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.mediaserv.fibre.protocole.domain.acces.Client;
import org.mediaserv.fibre.protocole.domain.acces.Commande;
import org.mediaserv.fibre.protocole.domain.acces.PTO;
import org.mediaserv.fibre.protocole.domain.acces.RDV;
import org.mediaserv.fibre.protocole.domain.acces.TypeCommandeDemande;
import org.mediaserv.fibre.protocole.domain.acces.TypeRacco;
import org.mediaserv.fibre.protocole.domain.gestionfichier.Fichier;
import org.mediaserv.fibre.protocole.domain.pm.Immeuble;
import org.mediaserv.fibre.protocole.domain.pm.PM;

/**
 *
 * @author RREMBLIN
 */
@Entity
@Table(name = "CMD_ACCES", uniqueConstraints = {
    @UniqueConstraint(name = "UK_REF_ACCES", columnNames = {"REF_CMD"})})
public class CmdAcces implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_ACCES", initialValue = 1, allocationSize = 1)
    @Column(name = "ID")
    private Long cmdAccesId;

    @JoinColumn(name = "CMD_ID", foreignKey = @ForeignKey(name = "FK_ACCES_COMMANDE"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Commande commande;

    @Column(name = "REF_CMD")
    private String referenceCommandePriseInterneOC;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "IMM_ID", foreignKey = @ForeignKey(name = "FK_ACCES_IMMEUBLE"))
    private Immeuble immeuble;

    @Column(name = "TYPE_RACCO")
    @Enumerated(EnumType.STRING)
    private TypeRacco typeRacco;

    @Column(name = "DATE_INSTALL")
    private String dateInstall;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID", foreignKey = @ForeignKey(name = "FK_ACCES_CLIENT"))
    private Client client;

    @NotNull
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "RDV_ID",foreignKey = @ForeignKey(name = "FK_ACCES_RDV"))
    private Collection<RDV> rdv;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "PM_ID", foreignKey = @ForeignKey(name = "FK_ACCES_PM"))
    private PM pm;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "PTO_ID", foreignKey = @ForeignKey(name = "FK_ACCES_PTO"))
    private PTO pto;

    @Column(name = "TYPE_CMD_DEM")
    @Enumerated(EnumType.STRING)
    private TypeCommandeDemande typeCommandeDemande;

    @Column(name = "DATE_CMD")
    private String dateCommandePrise;

    @Column(name = "COMMENTAIRE")
    private String commentaireCmdAcces;

    @Column(name = "INFO_CMD_ACCES1")
    private String infoCmdAcces1;

    @Column(name = "INFO_CMD_ACCES2")
    private String infoCmdAcces2;

    @Column(name = "INFO_CMD_ACCES3")
    private String infoCmdAcces3;

    @Column(name = "INFO_CMD_ACCES4")
    private String infoCmdAcces4;

    @Column(name = "INFO_CMD_ACCES5")
    private String infoCmdAcces5;

    @Column(name = "INFO_CMD_ACCES6")
    private String infoCmdAcces6;

    @Column(name = "INFO_CMD_ACCES7")
    private String infoCmdAcces7;

    @Column(name = "INFO_CMD_ACCES8")
    private String infoCmdAcces8;

    @JoinColumn(name = "FIC_ID", foreignKey = @ForeignKey(name = "FK_ACCES_FICHIER"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Fichier fichier;

    @JoinColumn(name = "ACCES_AR_ID", foreignKey = @ForeignKey(name = "FK_ACCES_AR"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private CmdAccesAr arCmdAcces;

    @JoinColumn(name = "ACCES_CR_ID", foreignKey = @ForeignKey(name = "FK_ACCES_CR"))
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private CmdAccesCr crCmdAcces;

    @Column(name = "DATE_INSERT")
    private Timestamp dateInsert;

    public Timestamp getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(Timestamp dateInsert) {
        this.dateInsert = dateInsert;
    }

    public Long getCmdAccesId() {
        return cmdAccesId;
    }

    public void setCmdAccesId(Long cmdAccesId) {
        this.cmdAccesId = cmdAccesId;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public String getReferenceCommandePriseInterneOC() {
        return referenceCommandePriseInterneOC;
    }

    public void setReferenceCommandePriseInterneOC(String referenceCommandePriseInterneOC) {
        this.referenceCommandePriseInterneOC = referenceCommandePriseInterneOC;
    }

    public Immeuble getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(Immeuble immeuble) {
        this.immeuble = immeuble;
    }

    public TypeRacco getTypeRacco() {
        return typeRacco;
    }

    public void setTypeRacco(TypeRacco typeRacco) {
        this.typeRacco = typeRacco;
    }

    public String getDateInstall() {
        return dateInstall;
    }

    public void setDateInstall(String dateInstall) {
        this.dateInstall = dateInstall;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<RDV> getRdv() {
        return rdv;
    }

    public void setRdv(Collection<RDV> rdv) {
        this.rdv = rdv;
    }

    public PM getPm() {
        return pm;
    }

    public void setPm(PM pm) {
        this.pm = pm;
    }

    public PTO getPto() {
        return pto;
    }

    public void setPto(PTO pto) {
        this.pto = pto;
    }

    public TypeCommandeDemande getTypeCommandeDemande() {
        return typeCommandeDemande;
    }

    public void setTypeCommandeDemande(TypeCommandeDemande typeCommandeDemande) {
        this.typeCommandeDemande = typeCommandeDemande;
    }

    public String getDateCommandePrise() {
        return dateCommandePrise;
    }

    public void setDateCommandePrise(String dateCommandePrise) {
        this.dateCommandePrise = dateCommandePrise;
    }

    public String getCommentaireCmdAcces() {
        return commentaireCmdAcces;
    }

    public void setCommentaireCmdAcces(String commentaireCmdAcces) {
        this.commentaireCmdAcces = commentaireCmdAcces;
    }

    public Long getCmd_acces_id() {
        return cmdAccesId;
    }

    public void setCmd_acces_id(Long cmd_acces_id) {
        this.cmdAccesId = cmd_acces_id;
    }

    public String getInfoCmdAcces1() {
        return infoCmdAcces1;
    }

    public void setInfoCmdAcces1(String infoCmdAcces1) {
        this.infoCmdAcces1 = infoCmdAcces1;
    }

    public String getInfoCmdAcces2() {
        return infoCmdAcces2;
    }

    public void setInfoCmdAcces2(String infoCmdAcces2) {
        this.infoCmdAcces2 = infoCmdAcces2;
    }

    public String getInfoCmdAcces3() {
        return infoCmdAcces3;
    }

    public void setInfoCmdAcces3(String infoCmdAcces3) {
        this.infoCmdAcces3 = infoCmdAcces3;
    }

    public String getInfoCmdAcces4() {
        return infoCmdAcces4;
    }

    public void setInfoCmdAcces4(String infoCmdAcces4) {
        this.infoCmdAcces4 = infoCmdAcces4;
    }

    public String getInfoCmdAcces5() {
        return infoCmdAcces5;
    }

    public void setInfoCmdAcces5(String infoCmdAcces5) {
        this.infoCmdAcces5 = infoCmdAcces5;
    }

    public String getInfoCmdAcces6() {
        return infoCmdAcces6;
    }

    public void setInfoCmdAcces6(String infoCmdAcces6) {
        this.infoCmdAcces6 = infoCmdAcces6;
    }

    public String getInfoCmdAcces7() {
        return infoCmdAcces7;
    }

    public void setInfoCmdAcces7(String infoCmdAcces7) {
        this.infoCmdAcces7 = infoCmdAcces7;
    }

    public String getInfoCmdAcces8() {
        return infoCmdAcces8;
    }

    public void setInfoCmdAcces8(String infoCmdAcces8) {
        this.infoCmdAcces8 = infoCmdAcces8;
    }

    public CmdAccesAr getArCmdAcces() {
        return arCmdAcces;
    }

    public void setArCmdAcces(CmdAccesAr arCmdAcces) {
        this.arCmdAcces = arCmdAcces;
    }

    public CmdAccesCr getCrCmdAcces() {
        return crCmdAcces;
    }

    public void setCrCmdAcces(CmdAccesCr crCmdAcces) {
        this.crCmdAcces = crCmdAcces;
    }

    public Fichier getFichier() {
        return fichier;
    }

    public void setFichier(Fichier fichier) {
        this.fichier = fichier;
    }

}
