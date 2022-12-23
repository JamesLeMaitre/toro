package com.torocommunication.torofull.repo;

import com.torocommunication.torofull.entities.Abonnement;
import com.torocommunication.torofull.entities.DetailSA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AbonnementRepository extends JpaRepository<Abonnement,Long> {
    @Query("select count(a) from Abonnement a")
    int countBy();
}
