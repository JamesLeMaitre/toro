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
public class UtilisateurUEA {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUser", nullable = false)
    private Long id;
    @Column(nullable = true)
    private String codeUEA;

    @Column(nullable = true)
    private String nom;

    @Column(nullable = true)
    private String prenom;

    @Column(nullable = true)
    private String photoPiece;


    @Column(nullable = true)
    private String dateNaissance;

    @Column(nullable = true)
    private String genre;

    @Column(nullable = true)
    private String cv;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String adresse;

    @Column(nullable = true)
    private String tel;


    @Column(name = "ACTIF")
    private Boolean actif = false;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

   @Column
    private Boolean isNotLocked = true;

    @Column
    private Boolean isActive = false;

    @Column
    private Boolean etat = true;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @ManyToOne
    @JoinColumn(name = "id_type_UEA")
    private TypeUEA typeUEA;

    @ManyToOne
    @JoinColumn(name = "id_type_operateur")
    private Abonnement abonnement;

    @ManyToOne
    @JoinColumn(name = "detailSA")
    private DetailSA detailSA;






    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "idRole"))
    @ManyToMany
    @ToString.Exclude
    private Collection<RoleUEA> roles = new ArrayList<>();



}
