package com.torocommunication.torofull.repo;


import com.torocommunication.torofull.entities.UtilisateurUEA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurUEARepo extends JpaRepository<UtilisateurUEA,Long> {



    @Query("select u from UtilisateurUEA u where u.username=:x")
    Optional<UtilisateurUEA> findByUsername(@Param("x") String username);


    @Query("select u from UtilisateurUEA u where u.username=:x and u.password=:y ")
    UtilisateurUEA findByUsernameAndPassword(@Param("x") String username,@Param("y") String password);


    @Query("select count(a) from UtilisateurUEA a")
    int countBy();

    @Query("select count(a) from UtilisateurUEA a  inner join a.roles  roleUEA where roleUEA.rolename=trim(lower('ROLE_ADMIN')) ")
    int countByAdmin();



    @Query(value = "Select count(a.id) from UtilisateurUEA a WHERE  a.codeUEA like CONCAT(:x, '%')")
    public Integer  getLastIn(@Param("x") String code);



    @Query(value = "Select u from UtilisateurUEA u WHERE  u.typeUEA.id=:x and  u.detailSA.id=:y")
    public List<UtilisateurUEA> listUeaByIdTypeueaAndIdDetailsa(@Param("x") Long idType, @Param("y") Long idSA);


}
