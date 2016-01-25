package org.mediaserv.fibre.protocole.domain.gestionfichier;

import java.io.Serializable;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Thomas Lecocq
 */
@Entity
@Table(name = "FICHIER")
public class Fichier implements Serializable {

    public Fichier() {
        // Constructeur par défaut.
    }

    public Fichier(
            Path path,
            CodeOperateur codeOI,
            CodeOperateur codeOC,
            StatusFichier statusFichier,
            StatusTraitement statusTraitement,
            TypeProtocole typeProtocole,
            VersionProtocole versionProtocole,
            TypeOperation typeOperation,
            Date dateCreation,
            byte[] fichierCSV,
            TypeExtension typeExtension) {

        this.nomFichier = path.getFileName().toString();

        this.codeOI = codeOI;
        this.codeOC = codeOC;
        this.statusFichier = statusFichier;
        this.typeProtocole = typeProtocole;
        this.versionProtocole = versionProtocole;
        this.typeOperation = typeOperation;
        this.dateCreation = dateCreation;
        this.document = fichierCSV;
        this.typeExtension = typeExtension;
        this.statusTraitement = statusTraitement;
    }

    public Fichier(
            String reference,
            CodeOperateur codeOI,
            CodeOperateur codeOC,
            StatusFichier statusFichier,
            StatusTraitement statusTraitement,
            TypeProtocole typeProtocole,
            VersionProtocole versionProtocole,
            TypeOperation typeOperation,
            Date dateCreation,
            byte[] fichierCSV,
            TypeExtension typeExtension) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        this.codeOI = codeOI;
        this.codeOC = codeOC;
        this.statusFichier = statusFichier;
        this.typeProtocole = typeProtocole;
        this.versionProtocole = versionProtocole;
        this.typeOperation = typeOperation;
        this.dateCreation = dateCreation;
        this.document = fichierCSV;
        this.typeExtension = typeExtension;
        this.statusTraitement = statusTraitement;

        this.nomFichier
                = typeOperation.getOperation() + "_"
                + reference + "_"
                + codeOI.getCode() + "_"
                + codeOC.getCode() + "_"
                + typeProtocole.getProtocole() + "_"
                + reference + "_"
                + typeOperation.getOperation() + "_"
                + versionProtocole.getVersion() + "_"
                + sdf.format(dateCreation) + "_"
                + "01" + typeExtension.getExtension();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_FICHIER", initialValue = 1, allocationSize = 1)
    @Column(name = "FIC_ID")
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "FIC_NOM")
    private String nomFichier;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "FIC_CODE_OI")
    private CodeOperateur codeOI;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "FIC_CODE_OC")
    private CodeOperateur codeOC;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "FIC_STATUS")
    private StatusFichier statusFichier;

    @Enumerated(EnumType.STRING)
    @Column(name = "FIC_TYPE_PROTOCOLE")
    private TypeProtocole typeProtocole;

    @Enumerated(EnumType.STRING)
    @Column(name = "FIC_VERSION_PROTOCOLE")
    private VersionProtocole versionProtocole;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "FIC_TYPE_OPERATION")
    private TypeOperation typeOperation;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "FIC_TYPE_EXTENSION")
    private TypeExtension typeExtension;

    @NotNull
    @Temporal(value = TemporalType.DATE)
    @Column(name = "FIC_DATE_CREATION")
    private Date dateCreation;

    // @NotNull
    @Lob
    @Column(name = "FIC_DOCUMENT")
    private byte[] document;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "FIC_STATUS_TRAITEMENT")
    private StatusTraitement statusTraitement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public CodeOperateur getCodeOI() {
        return codeOI;
    }

    public void setCodeOI(CodeOperateur codeOI) {
        this.codeOI = codeOI;
    }

    public CodeOperateur getCodeOC() {
        return codeOC;
    }

    public void setCodeOC(CodeOperateur codeOC) {
        this.codeOC = codeOC;
    }

    public StatusFichier getStatusFichier() {
        return statusFichier;
    }

    public void setStatusFichier(StatusFichier statusFichier) {
        this.statusFichier = statusFichier;
    }

    public TypeProtocole getTypeProtocole() {
        return typeProtocole;
    }

    public void setTypeProtocole(TypeProtocole typeProtocole) {
        this.typeProtocole = typeProtocole;
    }

    public VersionProtocole getVersionProtocole() {
        return versionProtocole;
    }

    public void setVersionProtocole(VersionProtocole versionProtocole) {
        this.versionProtocole = versionProtocole;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public TypeExtension getTypeExtension() {
        return typeExtension;
    }

    public void setTypeExtension(TypeExtension typeExtension) {
        this.typeExtension = typeExtension;
    }

    public StatusTraitement getStatusTraitement() {
        return statusTraitement;
    }

    public void setStatusTraitement(StatusTraitement statusTraitement) {
        this.statusTraitement = statusTraitement;
    }

    @Override
    public String toString() {
        String  valeurs = "getOperation: "+typeOperation.getOperation() + "" +
                 "codeOI: "+ codeOI.getCode() + ""+
                 "codeOC: "+ codeOC.getCode() + ""+
                 "typeProtocole: "+ typeProtocole.getProtocole() + ""+
                 "typeOperation: "+ typeOperation.getOperation() + ""+
                 "versionProtocole: "+ versionProtocole.getVersion() + ""+
                 "dateCreation: "+ dateCreation+ ""+
                 "typeExtension: "+ typeExtension.getExtension();
        
        return valeurs;
    }
}
