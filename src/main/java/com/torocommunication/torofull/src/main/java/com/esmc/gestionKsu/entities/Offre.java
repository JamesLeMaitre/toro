package com.torocommunication.torofull.src.main.java.com.esmc.gestionKsu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * {@link Offre} class
 * @author katoh <katohdavid@gmail.com>
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Offre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String libelle;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_type_offre")
    private TypeOffre typeOffre;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_type_pm")
    private TypePM typePM;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }

}
