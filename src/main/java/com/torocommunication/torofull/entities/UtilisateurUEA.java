package com.torocommunication.torofull.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UtilisateurUEA {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(nullable = false)
    private String codeUEA;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String photoPiece;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateNaissance;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String cv;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String adresse;


    @Column(name = "ACTIF")
    private Boolean actif = false;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @ManyToOne
    @JoinColumn(name = "id_type_operateur")
    private TypeUEA typeUEA;

    @ManyToOne
    @JoinColumn(name = "id_type_operateur")
    private Abonnement abonnement;

    @ManyToOne
    @JoinColumn(name = "id_type_operateur")
    private DetailSA detailSA;

    @ManyToOne
    @JoinColumn(name = "id_type_operateur")
    private UtilisateurUEA utilisateurUEA;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleUEA> roleUEAS = new ArrayList<>();




}
