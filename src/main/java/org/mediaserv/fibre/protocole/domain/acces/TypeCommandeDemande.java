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
public enum TypeCommandeDemande {
    AUTO("AUTO"), LOCA("LOCA") ,COFI("COFI");
    
    private final String typeCommandeDemande;

    public String getTypeCommandeDemande() {
        return typeCommandeDemande;
    }

    private TypeCommandeDemande(final String typeCommandeDemande) {
        this.typeCommandeDemande = typeCommandeDemande;
    }
    
    
}
