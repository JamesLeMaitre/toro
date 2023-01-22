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
public class Postuler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String diplome;


    @Column(nullable = false)
    private String lettreMotivation;

    @Column(nullable = false)
    private String curriculumVitae;

    @CreationTimestamp()
    @Column(nullable = false,updatable = false)
    private Date dateCreation;

    @UpdateTimestamp()
    @Column(nullable = false)
    private Date dateUpdate;


    @ManyToOne(cascade = CascadeType.ALL)
    private  AppelCandidature appelCandidature;


    @ManyToOne
    private UtilisateurUEA uea ;




}
