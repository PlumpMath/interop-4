package org.mediaserv.fibre.protocole.domain.pm;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import org.hibernate.envers.ModificationStore;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.mediaserv.fibre.protocole.domain.gestionfichier.Fichier;

/**
 *
 * @author Thomas Lecocq
 */
@Entity
@Table(name = "PM", uniqueConstraints = {@UniqueConstraint(name = "UK_REF_PM", columnNames = {"PM_REFERENCE"})})
public class PM implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_PM", initialValue = 1, allocationSize = 1)
    private Long id;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="PBO_PM"))
    private Collection<PBO> pbos;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDUCT_ID")
    private Adduction adduction;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "POS_ID")
    private Position position;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Collection<Fichier> historiqueFichiers;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey=@ForeignKey(name="ZCOF_PM"))
    private ZoneCofinancement zoneCofinancement;

    @Lob
    @Audited(modStore = ModificationStore.FULL, modifiedColumnName = "", targetAuditMode = RelationTargetAuditMode.AUDITED, withModifiedFlag = false)
    @Column(name = "PM_FIC_PLAN")
    private byte[] fichierPlan;

    /**
     * "Référence PM propre à chaque OI et pérenne. La referencePM est
     * obligatoire dès lors que le PM est en cours de déploiement et ne peut
     * apparaître avant La référence PM est celle du PM de Regroupement dans le
     * cas de plusieurs PMTechniques rattachés au même PM"
     *
     */
    @Length(max = 20)
    @Column(name = "PM_REFERENCE")
    private String referencePM;

    /**
     * "Etat PM lié au process de déploiement du PM et conditionné à la présence
     * de la referencePM : l'EtatPM doit être renseigné dès lors que le PM
     * apparait dans l'IPE - En cours de déploiement signifie que le PM est en
     * cours d'installation, sans qu'une définition précise n'ait été partagée
     * en Interop - Déployé signifie que le PM est installé sur le terrain. Il
     * doit alors être mis à disposition des opérateurs ayant acheté le PM -
     * Abandonné signifie que le PM est abandonné. Cet état doit apparaitre
     * pendant 3 mois. Les règles de gestion associées au passage à l'état
     * abandonné ne sont pas précisées
     *
     * En cours de déploiement est le statut par défaut à l'apparition du PM
     * dans l'IPE.
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @NotNull
    @Column(name = "PM_ETAT")
    private String etatPM;

    /**
     * "Date d'installation du PM, qu'il soit intérieur ou extérieur. Cette date
     * correspond à la date de passage à l'état déployé du PM. Cette date est
     * obligatoire dès lors qu'une referencePM existe. Elle est prévisionnelle
     * si EtatPM est ""en cours de déploiement"" et effective si EtatPM est
     * ""déployé""
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @Length(max = 8)
    @NotNull
    @NotEmpty
    @Column(name = "PM_DATE_INSTALLATION")
    private String dateInstallationPM;

    /**
     * "Ce champ permet de décrire la localisation physique du PM (façade,
     * poteau, chambre, intérieur…) et/ou type de PM (shelter, armoire de rue,
     * en sous-sol….). La liste de valeurs suivante est proposée pour ce champ :
     * - PME-Armoire de rue : PM Extérieur au sens de la réglementation, contenu
     * dans une armoire de rue - PME-Shelter : PM Extérieur au sens de la
     * réglementation, contenu dans un shelter - PME-Local technique : PM
     * Extérieur au sens de la réglementation, contenu dans un local technique,
     * par exemple NRO - PME : PM Extérieur au sens de la réglementation, dont
     * l’information du contenu n’est pas disponible dans le SI de l’OI
     * (notamment pour le cas des parcs historiques avant normalisation du
     * contenu de ce champ) - PMI = PM Intérieur c’est à dire situé dans une
     * partie privative nécessitant l’accord d’un tiers (syndic, gestionnaire)
     * en plus de l’accord de l’OI
     *
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @NotNull
    @NotEmpty
    @Column(name = "PM_TYPE_EMPLACEMENT")
    private String typeEmplacementPM;

    /**
     * Ce champ est facultatif et permet à l'opérateur d'immeuble d'expliciter
     * si besoin la Localisation du PM (s'il n'a pas d'adresse par exemple)
     *
     */
    @Column(name = "PM_COMMENTAIRE")
    private String commentairePM;

    /**
     * "Ce champ correspond à la capacité maximum théorique. * Ce champ est
     * facultatif dans le protocole mais il a été proposé un remplissage
     * systématique du champ
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @Column(name = "PM_CAPACITE_MAX")
    private String capaciteMaxPM;

    /**
     * "Code de la voie permettant à l'opérateur d'immeuble de communiquer les
     * éléments d'adresse du PM tel que décrits dans le référentiel Rivoli
     *
     * Ce champ est conditionné à la présence de la référence PM c'est-à-dire
     * renseigné de façon facultative si le champ ReferencePM est renseigné
     *
     * La valeur de ce champ ainsi que les autres champs d'adresse du PM est
     * relative au PM ou au PMR dans le cas d'un regroupement de PM Techniques
     * pour desservir l'adresse"
     *
     */
    @NotNull
    @NotEmpty
    @Length(max = 4)
    @Column(name = "PM_CODE_VOIE_RIVOLI")
    private String codeVoieRivoliPM;

    /**
     * Code insee de l'adresse du PM. Ce champ est conditionné à la présence de
     * la référence PM c'est-à-dire obligatoire si le champ ReferencePM est
     * renseigné
     *
     */
    @NotNull
    @NotEmpty
    @Length(max = 5)
    @Column(name = "PM_CODE_INSEE")
    private String codeInseePM;

    /**
     * Code postal de l'adresse du PM. Ce champ est conditionné à la présence de
     * la référence PM c'est-à-dire obligatoire si le champ ReferencePM est
     * renseigné
     *
     */
    @NotNull
    @NotEmpty
    @Length(max = 5)
    @Column(name = "PM_CODE_POSTAL")
    private String codePostalPM;

    /**
     * Nom de la commune de l'adresse du PM. Ce champ est conditionné à la
     * présence de la référence PM c'est-à-dire obligatoire si le champ
     * ReferencePM est renseigné
     *
     */
    @NotNull
    @NotEmpty
    @Column(name = "PM_COMMUNE")
    private String communePM;

    /**
     * Code hexaclé numéro de l'adresse du PM tel que décrit dans le référentiel
     * SNA. Ce champ est facultatif
     *
     */
    @Column(name = "PM_CODE_ADDRESSE")
    private String codeAdressePM;

    /**
     * Type de voie de l'adresse du PM. Ce champ est facultatif
     */
    @Column(name = "PM_TYPE_VOIE")
    private String typeVoiePM;

    /**
     * Nom de la voie de l'adresse du PM. Ce champ est conditionné à la présence
     * de la référence PM c'est-à-dire obligatoire si le champ ReferencePM est
     * renseigné
     *
     */
    @NotNull
    @NotEmpty
    @Column(name = "PM_NOM_VOIE")
    private String nomVoiePM;

    /**
     * "Numéro de la voie de l'adresse du PM. Ce champ est conditionné à la
     * présence de la référence PM c'est-à-dire obligatoire si le champ
     * ReferencePM est renseigné.
     *
     * Ce champ doit être rempli avec 0 quand aucun numéro n'a été attribué dans
     * cette rue. Le 0 ne constitue pas une valeur par défaut, si le numero est
     * inconnu de l'opérateur d'immeuble, le champ restera vide"
     *
     */
    @NotNull
    @NotEmpty
    @Length(max = 5)
    @Column(name = "PM_NUMERO_VOIE")
    private String numeroVoiePM;

    /**
     * "Complément d'adresses. Ce champ est composé d'une seule lettre (exemple
     * B= pour BIS, T pour TER, etc.) Ce champ est facultatif et ne peut
     * apparaitre que si la référencePM a été renseignée"
     *
     */
    @Column(name = "PM_COMPLEMENT_VOIE")
    private String complementVoiePM;

    /**
     * Nom du batiment de l'adresse du PM tel que décrit par l'opérateur
     * d'immeuble. Ce champ est facultatif
     *
     */
    @Column(name = "PM_BATIMENT")
    private String batimentPM;

    /**
     * "Champ décrivant le type d'ingénierie (mono, bi, quadri) tel que décrit
     * dans le contrat de l'OI. Cette valeur fait référence aux STAS de
     * l'opérateur d'immeuble. L'information contenue dans ce champ est utilisée
     * pour la facturation et renvoie aux listes autorisées dans le contrat.
     *
     * Ce champ est conditionné à la présence de la référence PM c'est-à-dire
     * obligatoire si le champ ReferencePM est renseigné.
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @NotNull
    @NotEmpty
    @Column(name = "PM_TYPE_INGENIERIE")
    private String typeIngenierie;

    /**
     * "Ce champ permet à l'opérateur d'immeuble d'indiquer la présence ou non
     * d'une fibre dédiée libre à l'adresse par une valeur O/N correspondant à
     * Oui / Non.
     *
     * Ce champ est facultatif et conditionné à la présence de la ref_PM
     * c'est-à-dire qu'il ne peut être renseigné que si le champ ReferencePM est
     * renseigné.
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @Column(name = "PM_FIBRE_DEDIEE_LIBRE")
    private String fibreDedieeLibre;

    /**
     * "Ce champ correspond au nombre total de logements dans la zone arrière du
     * PM Technique (c'est à dire nombre de logements total : ciblé, signé,
     * déployé). Dans le cadre d'un PM Intérieur il correspond à l'ensemble des
     * logements raccordables. Dans le cadre d'un PM Extérieur, il correspond à
     * l'ensemble des logements dans la zone arrière du PM, quel que soit leur
     * statut * En cas de regroupements de PMT pour une référence de PM, le
     * nombre de logements dans le CR MAD est celui du PM Technique
     * (contrairement à celui affiché dans l'IPE qui concerne le nombre de
     * logements au PM de Regroupement) * Le nombre de logements PM doit par
     * ailleurs correspondre à la somme des NombreLogementsAdresseIPE des
     * adresses dans la zone arrière du PM
     *
     * Ce champ est conditionné à l'état OK du champ EtatCrCommandePM c'est à
     * dire obligatoire si le CR est OK, facultatif si le CR est KO"
     *
     */
    @Length(max = 5)
    @Column(name = "PM_NB_LOGEMENT")
    private String nombreLogementsPM;

    /**
     * "Nombre de colonnes montantes associées au PM dans les cas de PM
     * Intérieur. Il est facultatif et renseigné par certains l'opérateur
     * d'immeuble à des fins de facturation * Ce champ est relatif au
     * PMTechnique dans le cas d'un regroupement de plusieurs PM Techniques
     * rattachés à un PM de Regroupement"
     *
     */
    @Column(name = "PM_NB_COLONNE_MONTANTE")
    private String nombreColonnesMontantesPM;

    /**
     * "Ce champ correspond à la date à laquelle le raccordement effectif d'un
     * client final à ce PM est possible du point de vue de la réglementation.
     * Cette date équivaut à la date à laquelle le PM est passé déployé avec une
     * première mise à disposition faite aux opérateurs commerciaux + 3 mois.
     *
     * Ce champ est conditionné, c'est à dire obligatoire quand le PM est
     * déployé * La valeur de ce champ est relative au PM ou au PMR dans le cas
     * d'un regroupement de PM Techniques pour desservir l'adresse."
     *
     */
    @Length(max = 8)
    @Column(name = "PM_DATE_MES_COMMERCIALE")
    private String dateMiseEnServiceCommercialPM;

    /**
     * "Ce champ est utilisé par certains opérateurs d'immeuble pour gérer
     * l'appartenance d'un PM à un parc d'une consultation en ZTD (correspond au
     * millesime du PM). Ce champ est facultatif.
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse."
     *
     */
    @Column(name = "PM_REF_CONSULT_NATIVE")
    private String referenceConsultationNative;

    /**
     * "Nombre de PM Techniques associés à une référence PM. Ce champ permet de
     * préciser la présence de plusieurs PM techniques agrégés sous une même
     * référence de PM, ce cas étant propre à certains opérateurs pour des
     * raisons de gestion SI
     *
     * Ce champ est facultatif"
     *
     */
    @Column(name = "PM_NB_PM_TECHNIQUE")
    private String nombrePMTechniques;

    /**
     * "Ce champ permet à l'opérateur d'immeuble de préciser les
     * caractéristiques de l'adresse (pavillon / immeuble). La définition d'un
     * pavillon versus un immeuble n'est pas normalisée, mais l'usage du champ
     * peut permettre de donner une indication du besoin de réaliser une colonne
     * montante par exemple
     *
     * Le champ est conditionné si EtatImmeuble est déployé, ce qui revient à le
     * rendre obligatoire comme c'est le cas dans l'IPE"
     *
     */
    @Column(name = "PM_TYPE_IMMEUBLE")
    private String typeImmeuble;

    /**
     * "Ce champ permet de renseigner le type de projection géographique
     * utilisé. Par exemple : WGS84: SR:102100, code EPSG :3857 LAMB2E:
     * SR:102582 RGF93: EPSG:2154
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @Column(name = "PM_TYPE_PROJECTION_GEO")
    private String typeProjectionGeographique;

    /**
     * "Coordonnées X de l'adresse du PM La valeur de ce champ est relative au
     * PM ou au PMR dans le cas d'un regroupement de PM Techniques pour
     * desservir l'adresse."
     *
     */
    @Column(name = "PM_COORD_X")
    private String coordonneePMX;

    /**
     * "Coordonnées Y de l'adresse du PM La valeur de ce champ est relative au
     * PM ou au PMR dans le cas d'un regroupement de PM Techniques pour
     * desservir l'adresse."
     *
     */
    @Column(name = "PM_COORD_Y")
    private String coordonneePMY;

    /**
     * "Ce champ doit indiquer s'il y a de l'electricité au PM pour permettre à
     * un opérateur commercial d'y disposer des équipements actifs. Ce champ
     * répond à une demande de la réglementation de pouvoir proposer de l'actif
     * au PM. Les valeurs de ce champ sont O pour Oui, et N pour Non
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse."
     *
     */
    @NotNull
    @NotEmpty
    @Column(name = "PM_EMPL_ACTIF_DISPO")
    private String emplacementActifDisponible;

    /**
     * "Ce champ permet de préciser si l'adresse du PM est de qualité précise ou
     * approximative. Ceci afin de permettre à l'opérateur commercial de prendre
     * toutes les dispositions nécessaires en cas d'adresse approximative
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @NotNull
    @NotEmpty
    @Column(name = "PM_QUALITE_ADRESSE")
    private String qualiteAdressePM;

    /**
     * "Ce champ permet de renseigner la date de Première Mise à Disposition du
     * PM à un opérateur commercial. Une fois cette première mise à disposition
     * passée, cette date n'évolue pas. En cas d'absence d'opérateur commercial
     * lors de l'installation du PM, cette date est valorisée avec la date
     * d'installation du PM (contenu du champ DateInstallationPM). Cette date
     * fait démarrer le délai réglementaire de 3 mois avant mise en service
     * commerciale du PM
     *
     * Ce champ est conditionné à l'état PM, c'est à dire que son remplissage
     * est obligatoire dès lors que le PM est déployé
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @NotNull
    @NotEmpty
    @Length(max = 8)
    @Column(name = "PM_DATE_PREMIERE_MAD")
    private String datePremiereMADPM;

    /**
     * "Ce champ permet de savoir si un accord du gestionnaire d'immeuble
     * (copropriété, syndic, etc.) est nécessaire ou non pour aller raccorder
     * l'adresse
     *
     * Ce champ est obligatoire et conditionne les champs relatifs aux
     * conventions syndic : - Le gestionnaire est relatif à l'adresse et non au
     * PM, il pourra donc appraitre un gestionnaire par ligne ou des
     * gestionnaires différents pour un même PM - Dans le cas d'adresses ne
     * nécessitant pas d'accord syndic, ce champ permet dans l'IPE d'anticiper
     * que ces adresses pourront passer de l'état ciblé à l'état en cours de
     * déploiement, sans passer par l'état signé"
     *
     */
    @NotNull
    @NotEmpty
    @Column(name = "PM_ACC_GEST_IMM_NECESSAIRE")
    private String accordGestionnaireImmeubleNecessaire;

    /**
     * "Ce champ permet de renseigner le type de zone de l'adresse desservie (et
     * non du PM) Les valeurs proposées pour ce champ sont : 1 = ZTD Haute
     * Densité 2 = ZTD Basse Densité 3 = ZMD Ces valeurs sont à lier au
     * référencement de l'Arcep"
     *
     */
    @NotNull
    @NotEmpty
    @Column(name = "PM_TYPE_ZONE")
    private String typeZone;

    /**
     * "La bonne pratique définie par le groupe est de remplir systématiquement
     * ce champ avec la Marque et le Modèle du matériel utilisé par l'opérateur
     * d'immeuble. Cette information donne une indication à l'opérateur
     * commercial qui peut dans certains cas poser des équipements équivalents
     *
     * Ce champ est relatif au PMTechnique dans le cas d'un regroupement de
     * plusieurs PM Techniques rattachés à un PM de Regroupement"
     *
     */
    @Column(name = "PM_TYPE_MATERIEL")
    private String typeMaterielPM;

    /**
     * "Ce champ permet de renseigner le code d'accès de l'entrée de l'immeuble
     * hébergeant le PM. Ce champ n'est pas destiné à renseigner le code d'accès
     * des adresses en zone arrière des PM extérieurs. Il ne sera donc pas
     * renseigné par exemple dans le cas d'adresses n'hébergeant pas un PM
     *
     * Ce champ est relatif au PMTechnique dans le cas d'un regroupement de
     * plusieurs PM Techniques rattachés à un PM de Regroupement"
     *
     */
    @Column(name = "PM_CODE_ACCESS_IMMEUBLE")
    private String codeAccesImmeuble;

    /**
     * "Ce champ permet de renseigner les informations de contact des gardiens
     * d'immeubles hébergeant un PM
     *
     * Ce champ est relatif au PMTechnique dans le cas d'un regroupement de
     * plusieurs PM Techniques rattachés à un PM de Regroupement"
     *
     */
    @Column(name = "PM_CONTACT_IMMEUBLE")
    private String contactsImmeuble;

    /**
     * "Ce champ permet de renseigner la Référence du PM Technique dans le cas
     * d'un regroupement de plusieurs PM Techniques rattachés à un PM de
     * Regroupement. * Cette référence peut selon les opérateurs être unique au
     * niveau national ou n'être unique que si elle est couplée à la référence
     * du PM de regroupement auquel elle est rattachée
     *
     * Elle peut par ailleurs selon les opérateurs n'être renseignée qu'en cas
     * de multiples PM Techniques rattachés à un PMR ou systématiquement
     * renseignée"
     *
     */
    @Column(name = "PM_REF_PM_TECHNIQUE")
    private String referencePMTechnique;

    /**
     * "Ce champ permet à l'opérateur d'immeuble de fournir des informations sur
     * l'accessibilité du PM
     *
     * Ce champ est relatif au PMTechnique dans le cas d'un regroupement de
     * plusieurs PM Techniques rattachés à un PM de Regroupement"
     *
     */
    @Column(name = "PM_ACCESSIBILITE")
    private String pmAccessible;

    /**
     * "Ce champ permet à l'opérateur d'immeuble de fournir des informations sur
     * les modalités d'obtention des clés du local technique
     *
     * Ce champ est relatif au PMTechnique dans le cas d'un regroupement de
     * plusieurs PM Techniques rattachés à un PM de Regroupement"
     *
     */
    @Column(name = "PM_INFO_OBTENTION_CLE")
    private String infoObtentionCle;

    /**
     * "Ce champ permet à l'opérateur d'immeuble de fournir des informations sur
     * les codes d'accès au sous-sol quand nécessaire
     *
     * Ce champ est relatif au PMTechnique dans le cas d'un regroupement de
     * plusieurs PM Techniques rattachés à un PM de Regroupement"
     *
     */
    @Column(name = "PM_CODE_ACCESS_SS")
    private String codeAccesSousSol;

    /**
     * "Ce champ permet à l'opérateur d'immeuble de fournir des informations sur
     * le code d'accès au local technique hébergeant le PM quand nécessaire
     *
     * Ce champ est relatif au PMTechnique dans le cas d'un regroupement de
     * plusieurs PM Techniques rattachés à un PM de Regroupement"
     *
     */
    @Column(name = "PM_CODE_LOCAL")
    private String codeLocalPM;

    /**
     * "Ce champ permet à l'opérateur d'immeuble de fournir toute autre
     * information utile pour l'accès au PM. Ce champ est conditionné, c'est à
     * dire obligatoire si tous les autres champs d'information d'accès au PM
     * sont vides.
     *
     * Ce champ est relatif au PMTechnique dans le cas d'un regroupement de
     * plusieurs PM Techniques rattachés à un PM de Regroupement"
     *
     */
    @Column(name = "PM_AUTRE_INFO")
    private String autresInformations;

    /**
     * "Ce champ permet à l'opérateur d'immeuble de fournir les coordonnées du
     * gestionnaire de l'immeuble abritant un PM Intérieur
     *
     * Ce champ est relatif au PMTechnique dans le cas d'un regroupement de
     * plusieurs PM Techniques rattachés à un PM de Regroupement"
     *
     */
    @Column(name = "PM_CONTACT_SYNDIC")
    private String contactsSyndic;

    /**
     * "Ce champ reprend la référence de commande de l'OC pour les commandes
     * unitaires ou permet d'indiquer le cas échéant une référence d'engagement
     * pour les cas de cofinancement.
     *
     * Ce champ est facultatif et peut être sans objet dans le cas d'un CR MAD à
     * un opérateur commercial cofinanceur
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse."
     *
     */
    @Column(name = "PM_REF_COMMANDE_INTERNE_OC")
    private String referenceCommandePMInterneOC;

    /**
     * "Ce champ permet à l'opérateur d'immeuble de préciser la façon dont la
     * commande est valorisée : en location unitaire ou en co-financement.
     *
     * UNIT signifie qu'il s'agit d'une commande de PM unitaire COFI signifie
     * qu'il s'agit d'une demande d'information sur un PM cofinancé par l'Usager
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @NotNull
    @NotEmpty
    @Column(name = "PM_TYPE_COMMANDE")
    private String typeCommandePM;

    /**
     * "Ce champ permet à l'opérateur d'immeuble de préciser si l'accès au PM
     * est donné sur fibre dédiée ou fibre partageable selon le choix technique
     * formulé par l'opérateur commercial et autorisé par l'opérateur d'immeuble
     * D signifie fibre dédiée P signifie fibre partageable
     *
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse
     *
     * Ce champ est conditionné à l'etat du CR, c'est à dire obligatoire pour un
     * CR OK"
     *
     */
    @Column(name = "PM_CHOIX_TECHNIQUE_OC")
    private String choixTechniqueOC;

    /**
     * "Ce champ est utilisé par les opérateurs d'immeubles pour fournir une
     * référence de prestation commerciale correspondant à la livraison du PM.
     * Cette référence fournie par l'opérateur d'immeuble à l'opérateur
     * commercial est obligatoire et doit être pérenne dans le temps car elle
     * constitue chez certains opérateurs d'immeuble un pré-requis à la commande
     * d'accès
     *
     * Cette référence peut également être utilisée dans le cadre de dépôt de
     * signalisation par l'OC suite à son adduction
     *
     * A noter, ce champ permet de fournir une référence relative à la livraison
     * des PM, à la différence du champ ReferencePrestationPB dont l'objet est
     * relatif aux adresses en zone arrière de PM
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @NotNull
    @NotEmpty
    @Column(name = "PM_REF_PRESTATION")
    private String referencePrestationPM;

    /**
     * "Ce champ correspond à la somme des logements raccordables (déployés) sur
     * le PM à l'instant de la diffusion du flux, contrairement au champ
     * NombreLogementsPM qui indique le nombre de logements total de la zone
     * arrière d'un PM, quel que soit leur état (ciblé, signé, etc.).
     *
     * Ce nombre de logements évolue au fur et à mesure des Mises à disposition
     * de nouvelles adresses. Le champ sera donc mis à jour avec l'ensemble des
     * adresses raccordables derrière le PM à chaque nouvelle diffusion de CR
     * MAD. * La valeur de ce champ est relative au PM ou au PMR dans le cas
     * d'un regroupement de PM Techniques pour desservir l'adresse.
     *
     * Ce champ est conditionné à l'état OK du champ EtatCrCommandePM c'est à
     * dire obligatoire si le CR est OK, facultatif si le CR est KO"
     *
     */
    @Column(name = "PM_NB_LOGEMENT_MAD")
    private String nombreLogementsMadPM;

    /**
     * "Ce champ permet aux opérateurs d'immeuble de communiquer le nombre
     * d'opérateurs ayant commandé une fibre dédiée sur l'adresse concernée afin
     * de pouvoir justifier du tarif appliqué aux opérateurs commerciaux en
     * conséquence
     *
     * Les opérateurs en location unitaire ne sont pas comptés
     *
     * L'opérateur d'immeuble doit se compter également dans ce nombre s'il est
     * OC (en adéquation avec la grille affichée dans son annexe tarifaire) "
     *
     */
    @Column(name = "PM_NB_OPE_FIBRE_DEDIEE")
    private String nombreOperateursFibreDediee;

    /**
     * "Ce champ permet aux opérateurs d'immeuble de communiquer le nombre
     * d'opérateurs ayant commandé une fibre partageable sur l'adresse concernée
     * afin de pouvoir justifier du tarif appliqué aux opérateurs commerciaux en
     * conséquence
     *
     * Les opérateurs en location unitaire ne sont pas comptés
     *
     * L'opérateur d'immeuble doit se compter ou non selon la grille affichée
     * dans son contrat "
     *
     */
    @Column(name = "PM_NB_OPE_FIBRE_PARTAGEABLE")
    private String nombreOperateursFibrePartageable;

    /**
     * "Ce champ permet à l'opérateur d'immeuble de renseigner la date
     * d'émission du CR de Mise à dispotion du PM. Cette date correspond à la
     * date de génération du CR, elle peut donc évoluer à chaque envoi de CR MAD
     * d'un PM, même si la mise à disposition effective du PM n'a pas évolué. *
     * Contrairement à cette date de génération du CR MAD, la date de Mise à
     * disposition effective du PM est indiquée dans le champ
     * DateMADPrestationPM
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @NotNull
    @NotEmpty
    @Column(name = "PM_DATE_CR_CMD")
    private String dateCrCommandePM;

    /**
     * "Ce champ permet à l'OI de générer un Compte-rendu de la commande de PM.
     * A ce stade, seul l'usage du CR OK a été normalisé et est attendu
     *
     * Il est convenu qu'un CR KO ne pourra arriver qu'après un premier CR OK,
     * sur des cas à préciser (exemple destruction d'immeuble, vandalisme à
     * répétition...). Le PM devra dans ce cas apparaitre à l'état abandonné
     * dans l'IPE et les impacts d'un CR KO (arrêt de la facturation, impact sur
     * les commandes d'accès, etc.) seront à travailler
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse"
     *
     */
    @NotNull
    @Column(name = "PM_ETAT_CR_CMD")
    private String etatCrCommandePM;

    /**
     * Ce champ permet d'intégrer le motif d'un CR KO. L'usage des CR KO n'ayant
     * pas été normalisé à ce stade, ce champ ne sera pas utilisé à court terme
     *
     */
    @Column(name = "PM_MOTIF_KO_CR_CMD")
    private String motifKoCrCommandePM;

    /**
     * "Ce champ permet aux opérateurs d'immeuble de fournir la date de la mise
     * à disposition du PM à l'opérateur commercial demandeur. Cette date est
     * fixe et ne doit pas évoluer, contrairement au champ DateCRCommandePM qui
     * correspond à la date d'envoi du CR et peut donc être mise à jour à chaque
     * nouvel envoi de CR pour une seule et même mise à disposition.
     *
     * La valeur de ce champ est relative au PM ou au PMR dans le cas d'un
     * regroupement de PM Techniques pour desservir l'adresse
     *
     * Ce champ est conditionné à l'etat du CR, c'est à dire obligatoire pour un
     * CR OK"
     *
     */
    @Length(max = 8)
    @Column(name = "PM_DATE_MAD_PRESTATION")
    private String dateMADprestationPM;

    /**
     * Ce champ est facultatif et permet à l'opérateur d'immeuble de communiquer
     * sa référence propre de contrat avec l'opérateur commercial
     *
     */
    @Column(name = "PM_REF_CONTRAT")
    private String referenceContrat;

    /**
     * Ce champ est un champ de secours utilisé par certains opérateurs pour
     * véhiculer des paramètres utiles à la facturation. A ce stade le contenu
     * de ce champ n'est pas normalisé
     *
     */
    @Column(name = "PM_REF_OFR_COMMERCIALE")
    private String referenceOffreCommerciale;

    /**
     * "Ce champ permet à l'opérateur d'immeuble de préciser à l'opérateur
     * commercial si le compte-rendu de Mise à disposition envoyé est un
     * compte-rendu Initial ou Mis à Jour.
     *
     * Un compte-rendu initial de PM est un premier compte-rendu de mise à
     * disposition d'un PM, pouvant contenir ou non des adresses en zone arrière
     * associées.
     *
     * Un compte-rendu Mis à Jour de PM est renvoyé : 1/ Pour les cas de
     * livraison de nouvelles adresses sur un PM déjà mis à disposition 2/ Pour
     * les cas de correction ou d'évolutions sur un compte-rendu de mise à
     * disposition de PM déjà effectué, notamment dans le cas de modifications
     * apportées à des paramètres facturants comme le nombre de logements. Le CR
     * MAD Mis à Jour a alors un usage de CR MAD ""correctif"""
     *
     */
    @Column(name = "PM_NATURE_CR")
    private String natureCR;

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferencePM() {
        return referencePM;
    }

    public void setReferencePM(String referencePM) {
        this.referencePM = referencePM;
    }

    public String getEtatPM() {
        return etatPM;
    }

    public void setEtatPM(String etatPM) {
        this.etatPM = etatPM;
    }

    public String getDateInstallationPM() {
        return dateInstallationPM;
    }

    public void setDateInstallationPM(String dateInstallationPM) {
        this.dateInstallationPM = dateInstallationPM;
    }

    public String getTypeEmplacementPM() {
        return typeEmplacementPM;
    }

    public void setTypeEmplacementPM(String typeEmplacementPM) {
        this.typeEmplacementPM = typeEmplacementPM;
    }

    public String getCommentairePM() {
        return commentairePM;
    }

    public void setCommentairePM(String commentairePM) {
        this.commentairePM = commentairePM;
    }

    public String getCapaciteMaxPM() {
        return capaciteMaxPM;
    }

    public void setCapaciteMaxPM(String capaciteMaxPM) {
        this.capaciteMaxPM = capaciteMaxPM;
    }

    public String getCodeVoieRivoliPM() {
        return codeVoieRivoliPM;
    }

    public void setCodeVoieRivoliPM(String codeVoieRivoliPM) {
        this.codeVoieRivoliPM = codeVoieRivoliPM;
    }

    public String getCodeInseePM() {
        return codeInseePM;
    }

    public void setCodeInseePM(String codeInseePM) {
        this.codeInseePM = codeInseePM;
    }

    public String getCodePostalPM() {
        return codePostalPM;
    }

    public void setCodePostalPM(String codePostalPM) {
        this.codePostalPM = codePostalPM;
    }

    public String getCommunePM() {
        return communePM;
    }

    public void setCommunePM(String communePM) {
        this.communePM = communePM;
    }

    public String getCodeAdressePM() {
        return codeAdressePM;
    }

    public void setCodeAdressePM(String codeAdressePM) {
        this.codeAdressePM = codeAdressePM;
    }

    public String getTypeVoiePM() {
        return typeVoiePM;
    }

    public void setTypeVoiePM(String typeVoiePM) {
        this.typeVoiePM = typeVoiePM;
    }

    public String getNomVoiePM() {
        return nomVoiePM;
    }

    public void setNomVoiePM(String nomVoiePM) {
        this.nomVoiePM = nomVoiePM;
    }

    public String getNumeroVoiePM() {
        return numeroVoiePM;
    }

    public void setNumeroVoiePM(String numeroVoiePM) {
        this.numeroVoiePM = numeroVoiePM;
    }

    public String getComplementVoiePM() {
        return complementVoiePM;
    }

    public void setComplementVoiePM(String complementVoiePM) {
        this.complementVoiePM = complementVoiePM;
    }

    public String getBatimentPM() {
        return batimentPM;
    }

    public void setBatimentPM(String batimentPM) {
        this.batimentPM = batimentPM;
    }

    public String getTypeIngenierie() {
        return typeIngenierie;
    }

    public void setTypeIngenierie(String typeIngenierie) {
        this.typeIngenierie = typeIngenierie;
    }

    public String getFibreDedieeLibre() {
        return fibreDedieeLibre;
    }

    public void setFibreDedieeLibre(String fibreDedieeLibre) {
        this.fibreDedieeLibre = fibreDedieeLibre;
    }

    public String getNombreLogementsPM() {
        return nombreLogementsPM;
    }

    public void setNombreLogementsPM(String nombreLogementsPM) {
        this.nombreLogementsPM = nombreLogementsPM;
    }

    public String getNombreColonnesMontantesPM() {
        return nombreColonnesMontantesPM;
    }

    public void setNombreColonnesMontantesPM(String nombreColonnesMontantesPM) {
        this.nombreColonnesMontantesPM = nombreColonnesMontantesPM;
    }

    public String getDateMiseEnServiceCommercialPM() {
        return dateMiseEnServiceCommercialPM;
    }

    public void setDateMiseEnServiceCommercialPM(String dateMiseEnServiceCommercialPM) {
        this.dateMiseEnServiceCommercialPM = dateMiseEnServiceCommercialPM;
    }

    public String getReferenceConsultationNative() {
        return referenceConsultationNative;
    }

    public void setReferenceConsultationNative(String referenceConsultationNative) {
        this.referenceConsultationNative = referenceConsultationNative;
    }

    public String getNombrePMTechniques() {
        return nombrePMTechniques;
    }

    public void setNombrePMTechniques(String nombrePMTechniques) {
        this.nombrePMTechniques = nombrePMTechniques;
    }

    public String getTypeImmeuble() {
        return typeImmeuble;
    }

    public void setTypeImmeuble(String typeImmeuble) {
        this.typeImmeuble = typeImmeuble;
    }

    public String getTypeProjectionGeographique() {
        return typeProjectionGeographique;
    }

    public void setTypeProjectionGeographique(String typeProjectionGeographique) {
        this.typeProjectionGeographique = typeProjectionGeographique;
    }

    public String getEmplacementActifDisponible() {
        return emplacementActifDisponible;
    }

    public void setEmplacementActifDisponible(String emplacementActifDisponible) {
        this.emplacementActifDisponible = emplacementActifDisponible;
    }

    public String getQualiteAdressePM() {
        return qualiteAdressePM;
    }

    public void setQualiteAdressePM(String qualiteAdressePM) {
        this.qualiteAdressePM = qualiteAdressePM;
    }

    public String getDatePremiereMADPM() {
        return datePremiereMADPM;
    }

    public void setDatePremiereMADPM(String datePremiereMADPM) {
        this.datePremiereMADPM = datePremiereMADPM;
    }

    public String getAccordGestionnaireImmeubleNecessaire() {
        return accordGestionnaireImmeubleNecessaire;
    }

    public void setAccordGestionnaireImmeubleNecessaire(String accordGestionnaireImmeubleNecessaire) {
        this.accordGestionnaireImmeubleNecessaire = accordGestionnaireImmeubleNecessaire;
    }

    public String getTypeZone() {
        return typeZone;
    }

    public void setTypeZone(String typeZone) {
        this.typeZone = typeZone;
    }

    public String getTypeMaterielPM() {
        return typeMaterielPM;
    }

    public void setTypeMaterielPM(String typeMaterielPM) {
        this.typeMaterielPM = typeMaterielPM;
    }

    public String getCodeAccesImmeuble() {
        return codeAccesImmeuble;
    }

    public void setCodeAccesImmeuble(String codeAccesImmeuble) {
        this.codeAccesImmeuble = codeAccesImmeuble;
    }

    public String getContactsImmeuble() {
        return contactsImmeuble;
    }

    public void setContactsImmeuble(String contactsImmeuble) {
        this.contactsImmeuble = contactsImmeuble;
    }

    public String getReferencePMTechnique() {
        return referencePMTechnique;
    }

    public void setReferencePMTechnique(String referencePMTechnique) {
        this.referencePMTechnique = referencePMTechnique;
    }

    public String getPmAccessible() {
        return pmAccessible;
    }

    public void setPmAccessible(String pmAccessible) {
        this.pmAccessible = pmAccessible;
    }

    public String getInfoObtentionCle() {
        return infoObtentionCle;
    }

    public void setInfoObtentionCle(String infoObtentionCle) {
        this.infoObtentionCle = infoObtentionCle;
    }

    public String getCodeAccesSousSol() {
        return codeAccesSousSol;
    }

    public void setCodeAccesSousSol(String codeAccesSousSol) {
        this.codeAccesSousSol = codeAccesSousSol;
    }

    public String getCodeLocalPM() {
        return codeLocalPM;
    }

    public void setCodeLocalPM(String codeLocalPM) {
        this.codeLocalPM = codeLocalPM;
    }

    public String getAutresInformations() {
        return autresInformations;
    }

    public void setAutresInformations(String autresInformations) {
        this.autresInformations = autresInformations;
    }

    public String getContactsSyndic() {
        return contactsSyndic;
    }

    public void setContactsSyndic(String contactsSyndic) {
        this.contactsSyndic = contactsSyndic;
    }

    public String getReferenceCommandePMInterneOC() {
        return referenceCommandePMInterneOC;
    }

    public void setReferenceCommandePMInterneOC(String referenceCommandePMInterneOC) {
        this.referenceCommandePMInterneOC = referenceCommandePMInterneOC;
    }

    public String getTypeCommandePM() {
        return typeCommandePM;
    }

    public void setTypeCommandePM(String typeCommandePM) {
        this.typeCommandePM = typeCommandePM;
    }

    public String getChoixTechniqueOC() {
        return choixTechniqueOC;
    }

    public void setChoixTechniqueOC(String choixTechniqueOC) {
        this.choixTechniqueOC = choixTechniqueOC;
    }

    public String getReferencePrestationPM() {
        return referencePrestationPM;
    }

    public void setReferencePrestationPM(String referencePrestationPM) {
        this.referencePrestationPM = referencePrestationPM;
    }

    public String getNombreLogementsMadPM() {
        return nombreLogementsMadPM;
    }

    public void setNombreLogementsMadPM(String nombreLogementsMadPM) {
        this.nombreLogementsMadPM = nombreLogementsMadPM;
    }

    public String getNombreOperateursFibreDediee() {
        return nombreOperateursFibreDediee;
    }

    public void setNombreOperateursFibreDediee(String nombreOperateursFibreDediee) {
        this.nombreOperateursFibreDediee = nombreOperateursFibreDediee;
    }

    public String getNombreOperateursFibrePartageable() {
        return nombreOperateursFibrePartageable;
    }

    public void setNombreOperateursFibrePartageable(String nombreOperateursFibrePartageable) {
        this.nombreOperateursFibrePartageable = nombreOperateursFibrePartageable;
    }

    public String getDateCrCommandePM() {
        return dateCrCommandePM;
    }

    public void setDateCrCommandePM(String dateCrCommandePM) {
        this.dateCrCommandePM = dateCrCommandePM;
    }

    public String getEtatCrCommandePM() {
        return etatCrCommandePM;
    }

    public void setEtatCrCommandePM(String etatCrCommandePM) {
        this.etatCrCommandePM = etatCrCommandePM;
    }

    public String getMotifKoCrCommandePM() {
        return motifKoCrCommandePM;
    }

    public void setMotifKoCrCommandePM(String motifKoCrCommandePM) {
        this.motifKoCrCommandePM = motifKoCrCommandePM;
    }

    public String getDateMADprestationPM() {
        return dateMADprestationPM;
    }

    public void setDateMADprestationPM(String dateMADprestationPM) {
        this.dateMADprestationPM = dateMADprestationPM;
    }

    public String getReferenceContrat() {
        return referenceContrat;
    }

    public void setReferenceContrat(String referenceContrat) {
        this.referenceContrat = referenceContrat;
    }

    public String getReferenceOffreCommerciale() {
        return referenceOffreCommerciale;
    }

    public void setReferenceOffreCommerciale(String referenceOffreCommerciale) {
        this.referenceOffreCommerciale = referenceOffreCommerciale;
    }

    public String getNatureCR() {
        return natureCR;
    }

    public void setNatureCR(String natureCR) {
        this.natureCR = natureCR;
    }

