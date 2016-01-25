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
public enum TypeCmdRetenu {
    NA("NA"), LOCA("LOCA"), COFI("COFI"); 
    
    private final String typeCmdRetenu;

    public String getTypeCmdRetenu() {
        return typeCmdRetenu;
    }

    private TypeCmdRetenu(final String typeCmdRetenu) {
        this.typeCmdRetenu = typeCmdRetenu;
    }
    
    
}
