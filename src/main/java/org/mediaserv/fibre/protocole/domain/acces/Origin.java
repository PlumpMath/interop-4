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
public enum Origin {
    GP("GP"), OI("OI"), BUE("BUE");

    private final String origin;

    public String getOrigin() {
        return origin;
    }

    private Origin(final String origin) {
        this.origin = origin;
    }

}
