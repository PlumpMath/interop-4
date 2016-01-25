package org.mediaserv.fibre.protocole.domain.pm;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Thomas Lecocq
 */
@Entity
@Table(name = "PBO", uniqueConstraints = {@UniqueConstraint(name = "UK_REF_PBO", columnNames = {"PBO_REF_PRESTATION"})})
public class PBO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SEQUENCE_PBO", initialValue = 1, allocationSize = 1)
    @Column(name = "PBO_ID")
    private Long id;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private Collection<Immeuble> immeubles;

    /**
     * Ce champ permet à l'opérateur d'immeuble de communiquer aux opérateurs
     * commerciaux des informations sur les modalités de raccordement de
     * l'adresse. Le contenu de ce champ n'est pas normé, cependant une
     * proposition de liste non exhaustive est proposée en bonne pratique dans
     * le document d'accompagnement du protocole. Cette liste permettrait de
     * préciser le type de PBO et le type d'adduction au bâtiment en cas de PBO
     * extérieur *
     */
    @Column(name = "PBO_CAT_RACC_LOGEMENT")
    private String categorieRaccoLogement;

    /**
     * "Ce champ permet à l'opérateur d'immeuble de communiquer une référence
     * commerciale associée aux nouvelles adresses mises à disposition dans le
     * CR MAD. Le CR MAD pouvant concerner une ou plusieurs adresses à la fois,
     * la ReferencePrestationPB permet de faire le lien avec la facturation des
     * logements raccordables
     *
     * Cette référence pourra être reprise dans la facture.
     *
     * Un CR MAD cumulant plusieurs adresses livrées à des dates différentes
     * portera plusieurs RéférencesPrestationPB associées à chaque ligne
     * d'adresse. "
     *
     */
    @Column(name = "PBO_REF_PRESTATION")
    private String referencePrestationPBs;

    /**
     * "Ce champ contient le nombre cumulé de logements raccordables livrés en
     * même temps lors d'une Mise à Disposition concernée par le CR. Ce nombre
     * est relatif à une référencePrestationPBs.
     *
     * Par exemple, pour un CR concernant une Mise à Disposition de 3 adresses
     * de 10, 3 et 5 logements, le nombre de logements prestation PB indiqué
     * dans ce champ sera de 18
     *
     * Ce champ sert à facturer les adresses raccordables livrées à une certaine
     * date"
     *
     */
    @Column(name = "PBO_NB_LOG_PRESTATION")
    private String nombreLogementsPrestationPBs;

    /**
     * Ce champ correspond à la date de la Mise à disposition des adresses
     * livrées en même temps lors d'un seul et unique CR MAD et associées à une
     * seule et même ReferencePrestationPBs. Cette date est répétée à
     * l'identique pour toutes les adresses livrées en même temps
     *
     */
    @Column(name = "PBO_DATE_MAD_PRESTATION")
    private String dateMADprestationPBs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Immeuble> getImmeubles() {
        return immeubles;
    }

    public void setImmeubles(Collection<Immeuble> immeubles) {
        this.immeubles = immeubles;
    }

    public String getCategorieRaccoLogement() {
        return categorieRaccoLogement;
    }

    public void setCategorieRaccoLogement(String categorieRaccoLogement) {
        this.categorieRaccoLogement = categorieRaccoLogement;
    }

    public String getReferencePrestationPBs() {
        return referencePrestationPBs;
    }

    public void setReferencePrestationPBs(String referencePrestationPBs) {
        this.referencePrestationPBs = referencePrestationPBs;
    }

    public String getNombreLogementsPrestationPBs() {
        return nombreLogementsPrestationPBs;
    }

    public void setNombreLogementsPrestationPBs(String nombreLogementsPrestationPBs) {
        this.nombreLogementsPrestationPBs = nombreLogementsPrestationPBs;
    }

    public String getDateMADprestationPBs() {
        return dateMADprestationPBs;
    }

    public void setDateMADprestationPBs(String dateMADprestationPBs) {
        this.dateMADprestationPBs = dateMADprestationPBs;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.referencePrestationPBs != null ? this.referencePrestationPBs.hashCode() : 0);
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
        final PBO other = (PBO) obj;
        if ((this.referencePrestationPBs == null) ? (other.referencePrestationPBs != null) : !this.referencePrestationPBs.equals(other.referencePrestationPBs)) {
            return false;
        }
        return true;
    }

}
