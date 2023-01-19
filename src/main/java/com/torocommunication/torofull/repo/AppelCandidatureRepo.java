package com.torocommunication.torofull.repo;

import com.torocommunication.torofull.entities.AppelCandidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppelCandidatureRepo extends JpaRepository<AppelCandidature,Long> {


    @Query("select a from AppelCandidature a where a.uea.id=:x  ")
    public List<AppelCandidature> listAppelCandidatureByIdUea(@Param("x") Long id);
}
