package org.mediaserv.fibre.protocole.domain.gestionfichier;

/**
 *
 * @author Thomas Lecocq
 */
public enum VersionProtocole {
    PM_2_1("V21"),
    ACCES_1_2("V12"),
    WKF_1_0("WKF10");

    private final String version;

    public String getVersion() {
        return version;
    }

    private VersionProtocole(final String version) {
        this.version = version;
    }

}
