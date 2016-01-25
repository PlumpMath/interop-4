package org.mediaserv.fibre.protocole.domain.acces;

/**
 *
 * @author Thomas Lecocq
 */
public enum StatusCmd {
    A_POUSSER("A_POUSSER"), EMIS("EMIS"), A_TRAITER("A_TRAITER"), TRAITE("TRAITE");
    
    private final String statusCmd;

    public String getStatusCmd() {
        return statusCmd;
    }

    private StatusCmd(final String statusCmd) {
        this.statusCmd = statusCmd;
    }
    
}