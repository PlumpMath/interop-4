/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mediaserv.fibre.protocole.domain.acces;

/**
 *
 * @author RREMBLIN
 */
public enum EtatCmd {
      ACCES("ACCES"), ACCES_AR("ACCES_AR"), ACCES_CR("ACCES_CR"), ACCES_ANNU("ACCES_ANNU"), ACCES_ANNU_CR("ACCES_ANNU_CR"), ACCES_CR_MAD("ACCES_CR_MAD"), ACCES_CR_MES("ACCES_CR_MES");

    private final String etatCmd;

    public String getEtatCmd() {
        return etatCmd;
    }

    private EtatCmd(final String etatCmd) {
        this.etatCmd = etatCmd;
    }

}
