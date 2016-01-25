package org.mediaserv.fibre.protocole.domain.gestionfichier;

/**
 * Dedie ou Partage
 * @author JOPHA Olivier
 *
 */
public enum TypeChoixTechniqueOc {
    DEDIE("D"),
    PARTAGE("P");

    private final String choix;

    public String getChoix() {
        return choix;
    }

    private TypeChoixTechniqueOc(final String choix) {
        this.choix = choix;
    }
}
