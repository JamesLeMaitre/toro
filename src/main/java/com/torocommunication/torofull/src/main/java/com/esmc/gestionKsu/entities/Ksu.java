package com.torocommunication.torofull.src.main.java.com.esmc.gestionKsu.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * {@link Ksu} class
 * @author katoh <katohdavid@gmail.com>
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Ksu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeKsu;

    @Column(nullable = true)
    private String codeBanKsu;

    @Column(nullable = true)
    private String nom;

    @Column(nullable = true)
    private String prenom;

    @Column(nullable = true)
    private String raisonSocial;

    @Column(nullable = true)
    private String codeKsuRepresentant;

    @Column(nullable = true)
    private String statusJuridique;

    @Column(name = "denomination", length = 30, nullable = true)
    private String denomination;

    @Column(nullable = true)
    private String telephone;

    @Column(nullable = true)
    private String adresse;

    @Column(nullable = true)
    private String boitePostale;

    @Column(nullable = true)
    private String email;

    private String professionActuelle;

    private String formation;

    private String lieuNaissance;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNaissance;

   

    private String sexe;

    private String nomPere;

    private String nomMere;

    private String situationMatrimoniale;

    private String nombreEnfant;

    private String domicile;

    private String numeroCompteBanque;

    @Column(nullable = true)
    private String signature;

    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    private String numeroPieceId;

    @Column(nullable = true)
    private  String photoPieceId;

    private boolean valide = false;

    @Column(nullable = true)
    private String nif;

    @Column(nullable = true)
    private String numeroRCCM;

    @Column(nullable = true)
    private String agregation;

    @Column(nullable = true)
    private String autorisation;

    @Column(nullable = true)
    private String recepice;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @ManyToOne
    @JoinColumn(name = "id_piece_identite")
    private PieceIdentite pieceIdentite;

    @ManyToOne
    @JoinColumn(name = "id_abonnement")
    private Abonnement abonnement;

    @ManyToOne
    @JoinColumn(name = "id_secteur")
    private Secteur secteur;

    @ManyToOne
    @JoinColumn(name = "id_type_pm")
    private TypePM typePM;

    @ManyToOne
    @JoinColumn(name = "id_type_operateur")
    private TypeOperateur typeOperateur;

    @ManyToOne
    @JoinColumn(name = "id_banque")
    private Banque banque;

    @Column(name = "id_ma_ban_Ksu")
    private Long IdmaBanKsu;

    @Column(name = "id_canton")
    private Long idCanton;

    @Column(nullable = true)
    private Boolean EnableConnexionStatus=false;

    @Column(nullable = true)
    private String codeConnexion=null;

    @Column(nullable = true,length = 5000)
    private String tempFingerPrintKey=null;

    @Column( nullable = true)
    private Long DateAccessConexionTimeStamp=null;


    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }


}