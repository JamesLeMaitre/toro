package com.torocommunication.torofull.repo;

import com.torocommunication.torofull.entities.CategorieREDS;
import com.torocommunication.torofull.entities.DetailSA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategorieREDSRepository extends JpaRepository<CategorieREDS,Long> {
    @Query("select count(a) from CategorieREDS a")
    int countBy();
}
