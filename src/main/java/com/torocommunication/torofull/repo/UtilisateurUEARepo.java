package com.torocommunication.torofull.repo;


import com.torocommunication.torofull.entities.UtilisateurUEA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurUEARepo extends JpaRepository<UtilisateurUEA,Long> {



    @Query("select u form UtilisateurUEA u where u.username=:x")
    UtilisateurUEA findByUsername(@Param("x") String username);
}
