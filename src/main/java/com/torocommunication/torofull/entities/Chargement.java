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
public class Chargement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String libellePoste;

    @Column(nullable = false)
    private boolean etat;

    @ManyToOne
    @JoinColumn()
    private UtilisateurUEA uea;

    @ManyToOne
    @JoinColumn()
    private AppelCandidature candidature;

   @CreationTimestamp
    private Date dateCreation;

    @UpdateTimestamp
    private Date dateUpdate;

}
