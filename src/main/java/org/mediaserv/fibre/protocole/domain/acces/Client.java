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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author RREMBLIN
 */
@Entity
@Table(name = "CLIENT", uniqueConstraints = {
    @UniqueConstraint(name = "UK_REF_CLIENT", columnNames = {"PRENOM_CLIENT", "NOM_CLIENT", "NUM_CONTRAT"})})
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_CLIENT", initialValue = 1, allocationSize = 1)
    @Column(name = "CLIENT_ID")
    private Long clientId;

    @Column(name = "NOM_CLIENT")
    private String nomClient;

    @Column(name = "PRENOM_CLIENT")
    private String prenomClient;

    @Column(name = "NUM_CONTRAT")
    private String numContrat;

    @Column(name = "NUM_WKF")
    private String numCmdWkf;

    @Column(name = "CONTACT_CLIENT1")
    private String contactClient1;

    @Column(name = "CONTACT_CLIENT2")
    private String contactClient2;

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getContactClient1() {
        return contactClient1;
    }

    public void setContactClient1(String contactClient1) {
        this.contactClient1 = contactClient1;
    }

    public String getContactClient2() {
        return contactClient2;
    }

    public void setContactClient2(String contactClient2) {
        this.contactClient2 = contactClient2;
    }

    public String getNumContrat() {
        return numContrat;
    }

    public void setNumContrat(String numContrat) {
        this.numContrat = numContrat;
    }

    public String getNumCmdWkf() {
        return numCmdWkf;
    }

    public void setNumCmdWkf(String numCmdWkf) {
        this.numCmdWkf = numCmdWkf;
    }

}
