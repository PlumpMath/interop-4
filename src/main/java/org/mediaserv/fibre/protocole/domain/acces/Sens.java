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
public enum Sens {
    ENTRANT("ENTRANT"), SORTANT("SORTANT");

    private final String sens;

    public String getSens() {
        return sens;
    }

    private Sens(final String sens) {
        this.sens = sens;
    }

}
