/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mediaserv.fibre.protocole.domain.acces;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.mediaserv.fibre.protocole.domain.gestionfichier.CodeOperateur;

/**
 *
 * @author RREMBLIN
 */
@Entity
@Table(name = "COMMANDE")
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_ACCES", initialValue = 1, allocationSize = 1)
    @Column(name = "CMD_ID")
    private Long idCmd;

    @Column(name = "STATUS_CMD")
    @Enumerated(EnumType.STRING)
    private StatusCmd statusCmd;

    @Column(name = "CMD_REF_WKF")
    private String cmdRefWkf;

    @Column(name = "OP_EMETTEUR")
    @Enumerated(EnumType.STRING)
    private CodeOperateur op_emetteur;

    @Column(name = "OP_DESTINATEUR")
    @Enumerated(EnumType.STRING)
    private CodeOperateur op_destinataire;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "CMD_ID")
    private Commande cmd_acces_init;

    @Column(name = "ENTITY_FROM")
    @Enumerated(EnumType.STRING)
    private Origin entityFrom;

    @Column(name = "ENTITY_DEST")
    @Enumerated(EnumType.STRING)
    private Origin entityDest;

    @Column(name = "NUM_CONTRAT")
    private String numContrat;

    //ReferenceCommandePriseInterneOC
    @Column(name = "REF_CMD_OC")
    private String refCmdOc;

    @Column(name = "SENS")
    @Enumerated(EnumType.STRING)
    private Sens sens;

    @Column(name = "ETAT_CMD")
    @Enumerated(EnumType.STRING)
    private EtatCmd etatCmd;

    @Column(name = "DATE_INSERT")
    private Timestamp dateInsert;

    @Column(name = "DATE_UPDATE")
    private String dateUpdate;

    public Long getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(Long id_cmd) {
        this.idCmd = id_cmd;
    }

    public Commande getCmdAccesInit() {
        return cmd_acces_init;
    }

    public void setCmdAccesInit(Commande cmd_acces_init) {
        this.cmd_acces_init = cmd_acces_init;
    }

    public String getCmdRefWkf() {
        return cmdRefWkf;
    }

    public void setCmdRefWkf(String cmd_ref_wkf) {
        this.cmdRefWkf = cmd_ref_wkf;
    }

    public Timestamp getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(Timestamp dateInsert) {
        this.dateInsert = dateInsert;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String date_update) {
        this.dateUpdate = date_update;
    }

    public Origin getEntityFrom() {
        return entityFrom;
    }

    public void setEntityFrom(Origin entity_from) {
        this.entityFrom = entity_from;
    }

    public Origin getEntityDest() {
        return entityDest;
    }

    public void setEntityDest(Origin entity_dest) {
        this.entityDest = entity_dest;
    }

    public StatusCmd getStatusCmd() {
        return statusCmd;
    }

    public void setStatusCmd(StatusCmd statusCmd) {
        this.statusCmd = statusCmd;
    }

    public CodeOperateur getOpEmetteur() {
        return op_emetteur;
    }

    public void setOpEmetteur(CodeOperateur op_emetteur) {
        this.op_emetteur = op_emetteur;
    }

    public CodeOperateur getOpDestinataire() {
        return op_destinataire;
    }

    public void setOpDestinataire(CodeOperateur op_destinataire) {
        this.op_destinataire = op_destinataire;
    }

    public String getRefCmdOc() {
        return refCmdOc;
    }

    public void setRefCmdOc(String ref_cmd_oc) {
        this.refCmdOc = ref_cmd_oc;
    }

    public Sens getSens() {
        return sens;
    }

    public void setSens(Sens sens) {
        this.sens = sens;
    }

    public String getNumContrat() {
        return numContrat;
    }

    public void setNumContrat(String num_contrat) {
        this.numContrat = num_contrat;
    }

    public CodeOperateur getOp_emetteur() {
        return op_emetteur;
    }

    public void setOp_emetteur(CodeOperateur op_emetteur) {
        this.op_emetteur = op_emetteur;
    }

    public CodeOperateur getOp_destinataire() {
        return op_destinataire;
    }

    public void setOp_destinataire(CodeOperateur op_destinataire) {
        this.op_destinataire = op_destinataire;
    }

    public Commande getCmd_acces_init() {
        return cmd_acces_init;
    }

    public void setCmd_acces_init(Commande cmd_acces_init) {
        this.cmd_acces_init = cmd_acces_init;
    }

    public EtatCmd getEtatCmd() {
        return etatCmd;
    }

    public void setEtatCmd(EtatCmd etatCmd) {
        this.etatCmd = etatCmd;
    }

}