//    public List<Immeuble> getImmeubles() {
//        return immeubles;
//    }
//
//    public void setImmeubles(List<Immeuble> immeubles) {
//        this.immeubles = immeubles;
//    }
    public Adduction getAdduction() {
        return adduction;
    }

    public void setAdduction(Adduction adduction) {
        this.adduction = adduction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public byte[] getFichierPlan() {
        return fichierPlan;
    }

    public void setFichierPlan(byte[] fichierPlan) {
        this.fichierPlan = fichierPlan;
    }

    public Collection<PBO> getPbos() {
        return pbos;
    }

    public void setPbos(Collection<PBO> pbos) {
        this.pbos = pbos;
    }

    public String getCoordonneePMX() {
        return coordonneePMX;
    }

    public void setCoordonneePMX(String coordonneePMX) {
        this.coordonneePMX = coordonneePMX;
    }

    public String getCoordonneePMY() {
        return coordonneePMY;
    }

    public void setCoordonneePMY(String coordonneePMY) {
        this.coordonneePMY = coordonneePMY;
    }

    public Collection<Fichier> getHistoriqueFichiers() {
        return historiqueFichiers;
    }

    public void setHistoriqueFichiers(Collection<Fichier> historiqueFichiers) {
        this.historiqueFichiers = historiqueFichiers;
    }

    public ZoneCofinancement getZoneCofinancement() {
        return zoneCofinancement;
    }

    public void setZoneCofinancement(ZoneCofinancement zoneCofinancement) {
        this.zoneCofinancement = zoneCofinancement;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.referencePM != null ? this.referencePM.hashCode() : 0);
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
        final PM other = (PM) obj;
        if ((this.referencePM == null) ? (other.referencePM != null) : !this.referencePM.equals(other.referencePM)) {
            return false;
        }
        return true;
    }

}
