package org.mediaserv.fibre.protocole.domain.gestionfichier;

/**
 *
 * @author Thomas Lecocq
 */
public enum TypeProtocole {
    PM("PM"),
    ACCES("ACCES"),
    SAV("SAV"),
    WKF("WKF"),;
    
    private final String protocole;

    public String getProtocole() {
        return protocole;
    }

    private TypeProtocole(final String typeProtocole) {
        this.protocole = typeProtocole;
    }
}
