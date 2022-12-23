package com.torocommunication.torofull.repo;

import com.torocommunication.torofull.entities.DetailSA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DetailSARepository extends JpaRepository<DetailSA,Long> {
    @Query("select count(a) from DetailSA a")
    int countBy();
}
