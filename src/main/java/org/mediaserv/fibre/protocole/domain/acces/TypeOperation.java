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
public enum TypeOperation {
    ANNUL("ANNUL"), RESIL("RESIL");

    private final String typeOperation;

    public String getTypeOperation() {
        return typeOperation;
    }

    private TypeOperation(final String typeOperation) {
        this.typeOperation = typeOperation;
    }

}
