package org.mediaserv.fibre.protocole.domain.gestionfichier;

/**
 *
 * @author Thomas Lecocq
 *
 */
public enum TypeOperation {

    IPE_ZONE_TRES_DENSE("IPE"),
    IPE_ZONE_MOINS_DENSE("IPEZMD"),
    CPN("CPN"),
    CMD_PM("CMD"),
    ACCUSE_RECEP_CMD("ARCMD"),
    COMPTE_RENDU_MAD("CRMAD"),
    ACCUSE_RECEP_MAD("ARMAD"),
    NOTIF_INTERV_PREV("INTERP"),
    COMPTE_RENDU_INFO_SYNDIC("INFOSY"),
    NOTIF_ADDUCTION("ADDU"),
    COMPTE_RENDU_ADDUCTION("CrADDU"),
    CMD_ANNULATION_RESILIATION("AnnRes"),
    CR_ANNULATION_RESILIATION("CrAnnRes"),
    ACCES("ACCES"),
    ACCUSE_RECEP_ARACCES("ARACCES"),
    NOTIF_AR("NOTIFAR"),
    NOTIF_CR("NOTIFCR"),
    ACCUSE_RECEP_CRACCES("CRACCES");

    private final String operation;

    public String getOperation() {
        return operation;
    }

    private TypeOperation(final String typeOperation) {
        this.operation = typeOperation;
    }
}
