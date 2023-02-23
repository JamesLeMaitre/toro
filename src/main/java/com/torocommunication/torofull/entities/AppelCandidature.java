package com.torocommunication.torofull.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppelCandidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private String resume;

    @Column(nullable = false,length = 6523)
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
    @JoinColumn
    private DetailSA detailSA;


    @JoinTable
    @ManyToMany
    @ToString.Exclude
    private Collection<JobType> jobTypes = new ArrayList<>();





}
