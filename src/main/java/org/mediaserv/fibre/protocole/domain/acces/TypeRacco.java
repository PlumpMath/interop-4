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
public enum TypeRacco {
    OC("OC"), OI("OI"), STOC("STOC");

    private final String typeRacco;

    public String getTypeRacco() {
        return typeRacco;
    }

   private TypeRacco(final String typeRacco) {
        this.typeRacco = typeRacco;
    }

}
