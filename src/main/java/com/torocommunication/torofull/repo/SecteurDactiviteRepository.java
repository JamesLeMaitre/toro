package com.torocommunication.torofull.repo;

import com.torocommunication.torofull.entities.DetailSA;
import com.torocommunication.torofull.entities.SecteurDactivite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SecteurDactiviteRepository extends JpaRepository<SecteurDactivite,Long> {
    @Query("select count(a) from SecteurDactivite a")
    int countBy();
}
