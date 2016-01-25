package org.mediaserv.fibre.protocole.domain.gestionfichier;

/**
 *
 * @author Thomas Lecocq
 */
public enum CodeOperateur {

    ORANGE("FTEL"),
    MEDIASERV("MIAS");

    private final String code;

    public String getCode() {
        return code;
    }

    private CodeOperateur(final String code) {
        this.code = code;
    }

}
