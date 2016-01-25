package org.mediaserv.fibre.protocole.domain.gestionfichier;

/**
 *
 * @author Thomas Lecocq
 */
public enum TypeExtension {
    CSV(".csv"),
    ZIP(".zip"),
    XML(".xml");

    private final String extension;

    public String getExtension() {
        return extension;
    }

    private TypeExtension(final String extension) {
        this.extension = extension;
    }

}
