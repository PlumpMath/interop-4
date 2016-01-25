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
public enum PriseExistante {
    OUI("O"), NON("N");
    
   private final String priseExistante;

    public String getPriseExistante() {
        return priseExistante;
    }

   private PriseExistante(final String priseExistante) {
        this.priseExistante = priseExistante;
    }
   
   
}
