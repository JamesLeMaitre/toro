package com.torocommunication.torofull.repo;

import com.torocommunication.torofull.entities.DetailSA;
import com.torocommunication.torofull.entities.TypeOffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeOffreRepository extends JpaRepository<TypeOffre,Long> {
    @Query("select count(a) from TypeOffre a")
    int countBy();
}
