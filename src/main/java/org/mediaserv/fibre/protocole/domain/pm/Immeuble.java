package org.mediaserv.fibre.protocole.domain.pm;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Thomas Lecocq
 */
@Entity
@Table(name = "IMMEUBLE", uniqueConstraints = {
    @UniqueConstraint(name = "UK_ID_IMMEUBLE", columnNames = {"IMM_IDENTIFIANT"}),
    @UniqueConstraint(name = "UK_ID_IMMEUBLE_CODE_ADRESSE", columnNames = {"IMM_IDENTIFIANT", "IMM_CODE_ADRESSE"})})
public class Immeuble implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_IMMEUBLE", initialValue = 1, allocationSize = 1)
    @Column(name = "IMM_ID")
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey=@ForeignKey(name="FK_IMMEUBLE_GEST"))
    private Gestionnaire gestionnaire;

    /**
     * "Identifiant de l'adresse publiée (pavillon ou immeuble), propre au
     * référentiel d'adresse de l'opérateur d'immeuble. Ce champ est facultatif.
     * Cependant quand il est renseigné et géré par l'opérateur d'immeuble, il
     * correspond à un identifiant unique par ligne de l'IPE
     *
     * Cet identifiant peut servir à publier les adresses pour lesquelles le SNA
     * n'a pas encore fourni d'identifiant hexaclé (CodeAdresseImmeuble) ainsi
     * qu'à aller jusqu'au bâtiment dans la description de l'adresse. Dans le
     * cas où cet identifiant est géré pour aller jusqu'au batiment, il peut y
     * avoir alors plusieurs lignes pour un même code hexaclé
     * (CodeAdresseImmeuble) Cet identifiant peut également permettre une
     * certaine traçabilité des modifications ou des créations d'hexaclés pour
     * une adresse donnée."
     */
    @Length(max = 20)
    @Column(name = "IMM_IDENTIFIANT")
    private String identifiantImmeuble;

    /**
     * Code associé à la voie de l'adresse publiée tel que décrit dans les
     * référentiels "Rivoli". Ce champ est facultatif et n'est pas normalisé. Il
     * permet cependant pour les opérateurs d'immeuble qui le gèrent de
     * retrouver le nom de la voie dans les référentiels Rivoli.
     */
    @Length(max = 4)
    @Column(name = "IMM_CODE_VOIE_RIVOLI")
    private String codeVoieRivoliImmeuble;

    /**
     * Code insee de l'adresse publiée. Ce code permet de retrouver la commune
     * concernée par la rue
     */
    @Length(max = 5)
    @Column(name = "IMM_CODE_INSEE")
    private String codeInseeImmeuble;

    /**
     * Code postal de l'adresse publiée
     */
    @Length(max = 5)
    @Column(name = "IMM_CODE_POSTAL")
    private String codePostalImmeuble;

    /**
     * Commune de l'adresse publiée
     */
    @Column(name = "IMM_COMMUNE")
    private String communeImmeuble;

    /**
     * Code "hexaclé numéro" associé à l'adresse publiée tel que décrit dans les
     * référentiels du SNA (diffusion par Mediapost, Uniserve, etc) Ce code est
     * défini par le groupe Interop comme l'identifiant unique de l'adresse
     * utilisé dans les échanges inter-opérateurs sur l'infrastructure et
     * l'accès Ce champ est obligatoire. En cas d'absence de ce code au moment
     * de la diffusion de l'adresse dans l'IPE, il est à la charge de
     * l'opérateur d'immeuble d'en demander sa création auprès du SNA.
     *
     * Cet identifiant est unique pour une adresse donnée mais il peut
     * apparaitre plusieurs fois chez certains opérateurs qui décrivent
     * l'adresse jusqu'au champ batiment. Les champs IdentifiantImmeuble sont
     * alors différents pour chaque ligne. Les champs adresses de l'Immeuble
     * sont identiques jusqu'au ComplementNumeroVoie et diffèrent sur le
     * BatimentImmeuble.
     */
    @Length(max = 10)
    @Column(name = "IMM_CODE_ADRESSE")
    private String codeAdresseImmeuble;

    /**
     * Type de voie de l'adresse publiée
     */
    @Column(name = "IMM_TYPE_VOIE")
    private String typeVoieImmeuble;

    /**
     * Nom de voie de l'adresse publiée
     */
    @Column(name = "IMM_NOM_VOIE")
    private String nomVoieImmeuble;

    /**
     * "Numéro de voie de l'adresse publiée
     *
     * Dans le cas de regroupements de parcelles de type 166-170, il peut
     * exister un code Hexaclé pour le 166, le 168, le 170 et le regroupement
     * 166-170. Dans le cas de l'adresse regroupée, ce champ prendra la valeur
     * concaténée des deux numéros (par exemple, 166 et 170) décrivant le
     * regroupement (par exemple, 166170). Ce champ doit être rempli avec 0
     * quand aucun numéro n'a été attribué dans cette rue. Le 0 ne constitue pas
     * une valeur par défaut : si le numero est inconnu de l'opérateur
     * d'immeuble, le champ restera vide et pourra être un motif à rejet de la
     * ligne par certains opérateurs commerciaux"
     */
    @Length(max = 5)
    @Column(name = "IMM_NUMERO_VOIE")
    private String numeroVoieImmeuble;

    /**
     * "Complément de numéro de voie. Ce champ est facultatif et composé d'une
     * seule lettre Exemple B= pour BIS, T pour TER, etc."
     *
     */
    @Column(name = "IMM_NUMERO_VOIE_COMPL")
    private String complementNumeroVoieImmeuble;

    /**
     * Ce champ correspond au nom du batiment tel que décrit par l'opérateur
     * d'immeuble en cohérence avec ce qu'il constate sur le terrain. Ce champ
     * peut apparaitre après la publication de l'adresse dans l'IPE car
     * fiabilisé au cours de la phase de piquetage terrain.
     */
    @Column(name = "IMM_BATIMENT")
    private String batimentImmeuble;

    /**
     * "Nombre de logements de l'adresse telle que décrite sur la ligne de
     * l'IPE. Ce nombre de logements peut donc par exemple décrire le nombre de
     * logements du bâtiment si la ligne de l'IPE décrit un batiment. * La somme
     * des NombreLogementsAdresseIPE des lignes portant un même
     * CodeAdresseImmeuble correspondra au nombre de locaux FTTH de l'adresse.
     *
     * Le nombre de logements de ce champ dans l'IPE doit être strictement
     * identique à celui communiqué dans le CR MAD de l'adresse. En cas
     * d'incohérence, le nombre de logements dans l'IPE doit être mis en
     * cohérence avec celui du CR MAD
     *
     * Le terme logement peut inclure des entreprises et commerces ou non selon
     * l'opérateur"
     *
     */
    @Length(max = 5)
    @Column(name = "IMM_NB_LOGEMENT_ADDR_IPE")
    private String nombreLogementsAdresseIPE;

    /**
     * "Champ permettant à l'opérateur d'immeuble de communiquer le statut de
     * l'adresse. Ce statut s'applique à l'adresse uniquement, le champ EtatPM
     * permettant de communiquer le statut du PM.
     *
     * Ce champ permet d'indiquer l'avancement du déploiement et des
     * négociations syndics de l'adresse : - Ciblé signifie que l'adresse se
     * situe dans la zone arrière d'un PM déployé ou en cours de déploiement ou
     * ayant fait l'objet d'une consultation - Signé signifie qu'une convention
     * a été signée avec le gestionnaire de l'adresse. Le statut signé ne peut
     * s'appliquer que si AccordGestionnaireImmeuble = ""O"" - En cours de
     * déploiement signifie que l'adresse est en cours de déploiement, sans
     * qu'une définition précise de ce terme n'ait été partagée en Interop -
     * Déployé signifie que l'adresse est techniquement raccordable en fibre,
     * que le PB est posé et que l'adresse a été mise à disposition aux
     * opérateurs commerciaux. Déployé correspond à un état ""raccordable"" de
     * l'adresse. - Abandonné signifie que la commercialisation de l'adresse est
     * annulée par l'opérateur d'immeuble, quelqu'en soit le motif
     * (déconventionnement, insécurité installateur, fiabilisation des adresses,
     * destruction de l'immeuble …). Une adresse peut passer au statut abandonné
     * à tout moment. Elle y reste pendant 3 mois avant que la ligne ne
     * disparaisse de l'IPE : n'apparaissent donc dans l'IPE que les adresses
     * abandonnées dans les 3 mois précédants la publication de l'IPE
     *
     * Si AccordGestionnaire Immeuble = ""O"", EtatImmeuble prendra les valeurs
     * SIGNE, puis EN COURS DE DEPLOIEMENT, puis DEPLOYE. L'adresse peut
     * apparaitre à l'état CIBLE. Si AccordGestionnaireImmeuble = ""N"",
     * EtatImmeuble prendra les valeurs CIBLE, puis EN COURS DE DEPLOIEMENT,
     * puis DEPLOYE."
     *
     */
    @Column(name = "IMM_ETAT")
    private String etatImmeuble;

    /**
     * "Date prévisionnelle ou effective du câblage de l'adresse c'est à dire de
     * déploiement de l'adresse. Cette date correspond à la date à laquelle
     * EtatImmeuble passera à l'état déployé et l'adresse sera raccordable.
     *
     * Si EtatImmeuble est différent de ""DEPLOYE"", ce champ peut contenir une
     * date prévisionnelle de déploiement. Si EtatImmeuble vaut ""DEPLOYE"", il
     * s'agit de la date effective de déploiement de l'adresse.
     *
     * Ce champ est notamment utile dans le cas de PM Exterieurs avec des
     * adresses fibrées au fil de l'eau. Il permet à l'OI de renseigner les
     * dates prévisionnelles ou effectives de câblage de ces adresses"
     *
     */
    @Length(max = 8)
    @Column(name = "IMM_DATE_CABLAGE_ADDR")
    private String dateCablageAdresse;

    /**
     * Ce champ indique la date de dernière modification effectuée dans une
     * ligne, quelle que soit cette modification
     *
     */
    @Length(max = 8)
    @Column(name = "IMM_DATE_DER_MODIF")
    private String dateDerniereModification;

    /**
     * Coordonnées X de l'adresse ou du batiment concerné
     *
     */
    @Column(name = "IMM_COORD_X")
    private String coordonneeImmeubleX;

    /**
     * Coordonnées Y de l'adresse ou du batiment concerné
     *
     */
    @Column(name = "IMM_COORD_Y")
    private String coordonneeImmeubleY;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters & Setters
    public String getIdentifiantImmeuble() {
        return identifiantImmeuble;
    }

    public void setIdentifiantImmeuble(String identifiantImmeuble) {
        this.identifiantImmeuble = identifiantImmeuble;
    }

    public String getCodeVoieRivoliImmeuble() {
        return codeVoieRivoliImmeuble;
    }

    public void setCodeVoieRivoliImmeuble(String codeVoieRivoliImmeuble) {
        this.codeVoieRivoliImmeuble = codeVoieRivoliImmeuble;
    }

    public String getCodeInseeImmeuble() {
        return codeInseeImmeuble;
    }

    public void setCodeInseeImmeuble(String codeInseeImmeuble) {
        this.codeInseeImmeuble = codeInseeImmeuble;
    }

    public String getCodePostalImmeuble() {
        return codePostalImmeuble;
    }

    public void setCodePostalImmeuble(String codePostalImmeuble) {
        this.codePostalImmeuble = codePostalImmeuble;
    }

    public String getCommuneImmeuble() {
        return communeImmeuble;
    }

    public void setCommuneImmeuble(String communeImmeuble) {
        this.communeImmeuble = communeImmeuble;
    }

    public String getCodeAdresseImmeuble() {
        return codeAdresseImmeuble;
    }

    public void setCodeAdresseImmeuble(String codeAdresseImmeuble) {
        this.codeAdresseImmeuble = codeAdresseImmeuble;
    }

    public String getTypeVoieImmeuble() {
        return typeVoieImmeuble;
    }

    public void setTypeVoieImmeuble(String typeVoieImmeuble) {
        this.typeVoieImmeuble = typeVoieImmeuble;
    }

    public String getNomVoieImmeuble() {
        return nomVoieImmeuble;
    }

    public void setNomVoieImmeuble(String nomVoieImmeuble) {
        this.nomVoieImmeuble = nomVoieImmeuble;
    }

    public String getNumeroVoieImmeuble() {
        return numeroVoieImmeuble;
    }

    public void setNumeroVoieImmeuble(String numeroVoieImmeuble) {
        this.numeroVoieImmeuble = numeroVoieImmeuble;
    }

    public String getComplementNumeroVoieImmeuble() {
        return complementNumeroVoieImmeuble;
    }

    public void setComplementNumeroVoieImmeuble(String complementNumeroVoieImmeuble) {
        this.complementNumeroVoieImmeuble = complementNumeroVoieImmeuble;
    }

    public String getBatimentImmeuble() {
        return batimentImmeuble;
    }

    public void setBatimentImmeuble(String batimentImmeuble) {
        this.batimentImmeuble = batimentImmeuble;
    }

    public String getNombreLogementsAdresseIPE() {
        return nombreLogementsAdresseIPE;
    }

    public void setNombreLogementsAdresseIPE(String nombreLogementsAdresseIPE) {
        this.nombreLogementsAdresseIPE = nombreLogementsAdresseIPE;
    }

    public String getEtatImmeuble() {
        return etatImmeuble;
    }

    public void setEtatImmeuble(String etatImmeuble) {
        this.etatImmeuble = etatImmeuble;
    }

    public String getDateCablageAdresse() {
        return dateCablageAdresse;
    }

    public void setDateCablageAdresse(String dateCablageAdresse) {
        this.dateCablageAdresse = dateCablageAdresse;
    }

    public String getDateDerniereModification() {
        return dateDerniereModification;
    }

    public void setDateDerniereModification(String dateDerniereModification) {
        this.dateDerniereModification = dateDerniereModification;
    }

    public Gestionnaire getGestionnaire() {
        return gestionnaire;
    }

    public void setGestionnaire(Gestionnaire gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    public String getCoordonneeImmeubleX() {
        return coordonneeImmeubleX;
    }

    public void setCoordonneeImmeubleX(String coordonneeImmeubleX) {
        this.coordonneeImmeubleX = coordonneeImmeubleX;
    }

    public String getCoordonneeImmeubleY() {
        return coordonneeImmeubleY;
    }

    public void setCoordonneeImmeubleY(String coordonneeImmeubleY) {
        this.coordonneeImmeubleY = coordonneeImmeubleY;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.identifiantImmeuble != null ? this.identifiantImmeuble.hashCode() : 0);
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
        final Immeuble other = (Immeuble) obj;
        if ((this.identifiantImmeuble == null) ? (other.identifiantImmeuble != null) : !this.identifiantImmeuble.equals(other.identifiantImmeuble)) {
            return false;
        }
        return true;
    }

}
