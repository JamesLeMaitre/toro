package com.torocommunication.torofull.repo;

import com.torocommunication.torofull.entities.DetailSA;
import com.torocommunication.torofull.entities.TypeUEA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeUEARepository extends JpaRepository<TypeUEA,Long> {
    @Query("select count(a) from TypeUEA a")
    int countBy();
}
