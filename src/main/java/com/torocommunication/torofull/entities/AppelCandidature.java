package com.torocommunication.torofull.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppelCandidature {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private String resume;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String adresse ;

    @Column(nullable = false)
    private String typeOffre ;

    @Column
    private Boolean etat=true;

    @CreationTimestamp()
    @Column(nullable = false,updatable = false)
    private Date dateCreation;

    @UpdateTimestamp()
    @Column(nullable = false)
    private Date dateUpdate;


    @ManyToOne
    @JoinColumn(name = "id_utilisateurUEA")
    private  UtilisateurUEA uea;

    @ManyToOne
    @JoinColumn(name = "id_detailSA")
    private DetailSA detailSA;





}
