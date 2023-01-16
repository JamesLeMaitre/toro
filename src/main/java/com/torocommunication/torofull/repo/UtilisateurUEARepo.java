package com.torocommunication.torofull.repo;


import com.torocommunication.torofull.entities.UtilisateurUEA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurUEARepo extends JpaRepository<UtilisateurUEA,Long> {



    @Query("select u from UtilisateurUEA u where u.username=:x")
    Optional<UtilisateurUEA> findByUsername(@Param("x") String username);



}
