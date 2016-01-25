package org.mediaserv.fibre.protocole.domain.gestionfichier;

/**
 *
 * @author Thomas Lecocq
 */
public enum StatusTraitement {
    ECHEC("ECHEC"), EN_COURS("EN_COURS"), REUSSI("REUSSI");

    private final String statusTraitement;

    public String getStatusTraitement() {
        return statusTraitement;
    }

    private StatusTraitement(final String statusTraitement) {
        this.statusTraitement = statusTraitement;
    }

}
