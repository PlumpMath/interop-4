package org.mediaserv.fibre.protocole.domain.gestionfichier;

public enum TypeReponse {
    OUI("O"),
    NON("N");

    private final String reponse;

    public String getReponse() {
        return reponse;
    }

    private TypeReponse(final String reponse) {
        this.reponse = reponse;
    }
}
