package org.mediaserv.fibre.protocole.domain.gestionfichier;

/**
 *
 * @author Thomas Lecocq
 */
public enum StatusFichier {
    A_POUSSER("A_POUSSER"), EN_COURS("EN_COURS"), EMIS("EMIS"), A_TRAITER("A_TRAITER"), TRAITE("TRAITE");

    private final String statusFichier;

    public String getStatusFichier() {
        return statusFichier;
    }

    private StatusFichier(final String statusFichier) {
        this.statusFichier = statusFichier;
    }

}
