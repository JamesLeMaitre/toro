package com.torocommunication.torofull.repo;

import com.torocommunication.torofull.entities.DetailSA;
import com.torocommunication.torofull.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FormationRepository extends JpaRepository<Formation,Long> {
    @Query("select count(a) from Formation a")
    int countBy();
}
