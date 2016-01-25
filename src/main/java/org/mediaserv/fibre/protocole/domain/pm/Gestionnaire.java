package org.mediaserv.fibre.protocole.domain.pm;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Thomas Lecocq
 */
@Entity
@Table(name = "GESTIONNAIRE", uniqueConstraints = {
    @UniqueConstraint(name = "UK_GEST_CODE_POSTAL", columnNames = {"GEST_IMMEUBLE", "GEST_CODE_POSTAL"})})
public class Gestionnaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_GEST", initialValue = 1, allocationSize = 1)
    @Column(name = "GEST_ID")
    private Long id;

    /**
     * "Date de la signature de la convention avec le gestionnaire de
     * l'immeuble, devant être renseignée si
     * AccordGestionnaireImmeubleNecessaire = ""O"" et EtatImmeuble = ""SIGNE""
     * ou ""EN COURS DE DEPLOIEMENT"" ou ""DEPLOYE"" Cette information
     * conditionne le délai légal de câblage de l'adresse 6 mois au plus tard
     * après signature"
     *
     */
    @Column(name = "GEST_DATE_SIGNATURE_CONV")
    private String dateSignatureConvention;
    /**
     * "Nom de la société gestionnaire d'immeuble La valeur de ce champ n'est
     * obligatoire qu'à partir de l'état SIGNE. Le remplissage de ce champ est
     * obligatoire si AccordGestionnaireImmeubleNecessaire = ""O"" et
     * DateSignatureConvention est renseigné"
     *
     */
    @Column(name = "GEST_IMMEUBLE")
    private String gestionnaireImmeuble;
    /**
     * "Code postal de la société gestionnaire d'immeuble La valeur de ce champ
     * n'est obligatoire qu'à partir de l'état SIGNE. Le remplissage de ce champ
     * est obligatoire si AccordGestionnaireImmeubleNecessaire = ""O"" et
     * DateSignatureConvention est renseigné"
     *
     */
    @Length(max = 5)
    @Column(name = "GEST_CODE_POSTAL")
    private String codePostalGestionnaire;
    /**
     * "Commune de la société gestionnaire d'immeuble La valeur de ce champ
     * n'est obligatoire qu'à partir de l'état SIGNE. Le remplissage de ce champ
     * est obligatoire si AccordGestionnaireImmeubleNecessaire = ""O"" et
     * DateSignatureConvention est renseigné"
     *
     */
    @Column(name = "GEST_COMMUNE")
    private String communeGestionnaire;
    /**
     * "Type de voie de la société gestionnaire d'immeuble Ce champ est
     * facultatif"
     */
    @Column(name = "GEST_TYPE_VOIE")
    private String typeVoieGestionnaire;
    /**
     * "Nom de voie de la société gestionnaire d'immeuble La valeur de ce champ
     * n'est obligatoire qu'à partir de l'état SIGNE. Le remplissage de ce champ
     * est obligatoire si AccordGestionnaireImmeubleNecessaire = ""O"" et
     * DateSignatureConvention est renseigné"
     *
     */
    @Column(name = "GEST_NOM_VOIE")
    private String nomVoieGestionnaire;
    /**
     * "Numero de voie de la société gestionnaire d'immeuble. Ce champ est
     * facultatif"
     *
     */
    @Length(max = 5)
    @Column(name = "GEST_NUMERO_VOIE")
    private String numeroVoieGestionnaire;

    /**
     * "Complément de numero de voie de la société gestionnaire d'immeuble. Ce
     * champ est facultatif et composé d'une seule lettre. Exemple B= pour BIS,
     * T pour TER, etc."
     *
     */
    @Column(name = "GEST_NUMERO_VOIE_COMPL")
    private String complementNumeroVoieGestionnaire;
    /**
     * Code correspondant à l'hexaclé numéro de l'adresse du gestionnaire
     * d'immeuble tel que décrit dans le référentiel SNA. Ce champ est
     * facultatif
     *
     */
    @Column(name = "GEST_CODE_ADRESSE")
    private String codeAdresseGestionnaire;
    /**
     * n° de siret du gestionnaire d'immeuble. Ce champ est facultatif
     *
     */
    @Column(name = "GEST_SIRET")
    private String siretGestionnaire;

    // Getters & Setters
    public String getDateSignatureConvention() {
        return dateSignatureConvention;
    }

    public void setDateSignatureConvention(String dateSignatureConvention) {
        this.dateSignatureConvention = dateSignatureConvention;
    }

    public String getGestionnaireImmeuble() {
        return gestionnaireImmeuble;
    }

    public void setGestionnaireImmeuble(String gestionnaireImmeuble) {
        this.gestionnaireImmeuble = gestionnaireImmeuble;
    }

    public String getCodePostalGestionnaire() {
        return codePostalGestionnaire;
    }

    public void setCodePostalGestionnaire(String codePostalGestionnaire) {
        this.codePostalGestionnaire = codePostalGestionnaire;
    }

    public String getCommuneGestionnaire() {
        return communeGestionnaire;
    }

    public void setCommuneGestionnaire(String communeGestionnaire) {
        this.communeGestionnaire = communeGestionnaire;
    }

    public String getTypeVoieGestionnaire() {
        return typeVoieGestionnaire;
    }

    public void setTypeVoieGestionnaire(String typeVoieGestionnaire) {
        this.typeVoieGestionnaire = typeVoieGestionnaire;
    }

    public String getNomVoieGestionnaire() {
        return nomVoieGestionnaire;
    }

    public void setNomVoieGestionnaire(String nomVoieGestionnaire) {
        this.nomVoieGestionnaire = nomVoieGestionnaire;
    }

    public String getNumeroVoieGestionnaire() {
        return numeroVoieGestionnaire;
    }

    public void setNumeroVoieGestionnaire(String numeroVoieGestionnaire) {
        this.numeroVoieGestionnaire = numeroVoieGestionnaire;
    }

    public String getComplementNumeroVoieGestionnaire() {
        return complementNumeroVoieGestionnaire;
    }

    public void setComplementNumeroVoieGestionnaire(String complementNumeroVoieGestionnaire) {
        this.complementNumeroVoieGestionnaire = complementNumeroVoieGestionnaire;
    }

    public String getCodeAdresseGestionnaire() {
        return codeAdresseGestionnaire;
    }

    public void setCodeAdresseGestionnaire(String codeAdresseGestionnaire) {
        this.codeAdresseGestionnaire = codeAdresseGestionnaire;
    }

    public String getSiretGestionnaire() {
        return siretGestionnaire;
    }

    public void setSiretGestionnaire(String siretGestionnaire) {
        this.siretGestionnaire = siretGestionnaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.gestionnaireImmeuble != null ? this.gestionnaireImmeuble.hashCode() : 0);
        hash = 71 * hash + (this.codePostalGestionnaire != null ? this.codePostalGestionnaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gestionnaire other = (Gestionnaire) obj;
        if ((this.gestionnaireImmeuble == null) ? (other.gestionnaireImmeuble != null) : !this.gestionnaireImmeuble.equals(other.gestionnaireImmeuble)) {
            return false;
        }
        if ((this.codePostalGestionnaire == null) ? (other.codePostalGestionnaire != null) : !this.codePostalGestionnaire.equals(other.codePostalGestionnaire)) {
            return false;
        }
        return true;
    }

}
