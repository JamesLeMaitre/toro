package com.torocommunication.torofull.repo;


import com.torocommunication.torofull.entities.HistoriqueUEA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriqueUEARepo extends JpaRepository<HistoriqueUEA,Long> {


    @Query("select h from HistoriqueUEA h where h.uea.id=:x  ")
    public List<HistoriqueUEA> listHistoriqueUEAByIdUEA(@Param("x") Long id);
}
