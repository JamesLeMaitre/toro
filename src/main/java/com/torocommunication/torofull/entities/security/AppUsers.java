package com.torocommunication.torofull.entities.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUsers {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Boolean isNotLocked = true;

    private Boolean isActive = false;

    // Relation with role
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "idRole"))
    @ManyToMany
    @ToString.Exclude
    private Collection<AppRole> roles = new ArrayList<>();

    @CreationTimestamp
    private Date dateCreate;

    @UpdateTimestamp
    private Date dateUpdate;
    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }

}
