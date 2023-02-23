package com.torocommunication.torofull.repo;


import com.torocommunication.torofull.entities.Chargement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargementRepo extends JpaRepository<Chargement,Long> {

    @Query("select c from Chargement c where c.uea.id=:x and c.candidature.id=:y")
    public  Chargement findByIdUeaAndCandidature(@Param("x") Long idUea,@Param("y") Long appel);



    @Query("select c from Chargement c where c.uea.id=:x and etat=true ")
    public List<Chargement> findByIdUea(@Param("x") Long idUea);
}
